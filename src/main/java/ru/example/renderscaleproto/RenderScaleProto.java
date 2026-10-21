package ru.example.renderscaleproto;

import net.minecraft.client.shader.Framebuffer;
import net.minecraftforge.fml.common.Mod;

@Mod(
    modid = RenderScaleProto.MODID,
    name = RenderScaleProto.NAME,
    version = RenderScaleProto.VERSION,
    clientSideOnly = true,
    guiFactory = "ru.example.renderscaleproto.config.RenderScaleGuiFactory"
)
public class RenderScaleProto {
    public static final String MODID = "renderscaleproto";
    public static final String NAME = "RenderScale Prototype";
    public static final String VERSION = "0.0.1";

    public static Framebuffer worldBuffer;
}
