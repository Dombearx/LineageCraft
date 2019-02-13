package com.dombear.lineagecraft.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;

/**
 * Contains enchantment position in taglist and level
 * 
 * @author Dombear
 * 
 */
public class EnchantmentOnItem implements Comparable<EnchantmentOnItem>{
	
	private Enchantment enchantment;
	private int enchantmentId;
	private int enchantmentPosition;
	private int enchantmentLevel;
	
	public EnchantmentOnItem(ItemStack itemstack, Enchantment enchantment){
		this.enchantment = enchantment;
		this.enchantmentId = Enchantment.getEnchantmentID(enchantment);
		this.enchantmentPosition = itemstack.isItemEnchanted() ? getEnchantmentPosition(itemstack.getEnchantmentTagList(), enchantmentId) : -1;
		this.enchantmentLevel = enchantmentPosition > -1 ? itemstack.getEnchantmentTagList().getCompoundTagAt(enchantmentPosition).getShort("lvl") : -1;
	
	}
	
	/**
	 * 
	 * @return enchantmentId
	 */
	public int getEnchantmentId() {
		return enchantmentId;
	}
	
	/**
	 * 
	 * @return -1 if enchantment not in taglist
	 */
	public int getEnchantmentPosition(ItemStack itemstack) {
		return itemstack.isItemEnchanted() ? getEnchantmentPosition(itemstack.getEnchantmentTagList(), enchantmentId) : -1;
	}

	
	/**
	 * 
	 * @return -1 if enchantment not in taglist
	 */
	public int getEnchantmentLevel() {
		return enchantmentLevel;
	}
	
	

	public Enchantment getEnchantment() {
		return enchantment;
	}

	private int getEnchantmentPosition(NBTTagList enchantmentList, int enchantmentId){
		int position = -1;
		for(int i = 0; i < enchantmentList.tagCount(); i++){
			if(enchantmentList.getCompoundTagAt(i).hasKey("id")){
				if(enchantmentList.getCompoundTagAt(i).getShort("id") == enchantmentId){
					position = i;
					break;
				}
			}
		}
		return position;
	}

	@Override
	public int compareTo(EnchantmentOnItem arg0) {
        //returns -1 if "this" object is less than "that" object
        //returns 0 if they are equal
        //returns 1 if "this" object is greater than "that" object
		if(arg0.getEnchantmentLevel() < this.getEnchantmentLevel()){
			return -1;
		}
		if(arg0.getEnchantmentLevel() > this.getEnchantmentLevel()){
			return 1;
		}
		return 0;
	}
	
}
