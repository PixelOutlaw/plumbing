plugins {
    kotlin("jvm")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")

    api(project(":plumbing-api"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:_")
    rootProject.subprojects.filter { it.name.contains("_R") }.forEach {
        implementation(project(":${it.name}"))
    }
}
