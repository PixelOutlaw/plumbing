package io.pixeloutlaw.minecraft.spigot.plumbing.api

import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Allows broadcasting messages on a Spigot server.
 */
abstract class AbstractMessageBroadcaster {
    /**
     * To whom should the broadcast be sent?
     */
    enum class BroadcastTarget {
        SERVER,
        WORLD,
        PLAYER
    }

    /**
     * Should the item's name be visible in the broadcast?
     */
    enum class BroadcastItemNameVisibility {
        HIDE,
        SHOW
    }

    /**
     * Broadcasts a message about the player and item using the given format. Replaces "%player%" with the player's
     * display name and replaces "%item%" with the item tooltip for the given item.
     *
     * Equivalent of calling
     * `broadcastItem(format, player, itemStack, BroadcastTarget.SERVER, BroadcastItemNameVisibility.HIDE)`
     *
     * @param format Format of message
     * @param player Player to reference
     * @param itemStack ItemStack to reference
     */
    fun broadcastItem(
        format: String,
        player: Player,
        itemStack: ItemStack
    ) = broadcastItem(format, player, itemStack, BroadcastTarget.SERVER, BroadcastItemNameVisibility.HIDE)

    /**
     * Broadcasts a message about the player and item using the given format. Replaces "%player%" with the player's
     * display name and replaces "%item%" with the item tooltip for the given item.
     *
     * Equivalent of calling `broadcastItem(format, player, itemStack, target, BroadcastItemNameVisibility.HIDE)`
     *
     * @param format Format of message
     * @param player Player to reference
     * @param itemStack ItemStack to reference
     * @param target Who should see the broadcast
     */
    fun broadcastItem(
        format: String,
        player: Player,
        itemStack: ItemStack,
        target: BroadcastTarget
    ) = broadcastItem(format, player, itemStack, target, BroadcastItemNameVisibility.HIDE)

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
        target: BroadcastTarget,
        visibility: BroadcastItemNameVisibility
    ) {
        val displayName = player.displayName
        val locale = format.replaceArgs("%player%" to displayName).chatColorize()
        val messages = locale.split("%item%")
        val broadcastComponent = TextComponent("")
        val itemStackName = when (visibility) {
            BroadcastItemNameVisibility.HIDE -> "[Item]"
            BroadcastItemNameVisibility.SHOW ->
                itemStack.getDisplayName() ?: itemStack.type.name.split("_")
                    .joinToString(" ").toTitleCase()
        }
        val itemStackNameComponent = TextComponent()
        TextComponent.fromLegacyText(itemStackName).forEach {
            itemStackNameComponent.addExtra(it)
        }
        itemStackNameComponent.hoverEvent = createShowItemHoverEvent(itemStack)
        messages.indices.forEach { idx ->
            val key = messages[idx]
            TextComponent.fromLegacyText(key).forEach {
                broadcastComponent.addExtra(it)
            }
            if (idx < messages.size - 1) {
                broadcastComponent.addExtra(itemStackNameComponent)
            }
        }

        when (target) {
            BroadcastTarget.SERVER -> {
                Bukkit.getOnlinePlayers().forEach { p ->
                    p.spigot().sendMessage(broadcastComponent)
                }
            }
            BroadcastTarget.WORLD -> {
                player.world.players.forEach { p ->
                    p.spigot().sendMessage(broadcastComponent)
                }
            }
            BroadcastTarget.PLAYER -> {
                player.spigot().sendMessage(broadcastComponent)
            }
        }
    }

    abstract fun createShowItemHoverEvent(itemStack: ItemStack): HoverEvent

    private fun ItemStack.getDisplayName(): String? = this.itemMeta?.let {
        return if (it.hasDisplayName()) {
            it.displayName
        } else {
            null
        }
    }
}
