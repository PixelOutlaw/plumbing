package io.pixeloutlaw.minecraft.spigot.plumbing.v116R3

import io.pixeloutlaw.minecraft.spigot.plumbing.api.AbstractMessageBroadcaster
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import net.minecraft.server.v1_16_R3.NBTTagCompound
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

class MessageBroadcaster : AbstractMessageBroadcaster() {
    override fun convertItemStackToJson(itemStack: ItemStack): String {
        val nbtTagCompound = NBTTagCompound()
        val nmsItemStack = CraftItemStack.asNMSCopy(itemStack)
        return nmsItemStack.save(nbtTagCompound).toString()
    }

    override fun createShowItemHoverEvent(itemStackJson: String): HoverEvent {
        return HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(arrayOf(TextComponent(itemStackJson))))
    }
}
