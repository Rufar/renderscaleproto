package ru.example.renderscaleproto.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.example.renderscaleproto.RenderScaleProto;

@Config(modid = RenderScaleProto.MODID, name = RenderScaleProto.MODID)
public class RenderScaleConfig {

    @Config.Name("scale")
    @Config.Comment("Масштаб разрешения рендера мира (0.1 - 1.0). 0.5 = 50% разрешения экрана.")
    @Config.RangeDouble(min = 0.1, max = 1.0)
    @Config.SlidingOption
    public static float scale = 0.5f;

    @Mod.EventBusSubscriber(modid = RenderScaleProto.MODID)
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(RenderScaleProto.MODID)) {
                ConfigManager.sync(RenderScaleProto.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
