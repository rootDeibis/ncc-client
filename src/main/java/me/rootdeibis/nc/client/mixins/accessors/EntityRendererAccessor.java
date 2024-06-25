package me.rootdeibis.nc.client.mixins.accessors;

import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityRenderer.class)
public interface  EntityRendererAccessor {

    @Invoker("setupFog")
    public void setupFog(int p_78468_1_, float partialTicks);
}
