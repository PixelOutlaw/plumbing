package io.pixeloutlaw.minecraft.spigot.plumbing.api

import org.bukkit.Bukkit

/**
 * Utility for determining which version of Minecraft is in use.
 */
object MinecraftVersions {
    private const val THIRD_DOT = 3

    val nmsVersion: String by lazy {
        Bukkit.getServer().javaClass.`package`.name.split(".")[THIRD_DOT]
    }

    /**
     * Returns true if the PrepareSmithingEvent class exists, which means we're in 1.16+ and a newer version
     * of 1.16+.
     */
    val isAtLeastNewerMinecraft116 by lazy {
        try {
            Class.forName("org.bukkit.event.inventory.PrepareSmithingEvent")
            true
        } catch (ex: ClassNotFoundException) {
            false
        }
    }

    /**
     * Returns true if the Piglin interface exists, which means we're in 1.16+.
     */
    val isAtLeastMinecraft116 by lazy {
        try {
            Class.forName("org.bukkit.entity.Piglin")
            true
        } catch (ex: ClassNotFoundException) {
            false
        }
    }

    /**
     * Returns true if the PersistentDataHolder interface exists, which means we're in 1.15+.
     */
    val isAtLeastMinecraft115 by lazy {
        try {
            Class.forName("org.bukkit.persistence.PersistentDataHolder")
            true
        } catch (ex: ClassNotFoundException) {
            false
        }
    }

    /**
     * Returns true if the GrindstoneInventory interface exists, which means we're in 1.14+.
     */
    val isAtLeastMinecraft114 by lazy {
        try {
            Class.forName("org.bukkit.inventory.GrindstoneInventory")
            true
        } catch (ex: ClassNotFoundException) {
            false
        }
    }

    /**
     * Returns true if the Damageable interface exists, which means we're in 1.13+.
     */
    val isAtLeastMinecraft113 by lazy {
        try {
            Class.forName("org.bukkit.inventory.meta.Damageable")
            true
        } catch (ex: ClassNotFoundException) {
            false
        }
    }
}
