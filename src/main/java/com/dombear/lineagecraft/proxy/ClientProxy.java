package com.dombear.lineagecraft.proxy;

import com.dombear.lineagecraft.LineageCraft;
import com.dombear.lineagecraft.utils.handlers.LineageCraftGuiHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy implements CommonProxy{

	@Override
	public void registerGuis() {
		   NetworkRegistry.INSTANCE.registerGuiHandler(LineageCraft.instance, new LineageCraftGuiHandler());

	}
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));		
	}

}
