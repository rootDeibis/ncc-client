package me.rootdeibis.nc.client.menu;

import me.rootdeibis.nc.gui.GUIButton;
import me.rootdeibis.nc.gui.GUIScreen;
import me.rootdeibis.nc.utils.gui.PanoramaRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.function.BiConsumer;


public class GUINCMainMenu extends GUIScreen {


    private final PanoramaRender panoramaRender;

    private final ResourceLocation buttonTextures = new ResourceLocation("sixnine/button.png");
    public GUINCMainMenu() {
        this.panoramaRender =  new PanoramaRender(this);

        int buttonWidth = 200;
        int buttonHeight = 20;
        int buttonOffset = 10;

        GUIButton sigleplayerButton = new GUIButton(I18n.format("menu.singleplayer"), 0);


        this.addButton(sigleplayerButton);

        GUIButton multiplayerButton = new GUIButton(I18n.format("menu.multiplayer"), 1);

        this.addButton(multiplayerButton);

        for (int i = 0; i < this.getButtons().size(); i++) {
            GUIButton btn = this.getButtons().get(i);

            btn.setHoverTransition(true);
            btn.setBackgroundColor(new Color(61,63,73));
            btn.setHoverColor(new Color(61,63,73));

            final int buttonOffsetMultiplier = i;
            btn.setPosAndSize((b, data) -> {
                ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
                data.setWidth(buttonWidth)
                        .setHeight(buttonHeight)
                        .setX(sr.getScaledWidth() / 2 - (buttonWidth / 2))
                        .setY(sr.getScaledHeight() / 2 - (buttonHeight / 2) + ((buttonHeight + buttonOffset) * buttonOffsetMultiplier));
            });
        }

    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        this.panoramaRender.renderSkybox(mouseX, mouseY, partialTicks);


        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateScreen() {
        panoramaRender.update();
        super.updateScreen();
    }

}
