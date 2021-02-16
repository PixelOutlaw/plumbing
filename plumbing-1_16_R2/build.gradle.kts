plugins {
    kotlin("jvm")
}

dependencies {
    compileOnly("org.spigotmc:spigot:1.16.3-R0.1-SNAPSHOT")

    api(project(":plumbing-api"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:_")
}
