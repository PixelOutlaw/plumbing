package io.pixeloutlaw.minecraft.spigot.plumbing.api

import net.kyori.adventure.key.Key
import net.kyori.adventure.nbt.api.BinaryTagHolder
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
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
        itemStack: ItemStack,
        bukkitAudiences: BukkitAudiences,
    ) = broadcastItem(format, player, itemStack, bukkitAudiences, BroadcastTarget.SERVER)

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
        bukkitAudiences: BukkitAudiences,
        target: BroadcastTarget,
    ) {
        val displayName = player.displayName
        val locale = format.replaceArgs("%player%" to displayName).chatColorize()
        val messages = locale.split("%item%")
        var broadcastComponent = Component.empty()
        val itemStackName = itemStack.getDisplayName() ?: itemStack.type.name.split("_")
            .joinToString(" ").toTitleCase()
        val itemStackAsJson = convertItemStackToJson(itemStack)
        val nbtBinary = BinaryTagHolder.of(itemStackAsJson)
        val itemStackNameComponent = LegacyComponentSerializer.legacySection().deserialize(itemStackName).hoverEvent(
            HoverEvent.showItem(
                Key.key(
                    itemStack.type.key.namespace,
                    itemStack.type.key.key
                ),
                itemStack.amount, nbtBinary
            )
        )
        messages.indices.forEach { idx ->
            val key = messages[idx]
            broadcastComponent = broadcastComponent.append(LegacyComponentSerializer.legacyAmpersand().deserialize(key))
            if (idx < messages.size - 1) {
                broadcastComponent = broadcastComponent.append(itemStackNameComponent)
            }
        }

        when (target) {
            BroadcastTarget.SERVER -> {
                bukkitAudiences.players().sendMessage(broadcastComponent)
            }
            BroadcastTarget.WORLD -> {
                bukkitAudiences.world(Key.key(player.world.name)).sendMessage(broadcastComponent)
            }
            BroadcastTarget.PLAYER -> {
                bukkitAudiences.player(player).sendMessage(broadcastComponent)
            }
        }
    }

    abstract fun convertItemStackToJson(itemStack: ItemStack): String

    private fun ItemStack.getDisplayName(): String? = this.itemMeta?.let {
        return if (it.hasDisplayName()) {
            it.displayName
        } else {
            null
        }
    }
}
