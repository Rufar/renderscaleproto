package ru.example.renderscaleproto;

import net.minecraft.client.shader.Framebuffer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.example.renderscaleproto.config.RenderScaleConfig;

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

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        RenderScaleConfig.load(event.getSuggestedConfigurationFile());
    }
}
