package me.rootdeibis.nc.utils.interfaces;

import net.minecraft.client.multiplayer.WorldClient;

public interface IMixinRenderGlobal {
	WorldClient getWorldClient();
}
