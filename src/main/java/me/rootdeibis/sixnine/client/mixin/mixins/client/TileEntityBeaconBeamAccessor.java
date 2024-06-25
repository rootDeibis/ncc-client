package me.rootdeibis.sixnine.client.mixin.mixins.client;


import net.minecraft.tileentity.TileEntityBeacon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TileEntityBeacon.BeamSegment.class)
public interface TileEntityBeaconBeamAccessor {

    @Invoker("incrementHeight")
    public void incrementHeight();


}
