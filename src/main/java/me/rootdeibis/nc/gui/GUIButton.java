package me.rootdeibis.nc.gui;


import me.rootdeibis.nc.utils.animation.simple.SimpleAnimation;
import me.rootdeibis.nc.utils.color.ColorTransition;
import me.rootdeibis.nc.utils.color.ColorUtils;
import me.rootdeibis.nc.utils.mouse.MouseUtils;
import me.rootdeibis.nc.utils.render.RoundedUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class GUIButton {

    private String text;
    public final int buttonId;

    private ButtonData currentData;

    private BiConsumer<GUIButton, ButtonData> updateConsumer = (d,s) -> {};

    private ResourceLocation backgroundTextures;
    private ResourceLocation hoverTextures;

    private Color  backgroundColor = new Color(45, 45,4);
    private Color hoverColor = new Color(255,255,255);

    private SimpleAnimation  animation = new SimpleAnimation(255);

    private boolean hoverTransition = false;

    private ColorTransition colorTransition;

    private boolean hasHovered = false;

    private Consumer<GUIButton> onClick = (d) -> {};



    public GUIButton(String text, int buttonId) {
        this.text = text;
        this.buttonId = buttonId;
        this.currentData = new ButtonData();
    }

    public GUIButton setPosAndSize(BiConsumer<GUIButton, ButtonData> updateConsumer){
        this.updateConsumer = updateConsumer;
        return this;
    }

    public GUIButton setBackgroundTextures(ResourceLocation backgroundTextures) {
        this.backgroundTextures = backgroundTextures;
        return this;
    }

    public GUIButton setHoverTextures(ResourceLocation hoverTextures) {
        this.hoverTextures = hoverTextures;
        return this;
    }

    public GUIButton setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public GUIButton setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
        return this;
    }

    public GUIButton setHoverTransition(boolean hoverTransition) {
        this.hoverTransition = hoverTransition;
        return this;
    }

    public GUIButton setOnClick(Consumer<GUIButton> onClick) {
        this.onClick = onClick;

        return this;
    }

    public Consumer<GUIButton> getOnClick() {
        return onClick;
    }

    public boolean is(int mouseX, int mouseY) {
        return MouseUtils.isInside(mouseX, mouseY, this.currentData.getX(), this.currentData.getY(), this.currentData.getWidth(), this.currentData.getHeight());
    }

    public void drawButton(int mouseX, int mouseY) {
        updateConsumer.accept(this, this.currentData);

        int x = this.currentData.getX();
        int y = this.currentData.getY();
        int width = this.currentData.getWidth();
        int height = this.currentData.getHeight();

        boolean hovered = MouseUtils.isInside(mouseX, mouseY, x,y,width,height);



        if(this.backgroundTextures != null) {
            ResourceLocation textures = hovered && this.hoverTransition && this.hoverTextures != null ? this.hoverTextures : this.backgroundTextures;

            Minecraft.getMinecraft().getTextureManager().bindTexture(textures);
            RoundedUtils.drawRoundTextured(x,y,width,height, 1, 255);
        } else {


            if (hoverColor != null && hoverTransition) {

                if(hovered && !hasHovered) {
                    colorTransition = new ColorTransition(250, backgroundColor, hoverColor);
                    hasHovered = true;
                }


                if(!hovered && hasHovered) {
                    colorTransition = new ColorTransition(250, hoverColor, backgroundColor);
                    hasHovered = false;
                }

            }
            Color buttonColor = hoverColor != null && hoverTransition && this.colorTransition != null ? this.colorTransition.getValue() : hovered ? hoverColor : backgroundColor;


            RoundedUtils.drawRound(x,y,width,height, 1,  buttonColor);

            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;

            int textX = x + (width / 2) - fontRenderer.getStringWidth(this.text) / 2;

            fontRenderer.drawString(this.text, textX, y + (height / 2) - (fontRenderer.FONT_HEIGHT / 2), new Color(255,255,255).getRGB(), true);



        }




    }

    public class ButtonData {

        private int x = 0;
        private int y = 0;
        private int width = 0;
        private int height=  0;

        public ButtonData() {}

        public ButtonData setX(int x) {
            this.x = x;
            return this;
        }

        public ButtonData setY(int y) {
            this.y = y;
            return this;
        }

        public ButtonData setWidth(int width) {
            this.width = width;
            return this;
        }

        public ButtonData setHeight(int height) {
            this.height = height;
            return this;
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
    }


}
