package com.novvux.biomanipulation

import com.novvux.biomanipulation.block.ModBlocks
import com.novvux.biomanipulation.entity.ModEntities
import com.novvux.biomanipulation.item.ModItems
import com.novvux.petrushka.screen.FleshBoxScreenHandler
import io.wispforest.accessories.Accessories.MODID
import net.fabricmc.api.ModInitializer
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory


class BiologicalManipulation: ModInitializer {
	companion object {
		fun of(path: String?): Identifier {
			return Identifier.of(MODID, path)
		}

		const val MOD_ID: String = "biomanipulation"
		private val logger = LoggerFactory.getLogger("biomanipulation")

		val FLESH_BOX_SCREEN_HANDLER: ScreenHandlerType<FleshBoxScreenHandler>? = null
		//val FLESH_BOX_SCREEN_HANDLER: ScreenHandlerType<FleshBoxScreenHandler> = ScreenHandlerRegistry.registerSimple(ModBlocks.FLESH_BOX) { FleshBoxScreenHandler() }
	}

	// Initialization all parts of mod
	override fun onInitialize() {
		ModItems.initialize()
		ModBlocks.initialize()
		ModEntities.initialize()


		logger.info("You like body modifications, don't you?")
	}
}