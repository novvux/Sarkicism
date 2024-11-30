package com.novvux.sarkicism.util

import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text

class AttributeUtils {
    companion object {
        fun changePlayerAttributes(player: PlayerEntity) {
        // Пример изменения здоровья (максимальное здоровье)
        //player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 40.0
        //player.health = player.health+100 // Обновить текущее здоровье

            player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue += 0.1

        // Пример изменения скорости
        //player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.addTemporaryModifier(
        //	EntityAttributeModifier(Identifier.of(MOD_ID, "speedboost"), 1.0, EntityAttributeModifier.Operation.ADD_VALUE)
        //)
        }
    }
}