package com.example.morphmod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;

public class MorphScreen extends Screen {
    protected MorphScreen() {
        super(Text.translatable("gui.morphmod.title"));
    }

    @Override
    protected void init() {
        super.init();
        // List of common mobs to morph into
        List<EntityType<?>> mobs = new ArrayList<>();
        mobs.add(EntityType.COW);
        mobs.add(EntityType.PIG);
        mobs.add(EntityType.SHEEP);
        mobs.add(EntityType.CHICKEN);
        mobs.add(EntityType.SQUID);
        mobs.add(EntityType.WOLF);
        mobs.add(EntityType.ZOMBIE);
        mobs.add(EntityType.SKELETON);
        mobs.add(EntityType.CREEPER);
        mobs.add(EntityType.ENDERMAN);
        mobs.add(EntityType.SPIDER);
        mobs.add(EntityType.HORSE);

        int y = this.height / 4;
        int btnWidth = 150;
        int btnPerRow = Math.max(1, this.width / (btnWidth + 10));
        int i = 0;
        for (EntityType<?> mob : mobs) {
            String id = Registries.ENTITY_TYPE.getId(mob).toString();
            int x = 10 + (i % btnPerRow) * (btnWidth + 10);
            int yy = y + (i / btnPerRow) * 24;
            this.addDrawableChild(new ButtonWidget(x, yy, btnWidth, 20, Text.of(id), button -> {
                MorphState.setMorph(id);
                // Apply simple effects for prototype
                MorphState.applyPrototypeMorph(MinecraftClient.getInstance().player, id);
                this.onClose();
            }));
            i++;
        }

        this.addDrawableChild(new ButtonWidget(this.width/2 - 100, this.height - 40, 200, 20, Text.of("Clear Morph"), button -> {
            MorphState.clearMorph(MinecraftClient.getInstance().player);
            this.onClose();
        }));
    }

    @Override
    public boolean shouldPause() { return false; }

    @Override
    public void renderBackground() {
        super.renderBackground();
    }

}
