plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.10.1"
}

rootProject.name = "plumbing"

gradle.allprojects {
    group = "io.pixeloutlaw"

    repositories {
        mavenCentral()
        maven {
            url = uri("https://repo.minebench.de/")
        }
        maven {
            url = uri("https://repo.codemc.org/repository/nms")
        }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
    }
}

include(
    "plumbing-api",
    "plumbing-1_16_R3",
    "plumbing-1_16_R2",
    "plumbing-1_16_R1",
    "plumbing-1_15_R1",
    "plumbing-lib"
)
