package com.dombear.lineagecraft.events;

import com.dombear.lineagecraft.init.LineageCraftItems;
import com.dombear.lineagecraft.utils.handlers.LineageCraftSoundHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SoulShotEvents {
	
	
	public void effects(EntityPlayer player){
		player.sendMessage(new TextComponentString("playing sound"));
		player.getEntityWorld().playSound(null, player.getPosition(), LineageCraftSoundHandler.SOULSHOT_ACTIVATED, SoundCategory.PLAYERS, 0.5F, 1.0F);
	}
	
	private int slotInHotbar(InventoryPlayer inv, Item item){
		for(int i = 0; i < 9; i++){
			if(!inv.getStackInSlot(i).isEmpty()){
				if(inv.getStackInSlot(i).getItem() == item){
					return i;
				}
			}
		}
		return -1;
	}
	
	@SubscribeEvent
	public void onCriticalHit(CriticalHitEvent event){
		event.getEntityPlayer().sendMessage(new TextComponentString("CriticalHit!"));
		event.getEntityPlayer().getEntityWorld().playSound(null, event.getEntityPlayer().getPosition(), LineageCraftSoundHandler.CTRITICAL, SoundCategory.PLAYERS, 0.5F, 1.0F);
	}
	
	@SubscribeEvent
	public void onEntityDmg(LivingHurtEvent event){	
		
		if(event.getSource().getTrueSource() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
					
			
			InventoryPlayer inv = player.inventory;
			if(event.getSource().getDamageType().equals("arrow")){
				if(slotInHotbar(inv, LineageCraftItems.soulShotDGrade) > -1){
					event.setAmount(event.getAmount()*1.5f);
					inv.getStackInSlot(slotInHotbar(inv, LineageCraftItems.soulShotDGrade)).shrink(1);
					return;
				}
				if(slotInHotbar(inv, LineageCraftItems.soulShotIGrade) > -1){
					event.setAmount(event.getAmount()*1.3f);
					inv.getStackInSlot(slotInHotbar(inv, LineageCraftItems.soulShotIGrade)).shrink(1);
					return;
				}
			}
			if(event.getSource().getDamageType().equals("player")){
				if(!player.getHeldItemMainhand().isEmpty()){
					ItemStack item = player.getHeldItemMainhand();
					
					if(item.getItem() == Items.DIAMOND_SWORD || item.getItem() == Items.DIAMOND_AXE){
						if(slotInHotbar(inv, LineageCraftItems.soulShotDGrade) > -1){
							event.setAmount(event.getAmount()*1.5f);
							inv.getStackInSlot(slotInHotbar(inv, LineageCraftItems.soulShotDGrade)).shrink(1);
							effects(player);
							return;
						}
					}
					
					
					if(item.getItem() == Items.IRON_SWORD || item.getItem() == Items.IRON_AXE){
						if(slotInHotbar(inv, LineageCraftItems.soulShotIGrade) > -1){
							event.setAmount(event.getAmount()*1.5f);
							inv.getStackInSlot(slotInHotbar(inv, LineageCraftItems.soulShotIGrade)).shrink(1);
							effects(player);
							return;
						}
					}
				}
			}
			
		}
	}
}
