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
        mavenLocal()
        mavenCentral()
        jcenter()
        maven {
            url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
        }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
    }
}

include(
    "plumbing-api",
    "plumbing-1_16_4",
    "plumbing-1_16_3",
    "plumbing-1_16_1",
    "plumbing-lib"
)
