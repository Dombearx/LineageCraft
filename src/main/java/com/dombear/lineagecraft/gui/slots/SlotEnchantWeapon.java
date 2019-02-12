package com.dombear.lineagecraft.gui.slots;

import com.dombear.lineagecraft.items.ItemEnchantScrollArmor;
import com.dombear.lineagecraft.items.ItemEnchantScrollWeapon;
import com.dombear.lineagecraft.utils.LineageCraftTypes.Type;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEnchantWeapon extends Slot{
	
	private Type type;

	public SlotEnchantWeapon(IInventory inventoryIn, int index, int xPosition, int yPosition, Type type) {
		super(inventoryIn, index, xPosition, yPosition);
		this.type = type;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		if(type == Type.DIAMOND){
			return (!(itemstack.getItem() instanceof ItemEnchantScrollWeapon) && (
					itemstack.getItem().equals(Items.DIAMOND_SWORD) ||
					itemstack.getItem().equals(Items.DIAMOND_AXE)
					));			
		}
		if(type == Type.IRON){
			return (!(itemstack.getItem() instanceof ItemEnchantScrollWeapon) && (
					itemstack.getItem().equals(Items.IRON_SWORD) ||
					itemstack.getItem().equals(Items.IRON_AXE) ||
					itemstack.getItem().equals(Items.BOW)
					));			
		}

		return false;
	}

}
