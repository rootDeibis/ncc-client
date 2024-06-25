package me.rootdeibis.nc.client.mixins.accessors;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TileEntityHopper.class)
public interface TileEntityHopperAccessor {

    @Invoker("isInventoryEmpty")
    boolean isInventoryEmpty(IInventory inventoryIn, EnumFacing side);

    @Invoker("pullItemFromSlot")
    boolean pullItemFromSlot(IHopper hopper, IInventory inventoryIn, int index, EnumFacing direction);

    @Accessor("transferCooldown")
    int transferCooldown();

    @Accessor("transferCooldown")
    void setTransferCooldown(int transferCooldown);
}
