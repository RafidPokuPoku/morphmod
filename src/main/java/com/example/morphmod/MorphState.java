package com.example.morphmod;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionEffectInstance;
import net.minecraft.potion.PotionTypes;

public class MorphState {
    private static String currentMorphId = null;

    public static void setMorph(String id) {
        currentMorphId = id;
        System.out.println("[MorphMod] Morph set to: " + id);
    }

    public static String getMorph() {
        return currentMorphId;
    }

    public static void clearMorph(ClientPlayerEntity player) {
        currentMorphId = null;
        if (player != null) {
            player.removeStatusEffect(net.minecraft.entity.effect.StatusEffects.INVISIBILITY);
            player.removeStatusEffect(net.minecraft.entity.effect.StatusEffects.SPEED);
            player.sendMessage(net.minecraft.text.Text.of("Morph cleared."), true);
        }
    }

    // Prototype morph application: gives player effects and plays sound to create an "immersion" feel.
    public static void applyPrototypeMorph(ClientPlayerEntity player, String mobId) {
        if (player == null) return;
        // Basic mapping: some mobs give speed, jump boost, invisibility, etc.
        player.sendMessage(net.minecraft.text.Text.of("You morphed into " + mobId), true);

        // Apply a couple of effects depending on mob family (very approximate)
        if (mobId.contains("skeleton") || mobId.contains("zombie")) {
            player.addStatusEffect(new PotionEffectInstance(net.minecraft.entity.effect.StatusEffects.STRENGTH, 20*60, 0, false, false, true));
        } else if (mobId.contains("cow") || mobId.contains("pig") || mobId.contains("sheep")) {
            player.addStatusEffect(new PotionEffectInstance(net.minecraft.entity.effect.StatusEffects.SPEED, 20*60, 0, false, false, true));
        } else if (mobId.contains("chicken") || mobId.contains("rabbit")) {
            player.addStatusEffect(new PotionEffectInstance(net.minecraft.entity.effect.StatusEffects.JUMP_BOOST, 20*60, 0, false, false, true));
        } else if (mobId.contains("creeper")) {
            // subtle: give slowfall so explosions feel safer (prototype)
            player.addStatusEffect(new PotionEffectInstance(net.minecraft.entity.effect.StatusEffects.SLOW_FALLING, 20*60, 0, false, false, true));
        } else if (mobId.contains("enderman")) {
            player.addStatusEffect(new PotionEffectInstance(net.minecraft.entity.effect.StatusEffects.INVISIBILITY, 20*60, 0, false, false, true));
        } else {
            player.addStatusEffect(new PotionEffectInstance(net.minecraft.entity.effect.StatusEffects.SPEED, 20*60, 0, false, false, true));
        }

        // Play a mob sound for feedback (client-side)
        SoundEvent sound = SoundEvents.ENTITY_PLAYER_LEVELUP;
        player.playSound(sound, 1.0F, 1.0F);
    }
}
