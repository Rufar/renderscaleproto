package ru.example.renderscaleproto.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import java.io.File;

public class RenderScaleConfig {
    public static Configuration config;
    public static float scale = 0.5f;

    public static void load(File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
        }
        config.load();

        Property scaleProp = config.get(
                Configuration.CATEGORY_GENERAL,
                "scale",
                0.5f,
                "Масштаб разрешения рендера мира (0.1 - 1.0). 0.5 = 50% разрешения экрана."
        );
        scaleProp.setMinValue(0.1);
        scaleProp.setMaxValue(1.0);
        
        // Применяем значение
        scale = (float) scaleProp.getDouble();

        if (config.hasChanged()) {
            config.save();
        }
    }
}
