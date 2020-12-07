package io.pixeloutlaw.minecraft.spigot.plumbing.lib

import io.pixeloutlaw.minecraft.spigot.plumbing.api.AbstractMessageBroadcaster
import io.pixeloutlaw.minecraft.spigot.plumbing.api.MinecraftVersions
import net.md_5.bungee.api.chat.HoverEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object MessageBroadcaster {
    private val broadcasterByServer: AbstractMessageBroadcaster by lazy {
        val bukkitPackageName = Bukkit.getServer().javaClass.`package`.name
        when (try { bukkitPackageName.split("\\.")[3] } catch (e: IndexOutOfBoundsException) { "" }) {
            "v1_16_R3" -> io.pixeloutlaw.minecraft.spigot.plumbing.MessageBroadcaster1164()
            "v1_16_R2" -> io.pixeloutlaw.minecraft.spigot.plumbing.MessageBroadcaster1163()
            "v1_16_R1" -> io.pixeloutlaw.minecraft.spigot.plumbing.MessageBroadcaster1161()
            else -> {
                NoOpMessageBroadcaster()
            }
        }
    }

    /**
     * Returns `true` if the version of bukkit
     */
    val isSupportedBukkitVersion: Boolean by lazy { MinecraftVersions.isAtLeastMinecraft116 }

    /**
     * Broadcasts a message about the player and item using the given format. Replaces "%player%" with the player's
     * display name and replaces "%item%" with the item tooltip for the given item.
     *
     * @param format Format of message
     * @param player Player to reference
     * @param itemStack ItemStack to reference
     * @param target Who should see the broadcast
     * @param visibility Should item name be visible
     */
    fun broadcastItem(
        format: String,
        player: Player,
        itemStack: ItemStack,
        target: AbstractMessageBroadcaster.BroadcastTarget,
        visibility: AbstractMessageBroadcaster.BroadcastItemNameVisibility
    ) {
        if (!isSupportedBukkitVersion) return // do nothing for unsupported bukkit versions
        if (broadcasterByServer is NoOpMessageBroadcaster) return // do nothing for NoOp message broadcaster
        broadcasterByServer.broadcastItem(format, player, itemStack, target, visibility)
    }

    internal class NoOpMessageBroadcaster: AbstractMessageBroadcaster() {
        override fun convertItemStackToJson(itemStack: ItemStack): String {
            return "{}"
        }

        override fun createShowItemHoverEvent(itemStackJson: String): HoverEvent {
            return HoverEvent(HoverEvent.Action.SHOW_ITEM)
        }
    }
}