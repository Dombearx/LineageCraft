package com.dombear.lineagecraft.world.generators;

import java.util.Random;

import com.dombear.lineagecraft.utils.LineageCraftReferences;
import com.dombear.lineagecraft.utils.interfaces.IStructure;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class LineageCraftWorldGenereateStructure extends WorldGenerator implements IStructure{

	public static String structureName;
	
	public LineageCraftWorldGenereateStructure(String structureName) {
		this.structureName = structureName;
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		this.generateStructure(worldIn, position);
		return true;
	}
	
	public static void generateStructure(World world, BlockPos blockPos){
		MinecraftServer minecraftServer = world.getMinecraftServer();
		TemplateManager templateManager = WORLD_SERVER.getStructureTemplateManager();
		ResourceLocation resourceLocation = new ResourceLocation(LineageCraftReferences.MOD_ID, structureName);
		Template template = templateManager.get(minecraftServer, resourceLocation);
		
		if(template != null){
			IBlockState blockState = world.getBlockState(blockPos);
			world.notifyBlockUpdate(blockPos, blockState, blockState, 3);
			template.addBlocksToWorldChunk(world, blockPos, settings);
		}
	}

}
