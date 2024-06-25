package me.rootdeibis.sixnine.client.mixin.mixins.client;

import net.minecraft.tileentity.TileEntityBeacon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(TileEntityBeacon.class)
public interface TileEntityBeaconAccessor {

    @Accessor("beamSegments")

    public List<TileEntityBeacon.BeamSegment> beamSegments();

    @Accessor("isComplete")
    public boolean isCompleted();

    @Accessor("isComplete")
    public void setCompleted(boolean completed);

    @Accessor("levels")
    public int getLevels();

    @Accessor("levels")
    public void setLevels(int lvels);
    @Accessor("primaryEffect")
    public int getPrimaryEffect();

    @Accessor("secondaryEffect")
    public int getSecundaryEffect();
}
