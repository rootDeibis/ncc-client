package me.rootdeibis.sixnine.client.mixin.mixins;

import me.rootdeibis.sixnine.client.gui.Notification;
import me.rootdeibis.sixnine.client.gui.main.GuiMainScreen;
import me.rootdeibis.sixnine.client.gui.notification.NotificationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
public class MixinGuiScreen {

    @Inject(method = "drawScreen", at = @At(value = "INVOKE", shift = At.Shift.AFTER))
    public void onUpdateScreen(CallbackInfo ci) {

        if(Minecraft.getMinecraft().currentScreen instanceof GuiMultiplayer) {
            //NotificationManager.render();
        }

    }
}
