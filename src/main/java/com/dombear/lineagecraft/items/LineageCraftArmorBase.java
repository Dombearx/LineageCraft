package com.dombear.lineagecraft.items;

import com.dombear.lineagecraft.LineageCraft;
import com.dombear.lineagecraft.init.LineageCraftItems;
import com.dombear.lineagecraft.utils.IHasModel;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class LineageCraftArmorBase extends ItemArmor implements IHasModel {

	public LineageCraftArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		
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
