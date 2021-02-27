plugins {
    kotlin("jvm")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")

    api(project(":plumbing-api"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:_")
    implementation(project(":plumbing-1_16_R3"))
    implementation(project(":plumbing-1_16_R2"))
    implementation(project(":plumbing-1_16_R1"))
    implementation(project(":plumbing-1_15_R3"))
    implementation(project(":plumbing-1_15_R2"))
    implementation(project(":plumbing-1_15_R1"))
}
