package me.rootdeibis.nc.client.mixins;


import me.rootdeibis.nc.NCClient;
import me.rootdeibis.nc.event.events.DisplayGuiScreenEvent;
import me.rootdeibis.nc.event.events.GameStartEvent;
import me.rootdeibis.sixnine.client.gui.GuiSplashScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {


    @Shadow
    public abstract void displayGuiScreen(GuiScreen guiScreenIn);

    @Shadow
    public DefaultResourcePack mcDefaultResourcePack;

    @Shadow
    abstract ByteBuffer readImageToBuffer(InputStream imageStream);


    @Inject(method = "<init>", at = @At("RETURN"))
    public void onMinecraftInit(CallbackInfo info){
        new NCClient(Minecraft.getMinecraft());
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void setWindowIcon() {
        Util.EnumOS util$enumos = Util.getOSType();

        if (util$enumos != Util.EnumOS.OSX) {
            InputStream inputstream = null;
            InputStream inputstream1 = null;

            try {
                inputstream = this.mcDefaultResourcePack.getInputStreamAssets(new ResourceLocation("sixnine/32.png"));
                inputstream1 = this.mcDefaultResourcePack.getInputStreamAssets(new ResourceLocation("sixnine/32.png"));

                if (inputstream != null && inputstream1 != null) {
                    Display.setIcon(new ByteBuffer[]{this.readImageToBuffer(inputstream)});
                }
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            } finally {
                IOUtils.closeQuietly(inputstream);
                IOUtils.closeQuietly(inputstream1);
            }
        }
    }

    /*
        Change Window Title
     */
    @Redirect(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
    public void createDisplay(String title) {
        Display.setTitle(NCClient.getWindowTitle());
    }


    /**
     * @author rootDeibis
     * @reason custom splash screen
     */
    @Overwrite
    private void drawSplashScreen(TextureManager textureManager) {
        GuiSplashScreen.onRender(textureManager);
    }

    /*
        Game Start Event Dispatch
     */

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;", shift = At.Shift.AFTER))
    public void gameStart(CallbackInfo ci){
        new GameStartEvent().call();
    }

    /*
        Display Gui Screen Event Dispatch
     */

    @Inject(method = "displayGuiScreen", at = @At("RETURN"))
    public void customDisplayGuiScreen(GuiScreen guiScreen, CallbackInfo ci) {
        DisplayGuiScreenEvent guiScreenEvent = new DisplayGuiScreenEvent(guiScreen);

        guiScreenEvent.call();

        if(guiScreenEvent.isCancelled() || Minecraft.getMinecraft().currentScreen == guiScreenEvent.getGuiScreen()) return;


        displayGuiScreen(guiScreenEvent.getGuiScreen());

    }

}
