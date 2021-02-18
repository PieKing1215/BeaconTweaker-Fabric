package me.pieking1215.beacontweaker.mixin.client;

import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BeaconBlockEntityRenderer.class)
public class MixinBeaconBlockEntityRenderer {

    // I have the values set like this to basically "disable" the transparent beam since it renders weird with shaders

    @ModifyConstant(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;FJII[F)V", constant = @Constant(floatValue = 0.2f))
    private static float modifyOpaqueSize(float original){
        return 0.25f; // size of opaque beacon beam (0.2f in vanilla)
    }

    @ModifyConstant(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;FJII[F)V", constant = @Constant(floatValue = 0.25f))
    private static float modifyTranslucentSize(float original){
        return 0.1f; // size of translucent beacon beam (0.25f in vanilla)
    }
}
