package com.dombear.lineagecraft.gui.slots;

import com.dombear.lineagecraft.items.ItemEnchantScrollArmor;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEnchantArmor extends Slot{

	public SlotEnchantArmor(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return (!(itemstack.getItem() instanceof ItemEnchantScrollArmor) && (itemstack.getItem().equals(Items.DIAMOND_BOOTS) ||
				itemstack.getItem().equals(Items.DIAMOND_LEGGINGS) ||
				itemstack.getItem().equals(Items.DIAMOND_CHESTPLATE) ||
				itemstack.getItem().equals(Items.DIAMOND_HELMET)
				));
	}

}
