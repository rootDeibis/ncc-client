package me.rootdeibis.sixnine.client.gui;

import me.rootdeibis.nc.utils.animation.normal.Animation;

import me.rootdeibis.nc.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


import java.awt.*;


public class GuiSplashScreen extends GuiScreen {
    private static float progress = 0;
    private static float maxProgress = 17;

    private static Animation animation;


    public static void update() {
        if(Minecraft.getMinecraft().gameSettings == null || Minecraft.getMinecraft().getTextureManager() == null) {
            return;
        }
        onRender(Minecraft.getMinecraft().getTextureManager());


    }
    public static void onRender(TextureManager textureManagerInstance) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int i = sr.getScaleFactor();




        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, (double)sr.getScaledWidth(), (double)sr.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        GlStateManager.resetColor();
        GlStateManager.color(4, 4, 14);


        RenderUtils.drawImage(new ResourceLocation("sixnine/splash_screen.png"),0,0 , sr.getScaledWidth(), sr.getScaledHeight(), 255);


        drawProgress(textureManagerInstance);



        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);




        Minecraft.getMinecraft().updateDisplay();
    }

    private static void drawProgress(TextureManager textureManagerInstance) {
        if(Minecraft.getMinecraft().gameSettings == null || Minecraft.getMinecraft().getTextureManager() == null) {
            return;
        }

       if (animation != null) {
           animation.reset();
       }

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int addX = 65;
        int addY = 2;




    }

    public static void setProgress(int newProgress) {
        progress = newProgress;
        update();
    }

    private static org.lwjgl.util.Color getBackgroundColor(int id) {
        Color color = new Color(4, 4, 14);





        return new org.lwjgl.util.Color(color.getRed(), color.getGreen(), color.getBlue());
    }
}
