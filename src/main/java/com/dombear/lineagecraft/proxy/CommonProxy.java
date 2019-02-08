package com.dombear.lineagecraft.proxy;

import net.minecraft.item.Item;

public interface CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id);
	public void registerGuis();
	
}
