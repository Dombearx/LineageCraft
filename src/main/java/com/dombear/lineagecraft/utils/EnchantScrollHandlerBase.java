package com.dombear.lineagecraft.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class EnchantScrollHandlerBase {
	/**
	 * 
	 * @param itemstack - itemstack with enchantments to increase
	 * @param enchantments - list of enchantments to be increased
	 * @return -1 - enchantment didn't happen, 0 - enchantment successful, 1 - enchantment failure
	 */
	public int useEnchantScrollOnItem(ItemStack itemstack, ArrayList<Enchantment> enchantments){
		
		if(itemstack.isEmpty() || enchantments.size() == 0){
			System.out.println("---------------encahntments empty-----------");
			return -1;
		}
		
		Random random = new Random();
		
		List<EnchantmentOnItem> enchantmentsOnItem = new ArrayList<>();
		for (Enchantment enchantment : enchantments) {
			enchantmentsOnItem.add(new EnchantmentOnItem(itemstack, enchantment));
		}
							
		
		//Enchantments doesn't exists
		if(Collections.max(enchantmentsOnItem).getEnchantmentLevel() < 0){
			for (EnchantmentOnItem enchantmentOnItem : enchantmentsOnItem) {
				itemstack.addEnchantment(enchantmentOnItem.getEnchantment(), 1);
			}
			return 0;
		}
		
		//Max enchantment level for all enchantments
		if(Collections.min(enchantmentsOnItem).getEnchantmentLevel() >= LineageCraftReferences.MAX_ENCHANTMENT_LEVEL){
			System.out.println("---------------encahntments max-----------");
			return -1;
		}
		
		//Enchantments below SAFE_ENCHANTMENT_LEVEL level
		if(Collections.max(enchantmentsOnItem).getEnchantmentLevel() < LineageCraftReferences.SAFE_ENCHANTMENT_LEVEL){
			for (EnchantmentOnItem enchantmentOnItem : enchantmentsOnItem) {
				if(enchantmentOnItem.getEnchantmentLevel() > -1){
					if(enchantmentOnItem.getEnchantmentLevel() < LineageCraftReferences.MAX_ENCHANTMENT_LEVEL){
						itemstack.getEnchantmentTagList().removeTag(enchantmentOnItem.getEnchantmentPosition(itemstack));
						itemstack.addEnchantment(enchantmentOnItem.getEnchantment(), enchantmentOnItem.getEnchantmentLevel() + 1);	
					}
				} else {
					itemstack.addEnchantment(enchantmentOnItem.getEnchantment(), 1);					
				}
			}
			return 0;
		}
		
		//Enchantment above SAFE_ENCHANTMENT_LEVEL level
		//Random between 1 - 100
		int rand = random.nextInt(100) + 1;
		if(rand <= LineageCraftReferences.ENCHANTMENT_PERCENT_CHANCE){
			for (EnchantmentOnItem enchantmentOnItem : enchantmentsOnItem) {
				if(enchantmentOnItem.getEnchantmentLevel() > -1){
					if(enchantmentOnItem.getEnchantmentLevel() < LineageCraftReferences.MAX_ENCHANTMENT_LEVEL){
						itemstack.getEnchantmentTagList().removeTag(enchantmentOnItem.getEnchantmentPosition(itemstack));
						itemstack.addEnchantment(enchantmentOnItem.getEnchantment(), enchantmentOnItem.getEnchantmentLevel() + 1);	
					}
				} else {
					itemstack.addEnchantment(enchantmentOnItem.getEnchantment(), 1);					
				}
			}
			return 0;
		} else {
			return 1;
		}
	}
	
	
	public int countReturnItems(ItemStack itemstack, ArrayList<Enchantment> enchantments){
		
		List<EnchantmentOnItem> enchantmentsOnItem = new ArrayList<>();
		for (Enchantment enchantment : enchantments) {
			enchantmentsOnItem.add(new EnchantmentOnItem(itemstack, enchantment));
		}
		
		int amountOfItems = ((Collections.max(enchantmentsOnItem).getEnchantmentLevel() + 1) / 2) + 1;
		
		if(itemstack.getItem() instanceof ItemArmor){
			ItemArmor itemArmor = (ItemArmor) itemstack.getItem();
			switch(itemArmor.armorType){
			case HEAD: amountOfItems += 2; break;
			case CHEST: amountOfItems += 4; break;
			case LEGS: amountOfItems += 3; break;
			case FEET: amountOfItems += 2; break;
			default:
				break;
			}
		}
		
		if(itemstack.getItem() instanceof ItemSword){
			amountOfItems += 1;
		}
		if(itemstack.getItem() instanceof ItemBow){
			amountOfItems += 0;
		}
		if(itemstack.getItem() instanceof ItemAxe){
			amountOfItems += 1;
		}
		
		return amountOfItems;
	}
}
