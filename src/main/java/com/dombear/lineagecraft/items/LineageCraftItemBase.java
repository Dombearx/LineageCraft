package com.dombear.lineagecraft.items;

import com.dombear.lineagecraft.LineageCraft;
import com.dombear.lineagecraft.init.LineageCraftItems;
import com.dombear.lineagecraft.utils.IHasModel;

import net.minecraft.item.Item;

public class LineageCraftItemBase extends Item implements IHasModel{

	public LineageCraftItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(LineageCraft.CREATIVE_TAB);
		
		LineageCraftItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		LineageCraft.proxy.registerItemRenderer(this, 0, "inventory");
		
	}

}
