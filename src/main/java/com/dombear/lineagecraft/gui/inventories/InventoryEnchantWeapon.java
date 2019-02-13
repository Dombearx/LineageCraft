package com.dombear.lineagecraft.gui.inventories;

import com.dombear.lineagecraft.items.ItemEnchantScrollArmor;
import com.dombear.lineagecraft.items.ItemEnchantScrollWeapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class InventoryEnchantWeapon implements IInventory{
	
	private String name = "";

	private ItemStack invItem;
	
	public static final int INV_SIZE = 1;
	
	private ItemStack[] inventory = new ItemStack[INV_SIZE];
	
	public InventoryEnchantWeapon(ItemStack stack){
		invItem = stack;
		switch (((ItemEnchantScrollWeapon) stack.getItem()).getType()) {
		case IRON:
			name = "Enchant Weapon Iron-Grade";
			break;
		case DIAMOND:
			name = "Enchant Weapon Diamond-Grade";
			break;
		default:
			name = "Enchant Weapon Unknown-Grade";
			break;
		}
		inventory[0] = ItemStack.EMPTY;
	}
	
	public ItemStack getInvItem(){
		return invItem;
	}
	
	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory[slot];
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount){
		ItemStack stack = getStackInSlot(slot);
		if(!stack.isEmpty()){
			if(stack.getCount() > amount){
				stack = stack.splitStack(amount);
				markDirty();
			} else {
				setInventorySlotContents(slot, ItemStack.EMPTY);
			}
		}
		return stack;
	}

	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
		if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()){
			stack.setCount(getInventoryStackLimit());
		}
		markDirty();
	}
	
	
	@Override
	public boolean hasCustomName() {
		return name.length() > 0;		
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getInventoryStackLimit(){
		return 1;
	}
	
	@Override
	public void markDirty(){
		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (!getStackInSlot(i).isEmpty() && getStackInSlot(i).getCount() == 0) {
				inventory[i] = ItemStack.EMPTY;
			}
		}
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer entityplayer){
		return true;
	}	
	
	//This is done in custom inventory slot
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack){
		return true;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(name);
	}
		
	@Override
	public void setField(int id, int value) {
		
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		inventory[index] = ItemStack.EMPTY;
		return ItemStack.EMPTY;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		
	}
	
		
	@Override
	public int getFieldCount() {
		return 0;
	}
	
	@Override
	public int getField(int id) {
		return 0;
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
	}
	
	@Override
	public void clear() {
		this.setInventorySlotContents(0,ItemStack.EMPTY);
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < inventory.length; i++) {
			if(inventory[i] != ItemStack.EMPTY){
				return false;
			}
		}
		return true;
	}


}
