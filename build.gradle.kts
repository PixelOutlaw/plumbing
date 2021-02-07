plugins {
    kotlin("jvm") apply false
    id("io.pixeloutlaw.gradle")
    id("io.pixeloutlaw.spigot.build")
}

description = "NMS and OBC Adapters for MythicDrops"

subprojects {
    this@subprojects.description = rootProject.description
    this@subprojects.version = rootProject.version
}

spigotBuildTools {
    versions = listOf(
        "1.16.5", // 1_16_R3
        "1.16.3", // 1_16_R2
        "1.16.1" // 1_16_R1
    )
}
