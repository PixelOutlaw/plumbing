package io.pixeloutlaw.minecraft.spigot.plumbing.v116R1

import io.pixeloutlaw.minecraft.spigot.plumbing.api.AbstractMessageBroadcaster
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.ItemTag
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Item
import net.md_5.bungee.api.chat.hover.content.Text
import net.minecraft.server.v1_16_R1.NBTTagCompound
import org.bukkit.craftbukkit.v1_16_R1.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

class MessageBroadcaster : AbstractMessageBroadcaster() {
    override fun createShowItemHoverEvent(itemStack: ItemStack): HoverEvent {
        val nbtTagCompound = NBTTagCompound()
        val nmsItemStack = CraftItemStack.asNMSCopy(itemStack)
        val nbtTagString = nmsItemStack.save(nbtTagCompound).toString()
        val itemTag = ItemTag.ofNbt(nbtTagString)
        val item = Item(itemStack.type.key.toString(), itemStack.amount, itemTag)
        return HoverEvent(HoverEvent.Action.SHOW_ITEM, item)
    }
}
