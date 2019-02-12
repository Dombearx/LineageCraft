package com.dombear.lineagecraft.utils.handlers;

import com.dombear.lineagecraft.gui.containers.ContainerEnchantArmor;
import com.dombear.lineagecraft.gui.containers.ContainerEnchantWeapon;
import com.dombear.lineagecraft.gui.guis.GuiEnchantArmor;
import com.dombear.lineagecraft.gui.guis.GuiEnchantWeapon;
import com.dombear.lineagecraft.gui.inventories.InventoryEnchantArmor;
import com.dombear.lineagecraft.gui.inventories.InventoryEnchantWeapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class LineageCraftGuiHandler  implements IGuiHandler{

	
	public static final int CRYTABLE_TILE_ENTITY_GUI = 0;
	public static final int ENCHANT_WEAPON_TILE_ENTITY_GUI = 1;
	public static final int ENCHANT_ARMOR_TILE_ENTITY_GUI = 2;
	public static final int SATABLE_TILE_ENTITY_GUI = 3;
	public static final int SSPACKER_TILE_ENTITY_GUI = 4;
	public static final int BETTER_ANVIL_TILE_ENTITY = 5;
	public static final int BLESSED_EWD_TILE_ENTITY_GUI = 6;
	public static final int BLESSED_EAD_TILE_ENTITY_GUI = 7;
	public static final int TOWN_SCROLL = 8;
	public static final int HIGH_PRIEST = 9;
	public static final int HIGH_PRIEST2 = 10;
	public static final int GATEKEEPER = 11;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		/*
		if (ID == CRYTABLE_TILE_ENTITY_GUI){
			return new ContainerCRYTable(player.inventory, (TileEntityCRYTable) world.getTileEntity(new BlockPos(x, y, z)));
		}
		*/
		if (ID == ENCHANT_WEAPON_TILE_ENTITY_GUI){
			return new ContainerEnchantWeapon(player.inventory, new InventoryEnchantWeapon(player.getHeldItemMainhand()));
		}
		
		if (ID == ENCHANT_ARMOR_TILE_ENTITY_GUI){
			System.out.println("container returned");
			return new ContainerEnchantArmor(player.inventory, new InventoryEnchantArmor(player.getHeldItemMainhand()));
		}
		/*
		if (ID == SATABLE_TILE_ENTITY_GUI){
			player.sendMessage(new TextComponentString("satable gui handler"));
			return new ContainerSATable(player.inventory, (TileEntitySATable) world.getTileEntity(new BlockPos(x, y, z)));
		}
		
		if (ID == SSPACKER_TILE_ENTITY_GUI){
			return new ContainerSsPacker(player.inventory, (TileEntitySsPacker) world.getTileEntity(new BlockPos(x, y, z)));
		}
		if (ID == BETTER_ANVIL_TILE_ENTITY){
			return new ContainerBetterAnvil(player.inventory, (TileEntityBetterAnvil) world.getTileEntity(new BlockPos(x, y, z)));
		}
		if (ID == BLESSED_EWD_TILE_ENTITY_GUI){
			return new ContainerEWD(player.inventory, new EWDInventory(player.getHeldItemMainhand()));
		}
		if (ID == BLESSED_EAD_TILE_ENTITY_GUI){
			return new ContainerEAD(player.inventory, new EADInventory(player.getHeldItemMainhand()));
		}
		if (ID == HIGH_PRIEST){
			return new ContainerHighPriest(player.inventory, player);
		}
		*/
		return null;
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		/*
		if (ID == CRYTABLE_TILE_ENTITY_GUI){
			return new GuiCRYTable((ContainerCRYTable) new ContainerCRYTable(player.inventory, (TileEntityCRYTable) world.getTileEntity(new BlockPos(x, y, z))));
		}
		*/
		if (ID == ENCHANT_WEAPON_TILE_ENTITY_GUI){
			return new GuiEnchantWeapon((ContainerEnchantWeapon) new ContainerEnchantWeapon(player.inventory, new InventoryEnchantWeapon(player.getHeldItemMainhand())));	
		}
		
		if (ID == ENCHANT_ARMOR_TILE_ENTITY_GUI){
			return new GuiEnchantArmor((ContainerEnchantArmor) new ContainerEnchantArmor(player.inventory, new InventoryEnchantArmor(player.getHeldItemMainhand())));	
		}
		/*
		
		if (ID == SATABLE_TILE_ENTITY_GUI){
			return new GuiSATable((ContainerSATable) new ContainerSATable(player.inventory, (TileEntitySATable) world.getTileEntity(new BlockPos(x, y, z))));
		}
		/*
		if (ID == SSPACKER_TILE_ENTITY_GUI){
			return new GuiSsPacker((ContainerSsPacker) new ContainerSsPacker(player.inventory, (TileEntitySsPacker) world.getTileEntity(new BlockPos(x, y, z))));
		}
		if (ID == BETTER_ANVIL_TILE_ENTITY){
			return new GuiBetterAnvil((ContainerBetterAnvil) new ContainerBetterAnvil(player.inventory, (TileEntityBetterAnvil) world.getTileEntity(new BlockPos(x, y, z))));
		}
		if (ID == BLESSED_EWD_TILE_ENTITY_GUI){
			return new GuiBlessedEWD((ContainerEWD) new ContainerEWD(player.inventory, new EWDInventory(player.getHeldItemMainhand())));	
		}
		if (ID == BLESSED_EAD_TILE_ENTITY_GUI){
			return new GuiBlessedEAD((ContainerEAD) new ContainerEAD(player.inventory, new EADInventory(player.getHeldItemMainhand())));	
		}
		if (ID == TOWN_SCROLL){
			return new GuiTownScroll(player, world);	
		}
		if (ID == HIGH_PRIEST2){
			return new GuiHighPriest2(player, world);	
		}
		if (ID == HIGH_PRIEST){
			return new GuiHighPriest((ContainerHighPriest) new ContainerHighPriest(player.inventory, player));	
		}	
		if (ID == GATEKEEPER){
			return new GuiGatekeeper(player, world);	
		}
		*/
		return null;
	}

}
