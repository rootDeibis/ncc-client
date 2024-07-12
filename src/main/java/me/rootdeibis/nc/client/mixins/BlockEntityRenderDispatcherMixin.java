package me.rootdeibis.nc.client.mixins;

import me.rootdeibis.nc.client.mixins.accessors.Cullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;

@Mixin(TileEntityRendererDispatcher.class)
public class BlockEntityRenderDispatcherMixin {

    @Inject(method = "Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;renderTileEntityAt(Lnet/minecraft/tileentity/TileEntity;DDDFI)V", at = @At("HEAD"), cancellable = true)
    public void renderTileEntityAt(TileEntity blockEntity, double p_renderTileEntityAt_2_, double d1,
                                   double d2, float f1, int p_renderTileEntityAt_9_, CallbackInfo info) {
        if (!((Cullable) blockEntity).isForcedVisible() && ((Cullable) blockEntity).isCulled()) {
            info.cancel();
            return;
        }
    }

}