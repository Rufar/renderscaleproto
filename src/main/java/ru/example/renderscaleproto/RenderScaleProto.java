package ru.example.renderscaleproto;

import net.minecraft.client.shader.Framebuffer;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "renderscaleproto", name = "RenderScale Prototype", version = "0.0.1", clientSideOnly = true)
public class RenderScaleProto {

    /** Доля окна по каждой стороне: 0.75 означает, что мир рисуется в 75% размера. */
    public static final float SCALE = 0.75f;

    /** Буфер, в который рисуется мир. Создаётся миксином при первом кадре. */
    public static Framebuffer worldBuffer;
}
