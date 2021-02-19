package me.pieking1215.beacontweaker.mixin.client;

import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BeaconBlockEntityRenderer.class)
public class MixinBeaconBlockEntityRenderer {

    // I have the values set like this to basically "disable" the transparent beam since it renders weird with shaders

    @ModifyConstant(method = "renderBeaconLightBeam(DDDDJII[F)V", constant = @Constant(doubleValue = 0.2D))
    private static double modifyOpaqueSize(double original){
        return 0.25D; // size of opaque beacon beam (0.2D in vanilla)
    }

    @ModifyConstant(method = "renderBeaconLightBeam(DDDDJII[F)V", constant = @Constant(doubleValue = 0.25D))
    private static double modifyTranslucentSize(double original){
        return 0.1D; // size of translucent beacon beam (0.25D in vanilla)
    }
}
