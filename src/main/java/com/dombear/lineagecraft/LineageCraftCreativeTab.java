package com.dombear.lineagecraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class LineageCraftCreativeTab extends CreativeTabs{
	
	public LineageCraftCreativeTab() {
		super("lineageCraftTab");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(LineageCraftItems.soulshotd);
	}
}
