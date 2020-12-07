plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.4-R0.1-SNAPSHOT")

    api(project(":plumbing-api"))

    implementation(project(":plumbing-1_16_4"))
    implementation(project(":plumbing-1_16_3"))
    implementation(project(":plumbing-1_16_2"))
    implementation(project(":plumbing-1_16_1"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:_")
}
