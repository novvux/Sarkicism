package com.novvux.biomanipulation

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment


@Environment(EnvType.CLIENT)
class BiologicalManipulationClient: ClientModInitializer {
	override fun onInitializeClient() {
		// Initialize client renderers

		//HandledScreens.register(SecretPetrushki.FLESH_BOX_SCREEN_HANDLER, HandledScreens.Provider<ScreenHandler, Screen> { FleshBoxScreen() })

		/*
		HandledScreens.register(
			SecretPetrushki.FLESH_BOX_SCREEN_HANDLER,
			HandledScreens.Provider<ScreenHandler, Screen> { FleshBoxScreen() })
			*/

		// Initialize custom interactions
		//CustomClientInteractions.initialize()
	}
}
