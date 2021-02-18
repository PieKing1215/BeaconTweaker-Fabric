package me.pieking1215.beacontweaker.mixin.client;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Inject(at = @At("HEAD"), method = "getNightVisionStrength", cancellable = true)
    private static void getNightVisionStrength(LivingEntity livingEntityIn, float entitylivingbaseIn, CallbackInfoReturnable<Float> callback) {
        // these checks are theoretically not necessary as they are checked before this method is called
        if(livingEntityIn == null || livingEntityIn.getStatusEffect(StatusEffects.NIGHT_VISION) == null) {
            callback.setReturnValue(0.0f);
            return;
        }

        int i = livingEntityIn.getStatusEffect(StatusEffects.NIGHT_VISION).getDuration();

        // improved night vision time running out effect
        // desmos.com/calculator/33f12yq3m3
        float v = i > 200 ? 1.0f : (i / 200.0f + (float)Math.sin((i - 200) / 8.0)*0.07f);
        if(v < 0) v = 0;

        // scale brightness depending on level of potion
        switch(livingEntityIn.getStatusEffect(StatusEffects.NIGHT_VISION).getAmplifier()){
            case 0:
                v *= 0.15;
                break;
            case 1:
                v *= 0.5;
                break;
            default:
                // leave 2+ at full
                break;
        }

        callback.setReturnValue(v);
    }
}
