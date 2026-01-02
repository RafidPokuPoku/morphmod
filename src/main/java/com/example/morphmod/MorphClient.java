package com.example.morphmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class MorphClient implements ClientModInitializer {
    public static KeyBinding openMorphMenuKey;

    @Override
    public void onInitializeClient() {
        openMorphMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.morphmod.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                "category.morphmod"
        ));

        // Client tick to detect key presses
        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMorphMenuKey.wasPressed()) {
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.player != null) {
                    mc.setScreen(new MorphScreen());
                }
            }
        });
    }
}
