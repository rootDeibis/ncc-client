package me.rootdeibis.sixnine.client.gui;

import me.rootdeibis.nc.utils.TimerUtils;
import me.rootdeibis.nc.utils.animation.normal.Animation;
import me.rootdeibis.nc.utils.animation.normal.Direction;
import me.rootdeibis.nc.utils.animation.normal.impl.DecelerateAnimation;
import me.rootdeibis.nc.utils.render.RoundedUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.util.Color;

public class Notification {

    private final String text;

    private Animation animation;

    private TimerUtils timer = new TimerUtils();

    private FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
    public Notification(String text) {
        this.text = text;

    }

    public void show() {
        timer.reset();
    }

    public boolean isShown() {
        return !animation.isDone(Direction.BACKWARDS);
    }

    public void doRender() {




        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());




        int width = 150 + (fontRenderer.getStringWidth(this.text));
        int height = 40;

        int posX = sr.getScaledWidth() - width - 10;
        int posY = 10;

        if(this.animation == null) {
            animation = new DecelerateAnimation(500, posY);

        }

        if(timer.delay(5000)) {
            animation = new DecelerateAnimation(500, height - 10);
            animation.setDirection(Direction.BACKWARDS);
        }


        GlStateManager.pushMatrix();

        int offSet = animation.getDirection() == Direction.FORWARDS && animation.isDone() ? posY : (int) animation.getValue();


        RoundedUtils.drawRound(posX,offSet,width, height, 2,new Color(0,0,0));
        fontRenderer.drawString(this.text, posX + 10, offSet + (height / 2), new java.awt.Color(255,255,255).getRGB());


        GlStateManager.popMatrix();
        GlStateManager.enableBlend();







    }
}
