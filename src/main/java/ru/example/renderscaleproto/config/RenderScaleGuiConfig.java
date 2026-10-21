package ru.example.renderscaleproto.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import ru.example.renderscaleproto.RenderScaleProto;

import java.util.List;

public class RenderScaleGuiConfig extends GuiConfig {
    public RenderScaleGuiConfig(GuiScreen parentScreen) {
        super(
            parentScreen,
            getConfigElements(),
            RenderScaleProto.MODID,
            false,
            false,
            "RenderScale Settings"
        );
    }

    private static List<IConfigElement> getConfigElements() {
        return new ConfigElement(
            RenderScaleConfig.config.getCategory(Configuration.CATEGORY_GENERAL)
        ).getChildElements();
    }
}
