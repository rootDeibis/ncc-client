package me.rootdeibis.sixnine.client.gui.main;



import me.rootdeibis.nc.utils.animation.normal.impl.EaseInOutQuad;


import me.rootdeibis.nc.utils.mouse.MouseUtils;
import me.rootdeibis.nc.utils.render.RoundedUtils;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;

import net.minecraft.util.ResourceLocation;

import java.awt.*;


public class GuiMainButton{

    public final int id;
    private String name;

    private  int x;
    private  int y;

    private  int width;
    private  int height;

    private int[] backgroundColor = {41,41,41};
    private int[] hoverColor = {121,107,255};

    private boolean hoverAnimation = true;

    private String textures;

    EaseInOutQuad easeInOutQuad = new EaseInOutQuad(250, 255);

    public GuiMainButton(int id, String name, int x, int y, int width, int height) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public GuiMainButton(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setValues( int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void drawButton(int mouseX, int mouseY) {

        boolean hovered = this.isInside(mouseX, mouseY);


        GlStateManager.pushMatrix();

        if(!hovered) {
            easeInOutQuad.reset();
        }

        Color backgroundColor = !hovered ? new Color(this.backgroundColor[0],this.backgroundColor[1],this.backgroundColor[2], 80) :
                new Color(this.hoverColor[0], this.hoverColor[1], this.hoverColor[2], this.hoverAnimation ? (int) easeInOutQuad.getValue() : 255 );


        if(textures != null) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(this.textures));

            RoundedUtils.drawRoundTextured(this.x,this.y, this.width - 1, this.height - 1, 5, 255);
            RoundedUtils.drawRoundOutline(this.x, this.y, this.width, this.height, 2, 2, new Color(0, 0, 0, 0), backgroundColor);

        } else {
            RoundedUtils.drawRound(this.x , this.y, this.width, this.height, 1, backgroundColor);
        }




        GlStateManager.popMatrix();


        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;

        int stringWidth = fontRenderer.getStringWidth(this.name);


        int posX = this.x + (this.width / 2)- (stringWidth / 2);

        Minecraft.getMinecraft().fontRendererObj.drawString(this.getName(), posX, this.y + (this.height / 2) - 2,
                new java.awt.Color(255,255,255).getRGB());



    }


    public boolean isInside(int mouseX, int mouseY) {
        return MouseUtils.isInside(mouseX, mouseY,this.x, this.y, this.width, this.height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public void setBackgroundColor(int... backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setHoverColor(int... hoverColor) {
        this.hoverColor = hoverColor;
    }


    public void setTextures(String textures) {
        this.textures = textures;
    }

    public void setHoverAnimation(boolean hoverAnimation) {
        this.hoverAnimation = hoverAnimation;
    }
}
