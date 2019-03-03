package com.dombear.lineagecraft.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ItemStackHandler implements IItemHandler{

	private InventoryPlayer inventory;
	
	public ItemStackHandler(EntityPlayer player) {
		this.inventory = player.inventory;
	}
	
	public InventoryPlayer getInventory() {
		return inventory;
	}
	
	public void setInventory(InventoryPlayer inventory) {
		this.inventory = inventory;
	}

	@Override
	public int getSlots() {
		return inventory == null ? 0 : inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory.getStackInSlot(slot);
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if(!simulate){
			inventory.setInventorySlotContents(slot, stack);			
		}
		return null;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return inventory.getStackInSlot(slot);
	}

	@Override
	public int getSlotLimit(int slot) {
		return inventory.getStackInSlot(slot).getMaxStackSize();
	}

}
