package com.dombear.lineagecraft.gui.slots;

import com.dombear.lineagecraft.items.ItemEnchantScrollArmor;
import com.dombear.lineagecraft.items.ItemEnchantScrollWeapon;
import com.dombear.lineagecraft.utils.LineageCraftTypes.Type;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEnchantArmor extends Slot{

	private Type type;
	
	public SlotEnchantArmor(IInventory inventoryIn, int index, int xPosition, int yPosition, Type type) {
		super(inventoryIn, index, xPosition, yPosition);
		this.type = type;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		if(type == Type.DIAMOND){
			return (!(itemstack.getItem() instanceof ItemEnchantScrollArmor) && (itemstack.getItem().equals(Items.DIAMOND_BOOTS) ||
					itemstack.getItem().equals(Items.DIAMOND_LEGGINGS) ||
					itemstack.getItem().equals(Items.DIAMOND_CHESTPLATE) ||
					itemstack.getItem().equals(Items.DIAMOND_HELMET)
					));		
		}
		if(type == Type.IRON){
			return (!(itemstack.getItem() instanceof ItemEnchantScrollArmor) && (itemstack.getItem().equals(Items.IRON_BOOTS) ||
					itemstack.getItem().equals(Items.IRON_LEGGINGS) ||
					itemstack.getItem().equals(Items.IRON_CHESTPLATE) ||
					itemstack.getItem().equals(Items.IRON_HELMET)
					));		
		}
		return false;
	}
}
