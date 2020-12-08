package io.pixeloutlaw.minecraft.spigot.plumbing

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import io.pixeloutlaw.minecraft.spigot.plumbing.api.AbstractItemAttributes
import net.minecraft.server.v1_16_R3.AttributeBase
import net.minecraft.server.v1_16_R3.AttributeModifier
import net.minecraft.server.v1_16_R3.EnumItemSlot
import net.minecraft.server.v1_16_R3.GenericAttributes
import org.bukkit.attribute.Attribute
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

class ItemAttributes1164 : AbstractItemAttributes() {
    private val genericAttributeList = listOf(
        GenericAttributes.MAX_HEALTH,
        GenericAttributes.FOLLOW_RANGE,
        GenericAttributes.KNOCKBACK_RESISTANCE,
        GenericAttributes.MOVEMENT_SPEED,
        GenericAttributes.FLYING_SPEED,
        GenericAttributes.ATTACK_DAMAGE,
        GenericAttributes.ATTACK_KNOCKBACK,
        GenericAttributes.ATTACK_SPEED,
        GenericAttributes.ARMOR,
        GenericAttributes.ARMOR_TOUGHNESS,
        GenericAttributes.LUCK,
        GenericAttributes.SPAWN_REINFORCEMENTS,
        GenericAttributes.JUMP_STRENGTH
    )

    override fun getDefaultItemAttributes(
        itemStack: ItemStack,
        equipmentSlot: EquipmentSlot
    ): Multimap<Attribute, org.bukkit.attribute.AttributeModifier> {
        val vanillaItemStack = ItemStack(itemStack.type) // create copy with no metadata at all
        val nmsItemStack = CraftItemStack.asNMSCopy(vanillaItemStack)
        val attributeMap = nmsItemStack.a(convertEquipmentSlotToEnumItemSlot(equipmentSlot))
        val defaultAttributeMap = HashMultimap.create<Attribute, org.bukkit.attribute.AttributeModifier>()

        // iterate through each of the known attributes for this version of MC
        genericAttributeList.forEach { attribute ->
            // if the default list contains the iterated attribute, add any attribute modifiers to the known list
            attributeMap[attribute]?.let { attributeModifiers ->
                defaultAttributeMap.putAll(
                    convertGenericAttribute(attribute),
                    attributeModifiers.map { convertNmsAttributeModifier(it, equipmentSlot) }
                )
            }
        }
        return defaultAttributeMap
    }

    private fun convertNmsAttributeModifier(
        nmsAttributeModifier: AttributeModifier,
        equipmentSlot: EquipmentSlot
    ): org.bukkit.attribute.AttributeModifier {
        return org.bukkit.attribute.AttributeModifier(
            nmsAttributeModifier.uniqueId,
            nmsAttributeModifier.name,
            nmsAttributeModifier.amount,
            convertNmsAttributeModifierOperation(nmsAttributeModifier.operation),
            equipmentSlot
        )
    }

    private fun convertNmsAttributeModifierOperation(nmsAttributeModifierOperation: AttributeModifier.Operation): org.bukkit.attribute.AttributeModifier.Operation =
        when (nmsAttributeModifierOperation) {
            AttributeModifier.Operation.ADDITION -> org.bukkit.attribute.AttributeModifier.Operation.ADD_NUMBER
            AttributeModifier.Operation.MULTIPLY_BASE -> org.bukkit.attribute.AttributeModifier.Operation.ADD_SCALAR
            AttributeModifier.Operation.MULTIPLY_TOTAL -> org.bukkit.attribute.AttributeModifier.Operation.MULTIPLY_SCALAR_1
        }

    private fun convertGenericAttribute(genericAttributes: AttributeBase): Attribute? =
        when (genericAttributes) {
            GenericAttributes.MAX_HEALTH -> Attribute.GENERIC_MAX_HEALTH
            GenericAttributes.FOLLOW_RANGE -> Attribute.GENERIC_FOLLOW_RANGE
            GenericAttributes.KNOCKBACK_RESISTANCE -> Attribute.GENERIC_KNOCKBACK_RESISTANCE
            GenericAttributes.MOVEMENT_SPEED -> Attribute.GENERIC_MOVEMENT_SPEED
            GenericAttributes.FLYING_SPEED -> Attribute.GENERIC_FLYING_SPEED
            GenericAttributes.ATTACK_DAMAGE -> Attribute.GENERIC_ATTACK_DAMAGE
            GenericAttributes.ATTACK_KNOCKBACK -> Attribute.GENERIC_ATTACK_KNOCKBACK
            GenericAttributes.ATTACK_SPEED -> Attribute.GENERIC_ATTACK_SPEED
            GenericAttributes.ARMOR -> Attribute.GENERIC_ARMOR
            GenericAttributes.ARMOR_TOUGHNESS -> Attribute.GENERIC_ARMOR_TOUGHNESS
            GenericAttributes.LUCK -> Attribute.GENERIC_LUCK
            GenericAttributes.SPAWN_REINFORCEMENTS -> Attribute.ZOMBIE_SPAWN_REINFORCEMENTS
            GenericAttributes.JUMP_STRENGTH -> Attribute.HORSE_JUMP_STRENGTH
            else -> null
        }

    private fun convertEquipmentSlotToEnumItemSlot(equipmentSlot: EquipmentSlot): EnumItemSlot = when (equipmentSlot) {
        EquipmentSlot.HAND -> EnumItemSlot.MAINHAND
        EquipmentSlot.OFF_HAND -> EnumItemSlot.OFFHAND
        EquipmentSlot.FEET -> EnumItemSlot.FEET
        EquipmentSlot.LEGS -> EnumItemSlot.LEGS
        EquipmentSlot.CHEST -> EnumItemSlot.CHEST
        EquipmentSlot.HEAD -> EnumItemSlot.HEAD
    }
}
