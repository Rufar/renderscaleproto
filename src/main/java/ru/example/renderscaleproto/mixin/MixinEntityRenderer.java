package ru.example.renderscaleproto.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.example.renderscaleproto.RenderScaleProto;
import ru.example.renderscaleproto.config.RenderScaleConfig;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    @Inject(method = "renderWorld(FJ)V", at = @At("HEAD"))
    private void rsproto$beginWorld(float partialTicks, long finishTimeNano, CallbackInfo ci) {
        float currentScale = RenderScaleConfig.scale;

        if (currentScale >= 0.99f) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        int targetW = Math.max((int)(mc.displayWidth * currentScale), 1);
        int targetH = Math.max((int)(mc.displayHeight * currentScale), 1);

        if (RenderScaleProto.worldBuffer == null) {
            RenderScaleProto.worldBuffer = new Framebuffer(targetW, targetH, true);
        } else if (RenderScaleProto.worldBuffer.framebufferWidth != targetW ||
                   RenderScaleProto.worldBuffer.framebufferHeight != targetH) {
            RenderScaleProto.worldBuffer.createBindFramebuffer(targetW, targetH);
        }

        Framebuffer fb = RenderScaleProto.worldBuffer;
        fb.framebufferClear();
        fb.bindFramebuffer(true);
        GlStateManager.viewport(0, 0, targetW, targetH);
    }

    @Inject(method = "renderWorld(FJ)V", at = @At("RETURN"))
    private void rsproto$endWorld(float partialTicks, long finishTimeNano, CallbackInfo ci) {
        float currentScale = RenderScaleConfig.scale;
        if (currentScale >= 0.99f || RenderScaleProto.worldBuffer == null) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        Framebuffer fb = RenderScaleProto.worldBuffer;

        fb.unbindFramebuffer();
        mc.getFramebuffer().bindFramebuffer(true);
        GlStateManager.viewport(0, 0, mc.displayWidth, mc.displayHeight);

        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(
            GlStateManager.SourceFactor.SRC_ALPHA,
            GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
            GlStateManager.SourceFactor.ONE,
            GlStateManager.DestFactor.ZERO
        );

        fb.framebufferRender(mc.displayWidth, mc.displayHeight);

        GlStateManager.disableBlend();
    }
}
