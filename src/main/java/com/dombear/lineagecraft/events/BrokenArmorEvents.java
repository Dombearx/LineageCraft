package com.dombear.lineagecraft.events;

import com.dombear.lineagecraft.init.LineageCraftItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BrokenArmorEvents {

	/**
	 * Check if armor should be replaced with brokenArmor.
	 * Replace armor before it break, so armor doesn't make break sound - Should be rewrited
	 * @param event
	 */
	@SubscribeEvent
	public void onArmorBroke(LivingHurtEvent event){
		
		System.out.println("broken armor event fired");
		
		//Entity is a player
		if(event.getEntity() instanceof EntityPlayer){
			
			System.out.println("player ");
			EntityPlayer player = (EntityPlayer) event.getEntity();
			NonNullList<ItemStack> armorInventory = (NonNullList<ItemStack>) player.getArmorInventoryList();
			
			//Player has armor
			if(!armorInventory.isEmpty()){
				System.out.println("armor not empty list ");
				ItemArmor armor;
				ItemStack brokenArmor = null;
				for (int i = 0; i < armorInventory.size(); i++) {
					if(armorInventory.get(i) != ItemStack.EMPTY && armorInventory.get(i).getItem() instanceof ItemArmor){
						System.out.println("item armor at " + i);
						if(armorInventory.get(i).isItemEnchanted()){
							System.out.println("armor enchanted");
							armor = (ItemArmor) armorInventory.get(i).getItem();
							player.sendMessage(new TextComponentString("Armor damage left - " + (armor.getMaxDamage() - armor.getDamage(armorInventory.get(i)))));
							if((armor.getMaxDamage() - armor.getDamage(armorInventory.get(i))) <= 0){
								System.out.println("armor damage left < 0");
								switch (armor.getArmorMaterial()) {
								case IRON:
									switch (armor.getEquipmentSlot()) {
									case HEAD:
										brokenArmor = new ItemStack(LineageCraftItems.brokenHelmetIron);
										break;
									case CHEST:
										brokenArmor = new ItemStack(LineageCraftItems.brokenChestplateIron);
										break;
									case LEGS:
										brokenArmor = new ItemStack(LineageCraftItems.brokenLeggingsIron);
										break;
									case FEET:
										brokenArmor = new ItemStack(LineageCraftItems.brokenBootsIron);
										break;
									default:
										return;
									}
									break;
								case DIAMOND:
									switch (armor.getEquipmentSlot()) {
									case HEAD:
										brokenArmor = new ItemStack(LineageCraftItems.brokenHelmetDiamond);
										break;
									case CHEST:
										brokenArmor = new ItemStack(LineageCraftItems.brokenChestplateDiamond);
										break;
									case LEGS:
										brokenArmor = new ItemStack(LineageCraftItems.brokenLeggingsDiamond);
										break;
									case FEET:
										brokenArmor = new ItemStack(LineageCraftItems.brokenBootsDiamond);
										break;
									default:
										return;
									}									
									break;
								default:
									return;
								}
								
								NBTTagCompound nbt = new NBTTagCompound();
								
								//Set enchantment Id's and Level's array size
								int enchantmentsIds[] = new int[armorInventory.get(i).getEnchantmentTagList().tagCount()];
								int enchantmentsLvls[] = new int[armorInventory.get(i).getEnchantmentTagList().tagCount()];
								//Get enchantments ids and levels
								for(int i1 = 0; i1 < armorInventory.get(i).getEnchantmentTagList().tagCount(); i1++){
									enchantmentsIds[i1] = armorInventory.get(i).getEnchantmentTagList().getCompoundTagAt(i1).getShort("id");
									enchantmentsLvls[i1] = armorInventory.get(i).getEnchantmentTagList().getCompoundTagAt(i1).getShort("lvl");
								}
								nbt.setIntArray("EnchantmentsIds", enchantmentsIds);
								nbt.setIntArray("EnchantmentsLvls", enchantmentsLvls);

								nbt.setString("name", armorInventory.get(i).getDisplayName());
								
								brokenArmor.setTagCompound(new NBTTagCompound());
								brokenArmor.getTagCompound().setTag("properties", nbt);

								
								armorInventory.set(i, brokenArmor);
							}
						}
					}
				}
			}
		}
	}
}
