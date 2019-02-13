package com.dombear.lineagecraft.gui.containers;

import com.dombear.lineagecraft.gui.inventories.InventoryEnchantArmor;
import com.dombear.lineagecraft.gui.inventories.InventoryEnchantWeapon;
import com.dombear.lineagecraft.gui.slots.SlotEnchantWeapon;
import com.dombear.lineagecraft.items.ItemEnchantScrollArmor;
import com.dombear.lineagecraft.items.ItemEnchantScrollWeapon;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEnchantWeapon extends Container{
	
	public final InventoryEnchantWeapon inventory;
	
	private static final int INV_START = InventoryEnchantArmor.INV_SIZE, INV_END = INV_START+26,
			HOTBAR_START = INV_END+1, HOTBAR_END = HOTBAR_START+8;
	
	
	public ContainerEnchantWeapon(InventoryPlayer playerInv, InventoryEnchantWeapon inventoryEnchantWeapon) {
		
		this.inventory = inventoryEnchantWeapon;
		
		ItemEnchantScrollWeapon inventoryItem = (ItemEnchantScrollWeapon) this.inventory.getInvItem().getItem();
		// Slot 0
	    this.addSlotToContainer(new SlotEnchantWeapon(this.inventory, 0, 80, 35, inventoryItem.getType()));
	

	    // Player Inventory, Slot 9-35, Slot IDs 9-35
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18)); 
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 36-44
	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
	    }
	    
	    
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player){
		return inventory.isUsableByPlayer(player);
	}
	
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int index){
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);


		if (slot != null && slot.getHasStack() && Minecraft.getMinecraft().world.isRemote)
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// If item is in our custom Inventory or armor slot
			if (index < INV_START)
			{
				// try to place in player inventory / action bar
				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END+1, true))
				{
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			// Item is in inventory / hotbar, try to place in custom inventory or armor slots
			else
			{
				
				// Check that the item is the right type
				if (itemstack1.getItem() instanceof ItemEnchantScrollWeapon)
				{
					// Try to merge into your custom inventory slots
					// We use 'InventoryItem.INV_SIZE' instead of INV_START just in case
					// you also add armor or other custom slots
					if (!this.mergeItemStack(itemstack1, 0, inventory.INV_SIZE, false))
					{
						return ItemStack.EMPTY;
					}
				}
				if (index >= INV_START)
				{
					// place in custom inventory
					if (!this.mergeItemStack(itemstack1, 0, INV_START, false))
					{
						return ItemStack.EMPTY;
					}
				}
			}
			

			if (itemstack1.getCount() == 0)
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount())
			{
				return ItemStack.EMPTY;
			}

			//slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
			slot.onSlotChanged();
		}

		return itemstack;
	}
	@Override
	protected boolean mergeItemStack(ItemStack stack, int start, int end, boolean backwards){
		boolean flag1 = false;
		int k = (backwards ? end - 1 : start);
		Slot slot;
		ItemStack itemstack1;

		if (stack.isStackable())
		{
			while (stack.getCount() > 0 && (!backwards && k < end || backwards && k >= start))
			{
				slot = (Slot) inventorySlots.get(k);
				itemstack1 = slot.getStack();

				if (!slot.isItemValid(stack)) {
					k += (backwards ? -1 : 1);
					continue;
				}

				if (!itemstack1.isEmpty() && itemstack1.getItem() == stack.getItem() &&
						(!stack.getHasSubtypes() || stack.getItemDamage() == itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(stack, itemstack1))
				{
					int l = itemstack1.getCount() + stack.getCount();

					if (l <= stack.getMaxStackSize() && l <= slot.getSlotStackLimit()) {
						stack.setCount(0);
						itemstack1.setCount(l);
						inventory.markDirty();
						flag1 = true;
					} else if (itemstack1.getCount() < stack.getMaxStackSize() && l < slot.getSlotStackLimit()) {
						stack.setCount(stack.getCount() - stack.getMaxStackSize() - itemstack1.getCount());
						itemstack1.setCount(stack.getMaxStackSize());
						inventory.markDirty();
						flag1 = true;
					}
				}

				k += (backwards ? -1 : 1);
			}
		}
		if (stack.getCount() > 0)
		{
			k = (backwards ? end - 1 : start);
			while (!backwards && k < end || backwards && k >= start) {
				slot = (Slot) inventorySlots.get(k);
				itemstack1 = slot.getStack();

				if (!slot.isItemValid(stack)) {
					k += (backwards ? -1 : 1);
					continue;
				}

				if (itemstack1.isEmpty()) {
					int l = stack.getCount();
					if (l <= slot.getSlotStackLimit()) {
						slot.putStack(stack.copy());
						stack.setCount(0);
						inventory.markDirty();
						flag1 = true;
						break;
					} else {
						putStackInSlot(k, new ItemStack(stack.getItem(), slot.getSlotStackLimit(), stack.getItemDamage()));
						stack.setCount(stack.getCount() - slot.getSlotStackLimit());
						inventory.markDirty();
						flag1 = true;
					}
				}

				k += (backwards ? -1 : 1);
			}
		}

		return flag1;
	}
	
	
	@Override
	public void onContainerClosed(EntityPlayer player)
    {
        InventoryPlayer inventoryplayer = player.inventory;

        if (!inventoryplayer.getItemStack().isEmpty())
        {
            player.dropItem(inventoryplayer.getItemStack(), false);
            inventoryplayer.setItemStack(ItemStack.EMPTY);
        }        
        if(!this.inventory.getStackInSlot(0).isEmpty() && !player.getEntityWorld().isRemote){
        	EntityItem entityitem = new EntityItem(player.world, player.posX, player.posY+1, player.posZ, this.inventory.getStackInSlot(0));
    		entityitem.setPickupDelay(30);
    		player.world.spawnEntity(entityitem);	
        }
        
        
    }
    


}
