package me.rootdeibis.nc.client.mixins;


import me.rootdeibis.nc.NCClient;
import me.rootdeibis.nc.event.events.DisplayGuiScreenEvent;
import me.rootdeibis.nc.event.events.GameStartEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {


    @Shadow
    public abstract void displayGuiScreen(GuiScreen guiScreenIn);

    /*
        Change Window Title
     */
    @Redirect(method = "createDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V"))
    public void overwriteWindowTitle(String title) {
        Display.setTitle(NCClient.getWindowTitle());
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

        if(guiScreenEvent.isCancelled()) return;

        displayGuiScreen(guiScreenEvent.getGuiScreen());

    }

}
