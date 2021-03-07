import de.fayard.refreshVersions.bootstrapRefreshVersions

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:0.9.7")
}

rootProject.name = "plumbing"

bootstrapRefreshVersions()

gradle.allprojects {
    group = "io.pixeloutlaw"

    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        }
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
