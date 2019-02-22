package com.dombear.lineagecraft.events;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AmuletOfLostEvents {
	
	@SubscribeEvent
	public void onPlayerDeath(PlayerDropsEvent event){
		List<EntityItem> dropedItems = event.getDrops();
	}

	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.Clone event){
		if(event.isWasDeath()){
			
		}
	}
}
