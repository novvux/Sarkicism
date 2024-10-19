package com.novvux.sarkicism.item

import com.novvux.sarkicism.Sarkicism
import com.novvux.sarkicism.item.custom.TooltipItem
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier


object ModItems {
    // Item group name, id and key
    val CUSTOM_ITEM_GROUP_KEY: RegistryKey<ItemGroup> =
        RegistryKey.of(Registries.ITEM_GROUP.key, Identifier.of(Sarkicism.MOD_ID, "item_group"))
    val CUSTOM_ITEM_GROUP: ItemGroup = FabricItemGroup.builder()
        //.icon { ItemStack() }
        .displayName(Text.translatable("itemGroup.sarkicism"))
        .build()

    // Items

    // Modifiers
    val DULL_CRYSTAL: Item = register(TooltipItem("itemTooltip.sarkicism.dull_crystal", Formatting.GOLD, Item.Settings()), "dull_crystal")
    val FAINT_CRYSTAL: Item = register(TooltipItem("itemTooltip.sarkicism.faint_crystal", Formatting.GOLD, Item.Settings()), "faint_crystal")
    val GLITTERING_CRYSTAL: Item = register(TooltipItem("itemTooltip.sarkicism.glittering_crystal", Formatting.GOLD, Item.Settings()), "glittering_crystal")
    val SHINING_CRYSTAL: Item = register(TooltipItem("itemTooltip.sarkicism.shining_crystal", Formatting.GOLD, Item.Settings()), "shining_crystal")

    val SWORD_HEAD: Item = register(TooltipItem("itemTooltip.sarkicism.head_modifier", Formatting.GRAY, Item.Settings().maxCount(32)), "sword_head")
    val PICKAXE_HEAD: Item = register(TooltipItem("itemTooltip.sarkicism.head_modifier", Formatting.GRAY, Item.Settings().maxCount(32)), "pickaxe_head")
    val AXE_HEAD: Item = register(TooltipItem("itemTooltip.sarkicism.head_modifier", Formatting.GRAY, Item.Settings().maxCount(32)), "axe_head")
    val MACE_HEAD: Item = register(TooltipItem("itemTooltip.sarkicism.head_modifier", Formatting.GRAY, Item.Settings().maxCount(32)), "mace_head")

    fun register(item: Item, id: String?): Item {
        return Registry.register(Registries.ITEM, Identifier.of(Sarkicism.MOD_ID, id), item)
    }

    fun initialize() {
        // Register the item group
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP)

        // Register items to the custom item group
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY)
            .register(ModifyEntries { itemGroup: FabricItemGroupEntries ->
                itemGroup.add(DULL_CRYSTAL)
                itemGroup.add(FAINT_CRYSTAL)
                itemGroup.add(GLITTERING_CRYSTAL)
                itemGroup.add(SHINING_CRYSTAL)

                itemGroup.add(SWORD_HEAD)
                itemGroup.add(PICKAXE_HEAD)
                itemGroup.add(AXE_HEAD)
                itemGroup.add(MACE_HEAD)
            })
    }
}
