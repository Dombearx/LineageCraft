package com.dombear.lineagecraft.utils.handlers;

import com.dombear.lineagecraft.init.LineageCraftItems;
import com.dombear.lineagecraft.utils.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * EventBusSubscriber means that this class will be automaticly launched while game loading
 * Don't need to initialize it anywhere
 */
@EventBusSubscriber
public class LineageCraftRegisterHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
	    event.getRegistry().registerAll(LineageCraftItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
	    //event.getRegistry().registerAll(LineageCraftBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
	    for(Item item : LineageCraftItems.ITEMS) {
	    	if(item instanceof IHasModel) {
	    		((IHasModel)item).registerModels();
	    		System.out.println("------------item registered----------");
	    	}
	    }
	    /*
	    for(Block block: LineageCraftBlocks.BLOCKS) {
	    	if(block instanceof IHasModel) {
	    		((IHasModel)block).registerModels();
	    		System.out.println("------------block registered----------");
	    	}
	    }
	    */
	}
	
	public static void initRegisters() {
		//LineageCraftSoundHandler.init();
	}
}
