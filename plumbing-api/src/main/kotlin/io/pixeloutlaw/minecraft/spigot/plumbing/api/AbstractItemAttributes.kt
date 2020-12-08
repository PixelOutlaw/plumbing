package io.pixeloutlaw.minecraft.spigot.plumbing.api

import com.google.common.collect.Multimap
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

/**
 * Utility for getting default attributes from items.
 */
abstract class AbstractItemAttributes {
    /**
     * Gets the default attribute values for an [ItemStack] in a specific [EquipmentSlot].
     */
    abstract fun getDefaultItemAttributes(itemStack: ItemStack, equipmentSlot: EquipmentSlot): Multimap<Attribute, AttributeModifier>
}
