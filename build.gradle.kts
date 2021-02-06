plugins {
    kotlin("jvm")
    id("nebula.release")
    id("nebula.nebula-bintray")
    id("io.pixeloutlaw.multi")
    id("io.pixeloutlaw.spigot.build")
}

bintray {
    pkgName.value("plumbing")
    repo.value("pixeloutlaw-jars")
    userOrg.value("pixeloutlaw")
    syncToMavenCentral.value(false)
}

spigotBuildTools {
    versions = listOf(
        "1.16.5", // 1_16_R3
        "1.16.3", // 1_16_R2
        "1.16.1" // 1_16_R1
    )
}
