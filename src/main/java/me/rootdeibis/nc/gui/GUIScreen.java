package me.rootdeibis.nc.gui;

import com.google.common.collect.Lists;
import me.rootdeibis.nc.utils.mouse.MouseUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class GUIScreen extends GuiScreen {

    private List<GUIButton> buttons = Lists.newArrayList();

    public GUIScreen() {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {


        // Draw Buttons
        for(GUIButton btn : buttons) {
            btn.drawButton(mouseX,mouseY);
        }


        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
       GUIButton clickedButton = this.buttons.stream().filter(btn ->  btn.is(mouseX,mouseY)).findFirst().orElse(null);

       if(clickedButton != null) {
            clickedButton.getOnClick().accept(clickedButton);
       }
    }

    public void addButton(int id, String text, Function<GUIButton, GUIButton> button) {
        this.buttons.add(button.apply(new GUIButton(text, id)));

    }

    public void addButton(GUIButton btn) {
        this.buttons.add(btn);
    }


    public List<GUIButton> getButtons() {
        return buttons;
    }
}
