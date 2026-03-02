plugins {
    id("java")
}

repositories {
    mavenCentral()
}

group = "de.haw.landshut"
version = "0.2"

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

val cleanBundle = tasks.register<Delete>("cleanBundle") {
    delete(file(rootProject.layout.buildDirectory.file("bundle")))
}

val buildPdf = tasks.register<Exec>("buildPdf") {
    workingDir = file("workshop")
    commandLine("pdflatex", "-interaction=nonstopmode", "workshop.tex")
}

val bundleExamples = tasks.register<Zip>("bundle") {
    dependsOn(cleanBundle, buildPdf)
    group = "bundle"
    archiveFileName = "javaAndMidi-${project.version}.zip"
    destinationDirectory = layout.buildDirectory.dir("bundle")
    from(file("src")) {
        exclude {
            it.isDirectory && it.name == "build"
        }
        exclude {
            it.isDirectory && it.name == ".gradle"
        }
        exclude {
            it.isDirectory && it.name == "kotlin"
        }
        into("javaAndMidi/src")
    }
    from(file("workshop/workshop.pdf")) {
        into("javaAndMidi")
    }
    from(file("gradle")) {
        into("javaAndMidi/gradle")
    }
    from(file("build.gradle.kts.minimal")) {
        into("javaAndMidi")
        rename {
            "build.gradle.kts"
        }
    }
    from(file("gradlew")) {
        filePermissions {
            unix("rwxr-xr-x")
        }
        into("javaAndMidi")
    }
    from(file("gradlew.bat")) {
        into("javaAndMidi")
    }
    from(file("settings.gradle.kts")) {
        into("javaAndMidi")
    }
}
