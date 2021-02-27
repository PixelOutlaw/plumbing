plugins {
    kotlin("jvm")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.4-R0.1-SNAPSHOT")

    api("net.kyori:adventure-platform-bukkit:_")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:_")
    implementation("net.kyori:adventure-text-serializer-gson:_") {
        exclude(group = "com.google.code.gson", module = "gson")
    }
}
