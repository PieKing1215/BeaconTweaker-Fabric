package me.pieking1215.beacontweaker.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(BeaconBlockEntity.class)
public interface BeaconBlockEntityAccessor {
    @Accessor("EFFECTS_BY_LEVEL")
    static void setEFFECTS_BY_LEVEL(StatusEffect[][] EFFECTS_BY_LEVEL) {
        throw new AssertionError();
    }

    @Accessor("EFFECTS")
    static void setEFFECTS(Set<StatusEffect> EFFECTS) {
        throw new AssertionError();
    }
}