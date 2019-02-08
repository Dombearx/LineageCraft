package com.dombear.lineagecraft.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSoulShot extends LineageCraftItemBase {
	
	private Type type;
	
	public enum Type {
		IRON, DIAMOND;
	}
	
	public ItemSoulShot(String name, Type type){
		super(name);
		this.type = type;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entityIn;
			/*
			if(this.type.equals(type.DIAMOND)){
				if(!player.hasAchievement(AchievementHandler.achievementSoulStealerAdvanced)) {
					player.addStat(AchievementHandler.achievementSoulStealerAdvanced);
				}
			} else {
				if(!player.hasAchievement(AchievementHandler.achievementSoulStealerBasic)) {
					player.addStat(AchievementHandler.achievementSoulStealerBasic);
				}
			}
			*/
		}
	}

}
