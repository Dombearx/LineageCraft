package com.dombear.lineagecraft.events;


import com.dombear.lineagecraft.capabilities.ExtendedInventoryProvider;
import com.dombear.lineagecraft.utils.LineageCraftReferences;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ExtendedInventoryEvents {

	 public static final ResourceLocation AOL_SLOT = new ResourceLocation(LineageCraftReferences.MOD_ID, "aol_slot");
	
	
	@SubscribeEvent
	public void remainInventoryOnDeath(PlayerEvent.Clone event){
		if(event.isWasDeath()){
			IItemHandler inventory = event.getEntityPlayer().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			IItemHandler oldInventory = event.getOriginal().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

			inventory.insertItem(0, oldInventory.getStackInSlot(0), false);
		}
	}
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event){
		if(event.getObject() instanceof EntityPlayer){
			event.addCapability(AOL_SLOT, new ExtendedInventoryProvider(1));
		}
	}
}
