package com.dombear.lineagecraft.packets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dombear.lineagecraft.gui.containers.ContainerEnchantArmor;
import com.dombear.lineagecraft.gui.inventories.InventoryEnchantArmor;
import com.dombear.lineagecraft.init.LineageCraftItems;
import com.dombear.lineagecraft.utils.handlers.LineageCraftSoundHandler;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EnchantArmorPacketHandler implements IMessageHandler<EnchantArmorPacket, EnchantArmorPacket>{
	
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
	
	private void increaseEnachantmentLevel(){
		
	}
	
	/**
	 * 
	 * @param itemstack - itemstack with enchantments to increase
	 * @param enchantments - list of enchantments to be increased
	 * @return -1 - enchantment didn't happen, 0 - enchantment successful, 1 - enchantment failure
	 */
	private int useEnchantScrollOnItem(ItemStack itemstack, ArrayList<Enchantment> enchantments){
		
		if(itemstack.isEmpty()){
			return -1;
		}
		
		Random random = new Random();
		
		List<Integer> enchantmentIds = new ArrayList<>();
		for (Enchantment enchantment : enchantments) {
			enchantmentIds.add(Enchantment.getEnchantmentID(enchantment));
		}
				

		if(itemstack.isItemEnchanted()){
			
			List<Integer> enchantmentPositions = new ArrayList<>();
			List<Short> enchantmentCurrentLevels = new ArrayList<>();
			
			for (Integer enchantmentId : enchantmentIds) {
				enchantmentPositions.add(getEnchantmentPosition(itemstack.getEnchantmentTagList(), enchantmentId));
			}
			
			//enchantmentCurrentLevel from enchantmentPosition == -1 equals -1
			for (Integer enchantmentPosition : enchantmentPositions) {
				enchantmentCurrentLevels.add(enchantmentPosition > -1 ? itemstack.getEnchantmentTagList().getCompoundTagAt(enchantmentPosition).getShort("lvl") : -1);
			}
		
			
			if(enchantmentPosition > -1){
				
				if(enchantmentCurrentLevel >= 16){
					return -1;
				}
				if(enchantmentCurrentLevel < 3){
					itemstack.getEnchantmentTagList().removeTag(enchantmentPosition);
					itemstack.addEnchantment(enchantment, enchantmentCurrentLevel + 1);
					return 0;
				} else {
					int rand = random.nextInt(2);
					if(rand <= 2){
						itemstack.getEnchantmentTagList().removeTag(enchantmentPosition);
						itemstack.addEnchantment(enchantment, enchantmentCurrentLevel + 1);
						return 0;
					} else {
						return 1;
					}
				}
			} else {
				itemstack.addEnchantment(Enchantment.getEnchantmentByID(enchantmentId), 1);
				return 0;
			}
		} else {
			itemstack.addEnchantment(Enchantment.getEnchantmentByID(enchantmentId), 1);
			return 0;
		}
	}
	
	@Override
	public EnchantArmorPacket onMessage(EnchantArmorPacket message, MessageContext ctx) {

		Random random = new Random();
		EntityPlayer player = ctx.getServerHandler().player;
		InventoryPlayer inventoryPlayer = player.inventory;
		ContainerEnchantArmor container = (ContainerEnchantArmor) player.openContainer;
		InventoryEnchantArmor inventory = (InventoryEnchantArmor) container.inventory;

		int enchantment_protecion_id = 0;
		int enchantment_projectile_protecion_id = 4;

		if(!inventory.getStackInSlot(0).isEmpty()){
										
				ItemStack armor = inventory.getStackInSlot(0);
				int lvl_protecion = -1;
				int lvl_projectile_protecion = -1;

				int protecion_position = -1;
				int projectile_protecion_position =  -1;
					
				if(inventoryPlayer.getCurrentItem().isEmpty()){
					return null;
				}
				
				if(inventoryPlayer.getCurrentItem().getItem().equals(LineageCraftItems.enchantArmorDGrade)){
					
					inventoryPlayer.decrStackSize(inventoryPlayer.currentItem, 1);
					
					if(armor.isItemEnchanted()){
						if(getEnchantmentPosition(armor.getEnchantmentTagList(), enchantment_protecion_id) != -1){
							protecion_position = getEnchantmentPosition(armor.getEnchantmentTagList(), enchantment_protecion_id);
							lvl_protecion = armor.getEnchantmentTagList().getCompoundTagAt(protecion_position).getShort("lvl");
						}
						
						if(getEnchantmentPosition(armor.getEnchantmentTagList(), enchantment_projectile_protecion_id) != -1){
							projectile_protecion_position = getEnchantmentPosition(armor.getEnchantmentTagList(), enchantment_projectile_protecion_id);
							lvl_projectile_protecion = armor.getEnchantmentTagList().getCompoundTagAt(projectile_protecion_position).getShort("lvl");
						}			
						
					} else {
						armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_protecion_id), 1);
						armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_projectile_protecion_id), 1);
						inventory.setInventorySlotContents(0, armor);
						player.getEntityWorld().playSound(null, player.getPosition(), LineageCraftSoundHandler.ENCHANT_SCROLL_SUCCESSFUL, SoundCategory.PLAYERS, 0.5F, 1.0F);
						return null;
					}
					
					if(lvl_protecion >= 16 && lvl_projectile_protecion >= 16){
						return null;
					} else if(lvl_protecion >= 16){
						inventory.removeStackFromSlot(0);
						armor.getEnchantmentTagList().removeTag(getEnchantmentPosition(armor.getEnchantmentTagList(), enchantment_projectile_protecion_id));
						armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_projectile_protecion_id), 16);
						return null;
					}
					
					if(lvl_protecion == -1 && lvl_projectile_protecion == -1){
						armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_protecion_id), 1);
						armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_projectile_protecion_id), 1);

						inventory.setInventorySlotContents(0, armor);
					} else if(lvl_protecion == -1){
						
						armor.getEnchantmentTagList().removeTag(getEnchantmentPosition(armor.getEnchantmentTagList(), enchantment_projectile_protecion_id));
						armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_projectile_protecion_id), lvl_projectile_protecion + 1);
						
						armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_protecion_id), 1);

						inventory.setInventorySlotContents(0, armor);
					} else if(lvl_projectile_protecion == -1){
						

						armor.getEnchantmentTagList().removeTag(getEnchantmentPosition(armor.getEnchantmentTagList(), enchantment_protecion_id));
						armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_protecion_id), lvl_protecion + 1);
						
						armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_projectile_protecion_id), 1);

						inventory.setInventorySlotContents(0, armor);
						
					} else if(lvl_protecion < 3){
						armor.getEnchantmentTagList().removeTag(getEnchantmentPosition(armor.getEnchantmentTagList(), enchantment_protecion_id));
						armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_protecion_id), lvl_protecion + 1);

						armor.getEnchantmentTagList().removeTag(getEnchantmentPosition(armor.getEnchantmentTagList(), enchantment_projectile_protecion_id));
						armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_projectile_protecion_id), lvl_projectile_protecion + 1);

						inventory.setInventorySlotContents(0, armor);
					} else {
						int rand = random.nextInt(2);
						if(rand <= 2){
							armor.getEnchantmentTagList().removeTag(getEnchantmentPosition(armor.getEnchantmentTagList(), enchantment_protecion_id));
							armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_protecion_id), lvl_protecion + 1);

							armor.getEnchantmentTagList().removeTag(getEnchantmentPosition(armor.getEnchantmentTagList(), enchantment_projectile_protecion_id));
							armor.addEnchantment(Enchantment.getEnchantmentByID(enchantment_projectile_protecion_id), lvl_projectile_protecion + 1);

							inventory.setInventorySlotContents(0, armor);
						} else {
							
							int diamondsAmount = ((lvl_protecion + 1) / 2) / 3;
							if(inventory.getStackInSlot(0).getItem().equals(Items.DIAMOND_BOOTS) ||
									inventory.getStackInSlot(0).getItem().equals(Items.DIAMOND_HELMET)){
								diamondsAmount += 2;
							} else if(inventory.getStackInSlot(0).getItem().equals(Items.DIAMOND_LEGGINGS)){
								diamondsAmount += 3;
							} else if(inventory.getStackInSlot(0).getItem().equals(Items.DIAMOND_CHESTPLATE)){
								diamondsAmount += 4;
							}
							
							EntityItem entityitem = new EntityItem(player.world, player.posX, player.posY + 1, player.posZ, new ItemStack(Items.DIAMOND, diamondsAmount));
				    		entityitem.setPickupDelay(30);
				    		player.world.spawnEntity(entityitem);	
						
				    		player.getEntityWorld().playSound(null, player.getPosition(), LineageCraftSoundHandler.ENCHANT_SCROLL_FAILURE, SoundCategory.PLAYERS, 0.5F, 1.0F);
				    		inventory.removeStackFromSlot(0);
							player.closeScreen();
							return null;
						}
					}
				}	
			}
		player.getEntityWorld().playSound(null, player.getPosition(), LineageCraftSoundHandler.ENCHANT_SCROLL_SUCCESSFUL, SoundCategory.PLAYERS, 0.5F, 1.0F);
		return null;
	}


}
