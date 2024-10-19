package com.novvux.sarkicism.block

import com.novvux.sarkicism.Sarkicism
import com.novvux.sarkicism.entity.FleshBoxBlockEntity
import com.novvux.sarkicism.block.custom.FleshBoxBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos


object ModBlocks {
    val FLESH_BOX_BLOCK: Block
    private val FLESH_BOX_BLOCK_ITEM: BlockItem
    private var FLESH_BOX_BLOCK_ENTITY: BlockEntityType<FleshBoxBlockEntity>

    // a public identifier for multiple parts of our bigger chest
    val FLESH_BOX: Identifier = Identifier.of(Sarkicism.MOD_ID, "flesh_box")

    init {
        FLESH_BOX_BLOCK = Registry.register<Block, Block>(
            Registries.BLOCK, FLESH_BOX, FleshBoxBlock(FabricBlockSettings.copyOf(Blocks.CHEST))
        )
            FLESH_BOX_BLOCK_ITEM = Registry.register(Registries.ITEM, FLESH_BOX, BlockItem(FLESH_BOX_BLOCK, Item.Settings()))

        //The parameter of build at the very end is always null, do not worry about it
        FLESH_BOX_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, FLESH_BOX,
            FabricBlockEntityTypeBuilder.create({ pos: BlockPos, state: BlockState -> FleshBoxBlockEntity(pos, state) }, FLESH_BOX_BLOCK).build(null))
    }

    fun register(block: Block, name: String, shouldRegisterItem: Boolean): Block {
        // Register the block and its item
        val id: Identifier = Identifier.of(Sarkicism.MOD_ID, name)

        // Should block have item?
        if (shouldRegisterItem) {
            val blockItem = BlockItem(block, Item.Settings())
            Registry.register(Registries.ITEM, id, blockItem)
        }

        return Registry.register(Registries.BLOCK, id, block)
    }

    fun initialize() {

    }
}