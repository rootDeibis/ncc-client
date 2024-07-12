package me.rootdeibis.nc.client.mixins;

import me.rootdeibis.nc.NCClient;
import me.rootdeibis.nc.event.events.FontRenderEvent;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FontRenderer.class)
public abstract class FontRendererMixin {

    @Shadow
    public abstract int drawString(String text, float x, float y, int color, boolean dropShadow);

    @Shadow
    protected abstract void resetStyles();

    /**
     * @author
     * @reason
     */
    @Overwrite
    public int drawStringWithShadow(String text, float x, float y, int color) {
        // DISABLE SHADOW IF FPS MOD ENABLED
        return this.drawString(text, (float)x, (float)y, color, true);
    }

    @ModifyVariable(method = "renderString", at = @At("HEAD"), ordinal = 0)
    private String renderString(String text) {
        if(text == null || NCClient.INSTANCE.getEventManager() == null) {
            return text;
        }

        FontRenderEvent event = new FontRenderEvent(text);
        event.call();

        return event.getText();
    }

    @ModifyVariable(method = "getStringWidth", at = @At("HEAD"), ordinal = 0)
    private String getStringWidth(String text) {
        if(text == null ||  NCClient.INSTANCE.getEventManager() == null) {
            return text;
        }

        FontRenderEvent event = new FontRenderEvent(text);
        event.call();

        return event.getText();
    }

    @Inject(method = "drawString(Ljava/lang/String;FFIZ)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;renderString(Ljava/lang/String;FFIZ)I", ordinal = 0, shift = At.Shift.AFTER))
    private void drawString(CallbackInfoReturnable<Integer> ci) {
        this.resetStyles();
    }
}
