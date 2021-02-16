plugins {
    kotlin("jvm") apply false
    id("io.pixeloutlaw.gradle")
}

description = "NMS and OBC Adapters for MythicDrops"

subprojects {
    this@subprojects.description = rootProject.description
    this@subprojects.version = rootProject.version
}
