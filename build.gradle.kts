plugins {
    kotlin("jvm")
    id("nebula.release") version "15.3.0"
    id("nebula.nebula-bintray") version "8.5.0"
    id("io.pixeloutlaw.multi") version "2.0.5"
    id("io.pixeloutlaw.spigot.build") version "2.0.5"
}

bintray {
    pkgName.value("plumbing")
    repo.value("pixeloutlaw-jars")
    userOrg.value("pixeloutlaw")
    syncToMavenCentral.value(false)
}

spigotBuildTools {
    versions = listOf(
        "1.16.5",
        "1.16.4",
        "1.16.3",
        "1.16.2",
        "1.16.1"
    )
}
