package com.dombear.lineagecraft.packets;

import java.util.ArrayList;
import java.util.Random;

import com.dombear.lineagecraft.gui.containers.ContainerEnchantWeapon;
import com.dombear.lineagecraft.gui.inventories.InventoryEnchantWeapon;
import com.dombear.lineagecraft.items.ItemEnchantScrollWeapon;
import com.dombear.lineagecraft.utils.EnchantScrollHandlerBase;
import com.dombear.lineagecraft.utils.LineageCraftTypes.Type;
import com.dombear.lineagecraft.utils.handlers.LineageCraftSoundHandler;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EnchantWeaponPacketHandler extends EnchantScrollHandlerBase implements IMessageHandler<EnchantWeaponPacket, EnchantWeaponPacket>{
	
	
	
	@Override
	public EnchantWeaponPacket onMessage(EnchantWeaponPacket message, MessageContext ctx) {

		Type scrollType = message.getScrollType();
		
		Random random = new Random();
		EntityPlayer player = ctx.getServerHandler().player;
		InventoryPlayer inventoryPlayer = player.inventory;
		ContainerEnchantWeapon container = (ContainerEnchantWeapon) player.openContainer;
		InventoryEnchantWeapon inventory = (InventoryEnchantWeapon) container.inventory;


		if(!inventory.getStackInSlot(0).isEmpty()){
										
			ItemStack weapon = inventory.getStackInSlot(0);
				
			if(inventoryPlayer.getCurrentItem().isEmpty()){
				return null;
			}
			
			if(inventoryPlayer.getCurrentItem().getItem() instanceof ItemEnchantScrollWeapon){
				
				//Check if it's still same type of enchant scroll
				if(((ItemEnchantScrollWeapon)inventoryPlayer.getCurrentItem().getItem()).getType() == scrollType){
					
					ArrayList<Enchantment> enchantments = null;
					
					if(weapon.getItem() instanceof ItemAxe || weapon.getItem() instanceof ItemSword){
						enchantments = new ArrayList<Enchantment>() { {
			                	add(Enchantments.SHARPNESS); 
			            	} 
						}; 
					}
					
					if(weapon.getItem() instanceof ItemBow){
						enchantments = new ArrayList<Enchantment>() { {
			                	add(Enchantments.POWER); 
			            	} 
						}; 
					}
					
					int returnItemsAmount = countReturnItems(weapon, enchantments);
					ItemStack returnItemStack = null;
					switch (scrollType) {
					case IRON:
						returnItemStack = new ItemStack(Items.IRON_INGOT, returnItemsAmount);
						break;
					case DIAMOND:
						returnItemStack = new ItemStack(Items.DIAMOND, returnItemsAmount);
						break;
					default:
						returnItemStack = new ItemStack(Blocks.DIRT, 1);
						break;
					}
					
					int result = useEnchantScrollOnItem(weapon, enchantments);
					
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
