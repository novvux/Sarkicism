package com.novvux.sarkicism

import com.mojang.authlib.minecraft.client.MinecraftClient
import com.novvux.sarkicism.render.screen.container.UpgradeScreen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW


@Environment(EnvType.CLIENT)
class SarkicismClient: ClientModInitializer {
	companion object {
		lateinit var openMenuKeyBinding: KeyBinding
	}

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

		val openMenuKeyBinding = KeyBindingHelper.registerKeyBinding(KeyBinding(
			"key.sarkicism.menu", // The translation key of the keybinding's name
			InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_J, // The keycode of the key
			"category.sarkicism.sarkicism" // The translation key of the keybinding's category.
		));

		ClientTickEvents.END_CLIENT_TICK.register { client ->
            while (openMenuKeyBinding.wasPressed()) {
				client.setScreen(
					UpgradeScreen(client.player!!)
				);
            }
        }
    }
}
