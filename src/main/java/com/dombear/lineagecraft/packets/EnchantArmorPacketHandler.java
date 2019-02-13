package com.dombear.lineagecraft.packets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.dombear.lineagecraft.gui.containers.ContainerEnchantArmor;
import com.dombear.lineagecraft.gui.inventories.InventoryEnchantArmor;
import com.dombear.lineagecraft.init.LineageCraftItems;
import com.dombear.lineagecraft.items.ItemEnchantScrollArmor;
import com.dombear.lineagecraft.items.ItemEnchantScrollWeapon;
import com.dombear.lineagecraft.utils.EnchantScrollHandlerBase;
import com.dombear.lineagecraft.utils.EnchantmentOnItem;
import com.dombear.lineagecraft.utils.LineageCraftReferences;
import com.dombear.lineagecraft.utils.LineageCraftTypes.Type;
import com.dombear.lineagecraft.utils.handlers.LineageCraftSoundHandler;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EnchantArmorPacketHandler extends EnchantScrollHandlerBase implements IMessageHandler<EnchantArmorPacket, EnchantArmorPacket>{
	
	
	
	@Override
	public EnchantArmorPacket onMessage(EnchantArmorPacket message, MessageContext ctx) {

		Type scrollType = message.getScrollType();
		
		Random random = new Random();
		EntityPlayer player = ctx.getServerHandler().player;
		InventoryPlayer inventoryPlayer = player.inventory;
		ContainerEnchantArmor container = (ContainerEnchantArmor) player.openContainer;
		InventoryEnchantArmor inventory = (InventoryEnchantArmor) container.inventory;


		if(!inventory.getStackInSlot(0).isEmpty()){
										
			ItemStack armor = inventory.getStackInSlot(0);
				
			if(inventoryPlayer.getCurrentItem().isEmpty()){
				return null;
			}
			
			if(inventoryPlayer.getCurrentItem().getItem() instanceof ItemEnchantScrollArmor){
				
				//Check if it's still same type of enchant scroll
				if(((ItemEnchantScrollArmor)inventoryPlayer.getCurrentItem().getItem()).getType() == scrollType){
					
					ArrayList<Enchantment> enchantments = new ArrayList<Enchantment>() { {
			                add(Enchantments.PROTECTION); 
			                add(Enchantments.PROJECTILE_PROTECTION); 
			                add(Enchantments.BLAST_PROTECTION); 
			                add(Enchantments.FIRE_PROTECTION);
			            } 
			        }; 
					
					int result = useEnchantScrollOnItem(armor, enchantments);
					
					//Enchantment didn't happen
					if(result == -1){
						return null;
					}
					
					//Succesfull;
					if(result == 0){
						inventoryPlayer.decrStackSize(inventoryPlayer.currentItem, 1);
						player.getEntityWorld().playSound(null, player.getPosition(), LineageCraftSoundHandler.ENCHANT_SCROLL_SUCCESSFUL, SoundCategory.PLAYERS, 0.5F, 1.0F);
						return null;
					}
					
					//Failure:
					if(result == 1){
						inventoryPlayer.decrStackSize(inventoryPlayer.currentItem, 1);
	
						int returnItemsAmount = countReturnItems(armor, enchantments);
						
						ItemArmor itemArmor = (ItemArmor) armor.getItem();
						ItemStack returnItemStack = null;
						switch (itemArmor.getArmorMaterial()) {
						case IRON:
							returnItemStack = new ItemStack(Items.IRON_INGOT, returnItemsAmount);
							break;
						case DIAMOND:
							returnItemStack = new ItemStack(Items.DIAMOND, returnItemsAmount);
							break;
						default:
							break;
						}
						EntityItem entityitem = new EntityItem(player.world, player.posX, player.posY + 1, player.posZ, returnItemStack);
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
		return null;
	}
		
}
