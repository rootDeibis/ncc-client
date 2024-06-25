package me.rootdeibis.sixnine.client.mixin.mixins;


import me.rootdeibis.sixnine.client.gui.notification.NotificationManager;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
public class MixinGuiScreen {

    @Inject(method = "drawScreen", at = @At(value = "RETURN"))
    public void onUpdateScreen(CallbackInfo ci) {
        NotificationManager.render();
    }
}
