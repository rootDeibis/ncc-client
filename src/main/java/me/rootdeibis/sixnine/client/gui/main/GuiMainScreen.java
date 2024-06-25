package me.rootdeibis.sixnine.client.gui.main;




import me.guichaguri.betterfps.gui.GuiBetterFpsConfig;
import me.rootdeibis.sixnine.client.NCClient;
import me.rootdeibis.sixnine.client.gui.Notification;
import me.rootdeibis.sixnine.utils.font.FontUtils;
import me.rootdeibis.sixnine.utils.gui.PanoramaRender;
import me.rootdeibis.sixnine.utils.mouse.MouseUtils;
import me.rootdeibis.sixnine.utils.render.RenderUtils;
import me.rootdeibis.sixnine.utils.render.RoundedUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.Sys;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiMainScreen extends GuiScreen {




    private List<GuiMainButton> buttons = new ArrayList<>();


    private PanoramaRender panoramaRender;


    public GuiMainScreen() {
        this.buttons.add(
                new GuiMainButton(0,
                        I18n.format("menu.singleplayer"))
        );

        this.buttons.add(
                new GuiMainButton(1,
                        I18n.format("menu.multiplayer"))
        );

        this.buttons.add(
                new GuiMainButton(2,
                        I18n.format("menu.options"))
        );

        this.buttons.add(
                new GuiMainButton(3,
                        I18n.format("menu.quit"))
        );


        this.panoramaRender = new PanoramaRender(this);

    }

    @Override
    public void initGui() {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());







        GlStateManager.disableAlpha();
        this.panoramaRender.renderSkybox(mouseX, mouseY, partialTicks);

        RenderUtils.drawImage(new ResourceLocation("sixnine/banner.png"), sr.getScaledWidth() / 2  - (200 /2), sr.getScaledHeight() / 2 - 80 - (20 / 2),
                200, 40, 255);


        GlStateManager.enableAlpha();

        GlStateManager.disableTexture2D();


        String cp = "NC Client 1.8.9";
        String author = "Developed by rootDeibis, for 69Club";



        FontUtils.regular_bold18.drawString(cp, 10, this.height - 5 - 16,
                new Color(255,255,255).getRGB());
        FontUtils.regular_bold18.drawString(author, this.width - 10 - FontUtils.regular_bold18.getStringWidth(author), this.height - 5 - 16,
                new Color(255,255,255).getRGB());




        this.drawPlayButtons(sr, mouseX, mouseY, partialTicks);

        if(Minecraft.getMinecraft().thePlayer != null) {

            System.out.println("AKSPODKASKPODKOPAS POROOOT PUTAUISDI0UJAS ");
            GuiInventory.drawEntityOnScreen(0, 0, 1, (float) mouseX, (float)mouseY, Minecraft.getMinecraft().thePlayer);
        }



        super.drawScreen(mouseX, mouseY, partialTicks);


    }


    private void drawPlayButtons(ScaledResolution sr, int mouseX, int mouseY, float partialTicks) {


        int offSet = 16;

        for (GuiMainButton button : this.buttons) {

            if(button.id == 1) {
                button.setTextures("sixnine/ilus-btn.jpg");
            } else {
                button.setTextures("sixnine/button.png");
            }

            if(button.id == 2) {
                button.setValues(
                        sr.getScaledWidth() / 2  - (250 / 2),
                        sr.getScaledHeight() / 2 - 40 - (20 / 2) + (offSet == 20 ? 0 : offSet - 16),
                        120,
                        25
                );
            } else if(button.id == 3) {
                button.setBackgroundColor(255,0,0);
                button.setHoverColor(250,128,114);
                button.setHoverAnimation(false);


                button.setValues(
                        (sr.getScaledWidth() / 2  + (250 / 2)) - (120),
                        sr.getScaledHeight() / 2 - 40 - (20 / 2) + (offSet == 20 ? 0 : offSet - 16),
                        120,
                        25
                );

            }else {
                button.setValues(
                        sr.getScaledWidth() / 2  - (250 / 2),
                        sr.getScaledHeight() / 2 - 40 - (20 / 2) + (offSet == 20 ? 0 : offSet),
                        250,
                        25
                );
                offSet += offSet + (16);

            }



            button.drawButton(mouseX, mouseY);

        }



    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        GuiMainButton clickedButton = this.buttons.stream().filter(btn -> MouseUtils.isInside(mouseX,mouseY, btn.getX(), btn.getY(), btn.getWidth(), btn.getHeight())).findFirst().orElse(null);


        if (clickedButton != null) {

            if (clickedButton.id == 0) {
                mc.displayGuiScreen(new GuiBetterFpsConfig(mc.currentScreen));
            }

            if (clickedButton.id == 1) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiMultiplayer(this));
            }

            if (clickedButton.id == 2) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiOptions(this, Minecraft.getMinecraft().gameSettings));
            }

            if (clickedButton.id == 3) {
                this.mc.shutdown();
            }



        }





    }

    @Override
    public void updateScreen() {
        this.panoramaRender.update();



    }
}
