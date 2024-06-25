package me.rootdeibis.sixnine.client.mixin.mixins;

import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Gui.class)
public interface MixinGui {

    @Accessor("zLevel")
    float getZLevel();
}
