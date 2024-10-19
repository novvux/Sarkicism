package com.novvux.sarkicism.entity

import com.novvux.sarkicism.Sarkicism
import com.novvux.sarkicism.block.ModBlocks
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos


object ModEntities {
    val FLESH_BOX_BLOCK_ENTITY: BlockEntityType<FleshBoxBlockEntity> =
        Registry.register<BlockEntityType<*>, BlockEntityType<FleshBoxBlockEntity>>(Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(Sarkicism.MOD_ID, "flesh_box_entity"),
            BlockEntityType.Builder.create({ pos: BlockPos, state: BlockState -> FleshBoxBlockEntity(pos, state)
            }, ModBlocks.FLESH_BOX_BLOCK).build()
        )


    fun initialize() {

    }
}