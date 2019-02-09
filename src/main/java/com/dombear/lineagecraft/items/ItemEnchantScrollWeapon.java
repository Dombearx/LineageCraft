package com.dombear.lineagecraft.items;

import com.dombear.lineagecraft.LineageCraft;
import com.dombear.lineagecraft.utils.LineageCraftTypes.Type;
import com.dombear.lineagecraft.utils.handlers.LineageCraftGuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemEnchantScrollWeapon extends LineageCraftItemBase{
		
	private Type type;

	public ItemEnchantScrollWeapon(String name, Type type) {
		super(name);
		this.type = type;
	}
	
	public Type getType() {
		return this.type;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		if(!worldIn.isRemote){
			playerIn.openGui(LineageCraft.instance, LineageCraftGuiHandler.ENCHANT_WEAPON_TILE_ENTITY_GUI, worldIn, 0, 0, 0);
		}
        return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

}
