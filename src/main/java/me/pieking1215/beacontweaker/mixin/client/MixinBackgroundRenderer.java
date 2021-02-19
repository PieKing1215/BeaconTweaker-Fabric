package me.pieking1215.beacontweaker.mixin.client;

import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BackgroundRenderer.class)
public class MixinBackgroundRenderer {

    @Redirect(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;getNightVisionStrength(Lnet/minecraft/entity/LivingEntity;F)F"))
    private  float getNightVisionStrength(GameRenderer gameRenderer, LivingEntity livingEntity, float f){
        // make the fog not get super bright with night vision
        // (this lessens the harsh sky transition on the horizon with night vision)
        return gameRenderer.getNightVisionStrength(livingEntity, f) * 0.25f;
    }

}
