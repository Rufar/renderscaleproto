package ru.example.renderscaleproto.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.example.renderscaleproto.RenderScaleProto;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    @Inject(method = "renderWorld(FJ)V", at = @At("HEAD"))
    private void rsproto$beginWorld(float partialTicks, long finishTimeNano, CallbackInfo ci) {
        Minecraft mc = Minecraft.getMinecraft();
        int w = Math.max(1, (int) (mc.displayWidth * RenderScaleProto.SCALE));
        int h = Math.max(1, (int) (mc.displayHeight * RenderScaleProto.SCALE));

        if (RenderScaleProto.worldBuffer == null) {
            RenderScaleProto.worldBuffer = new Framebuffer(w, h, true);
        } else {
            Framebuffer fb = RenderScaleProto.worldBuffer;
            if (fb.framebufferWidth != w || fb.framebufferHeight != h) {
                fb.createFramebuffer(w, h);
            }
        }

        Framebuffer fb = RenderScaleProto.worldBuffer;
        fb.setFramebufferFilter(GL11.GL_LINEAR);
        fb.bindFramebuffer(true);
    }

    @Inject(method = "renderWorld(FJ)V", at = @At("RETURN"))
    private void rsproto$endWorld(float partialTicks, long finishTimeNano, CallbackInfo ci) {
        Minecraft mc = Minecraft.getMinecraft();
        Framebuffer fb = RenderScaleProto.worldBuffer;
        if (fb == null) {
            return;
        }

        fb.unbindFramebuffer();
        mc.getFramebuffer().bindFramebuffer(true);
        fb.framebufferRender(mc.displayWidth, mc.displayHeight);
    }
}
