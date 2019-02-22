package com.dombear.lineagecraft.utils.handlers;

import com.dombear.lineagecraft.events.BrokenArmorEvents;
import com.dombear.lineagecraft.events.ExtendedInventoryEvents;
import com.dombear.lineagecraft.events.SoulShotEvents;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class LineageCraftEventHandler {
	public void registerEvents(){
		MinecraftForge.EVENT_BUS.register(new SoulShotEvents());
//		MinecraftForge.EVENT_BUS.register(new PlayerEvents());
		MinecraftForge.EVENT_BUS.register(new BrokenArmorEvents());
		MinecraftForge.EVENT_BUS.register(new ExtendedInventoryEvents());
	}
}
