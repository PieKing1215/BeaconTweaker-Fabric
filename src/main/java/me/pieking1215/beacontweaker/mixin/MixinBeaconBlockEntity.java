package me.pieking1215.beacontweaker.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Mixin(BeaconBlockEntity.class)
public class MixinBeaconBlockEntity {

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(at = @At("TAIL"), method = "<clinit>", cancellable = true)
    private static void clinit(CallbackInfo callback){
        // inject night vision into beacon effects next to strength

        StatusEffect[][] EFFECTS_BY_LEVEL = BeaconBlockEntity.EFFECTS_BY_LEVEL;

        // if only one effect (strength) at tier 3
        if(EFFECTS_BY_LEVEL[2].length == 1){
            // add night vision to tier 3
            EFFECTS_BY_LEVEL[2] = new StatusEffect[]{EFFECTS_BY_LEVEL[2][0], StatusEffects.NIGHT_VISION};
        }
        Set<StatusEffect> EFFECTS = Arrays.stream(EFFECTS_BY_LEVEL).flatMap(Arrays::stream).collect(Collectors.toSet());

        BeaconBlockEntityAccessor.setEFFECTS_BY_LEVEL(EFFECTS_BY_LEVEL);
        BeaconBlockEntityAccessor.setEFFECTS(EFFECTS);
    }

    @ModifyConstant(method = "updateLevel", constant = @Constant(intValue = 4))
    private int modifyMaxLevel(int original){
        return 8;
    }

}
