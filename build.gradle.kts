plugins {
    id("java")
    kotlin("jvm") version "2.1.20"
}

repositories {
    mavenCentral()
}

group = "de.haw.landshut"
version = "0.1"

val bundleExamples = tasks.register<Zip>("bundle") {
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
    from(file("build.gradle.kts")) {
        into("javaAndMidi")
    }
    from(file("gradlew")) {
        into("javaAndMidi")
    }
    from(file("gradlew.bat")) {
        into("javaAndMidi")
    }
    from(file("settings.gradle.kts")) {
        into("javaAndMidi")
    }
}
