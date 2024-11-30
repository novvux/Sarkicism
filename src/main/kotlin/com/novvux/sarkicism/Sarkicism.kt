package com.novvux.sarkicism

import com.novvux.sarkicism.block.ModBlocks
import com.novvux.sarkicism.entity.ModEntities
import com.novvux.sarkicism.item.ModItems
import com.novvux.sarkicism.screen.FleshBoxScreenHandler
import com.novvux.sarkicism.util.AttributeUtils
import net.fabricmc.api.ModInitializer
import net.minecraft.screen.ScreenHandlerType
import org.slf4j.LoggerFactory
import net.minecraft.entity.player.PlayerEntity
import net.fabricmc.fabric.api.event.player.UseEntityCallback
import net.minecraft.text.Text
import net.minecraft.util.ActionResult


class Sarkicism: ModInitializer {
	companion object {
		const val MOD_ID: String = "sarkicism"
		private val logger = LoggerFactory.getLogger("sarkicism")

		val FLESH_BOX_SCREEN_HANDLER: ScreenHandlerType<FleshBoxScreenHandler>? = null
		//val FLESH_BOX_SCREEN_HANDLER: ScreenHandlerType<FleshBoxScreenHandler> = ScreenHandlerRegistry.registerSimple(ModBlocks.FLESH_BOX) { FleshBoxScreenHandler() }

		/*fun of(path: String?): Identifier {
			return Identifier.of(MOD_ID, path)
		}*/
	}

	// Initialization all parts of mod
	override fun onInitialize() {
		ModItems.initialize()
		ModBlocks.initialize()
		ModEntities.initialize()

		UseEntityCallback.EVENT.register { player, world, hand, entity, hitResult ->
			if (!world.isClient && player is PlayerEntity) {
				// Изменяем атрибуты игрока
				//AttributeUtils.changePlayerAttributes(player)
				player.sendMessage(Text.of("Ваши атрибуты изменены!"), false)
				ActionResult.SUCCESS
			} else {
				ActionResult.PASS
			}
		}

		logger.info("You like body modifications, don't you?")
	}
}