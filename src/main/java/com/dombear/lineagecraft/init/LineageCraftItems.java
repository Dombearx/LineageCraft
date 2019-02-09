package com.dombear.lineagecraft.init;


import java.util.ArrayList;
import java.util.List;

import com.dombear.lineagecraft.items.ItemEnchantScrollArmor;
import com.dombear.lineagecraft.items.ItemEnchantScrollWeapon;
import com.dombear.lineagecraft.items.ItemSoulShot;
import com.dombear.lineagecraft.utils.LineageCraftTypes.Type;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class LineageCraftItems {
	
	public static ArmorMaterial BROKENARMOR = EnumHelper.addArmorMaterial("BROKENARMOR", "lineagecraft:brokenarmor", 0, new int[] {0, 0, 0, 0}, 0, null, 0);
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	/**
	 * Items
	 */
	public static final Item soulShotIGrade = new ItemSoulShot("soulshotigrade", Type.IRON);
	public static final Item soulShotDGrade = new ItemSoulShot("soulshotdgrade", Type.DIAMOND);
	
	public static final Item enchantArmorDGrade = new ItemEnchantScrollArmor("enchantarmordgrade", Type.DIAMOND);
	public static final Item enchantWeaponDGrade = new ItemEnchantScrollWeapon("enchantweapondgrade", Type.DIAMOND);
	
	public static final Item enchantArmorIGrade = new ItemEnchantScrollArmor("enchantarmorigrade", Type.IRON);
	public static final Item enchantWeaponIGrade = new ItemEnchantScrollWeapon("enchantweaponigrade", Type.IRON);
	
	/*
	public static final Item sacryred = new ItemSACry("sacryred", SAType.RED);
	public static final Item sacrygreen= new ItemSACry("sacrygreen", SAType.GREEN);
	public static final Item sacryblue = new ItemSACry("sacryblue", SAType.BLUE);
	
	public static final ItemArmor brokenHelmet = new ItemBrokenArmor("brokenhelmet", BROKENARMOR, 1, EntityEquipmentSlot.HEAD);
	public static final ItemArmor brokenChestplate = new ItemBrokenArmor("brokenchestplate", BROKENARMOR, 1, EntityEquipmentSlot.CHEST);
	public static final ItemArmor brokenLeggings = new ItemBrokenArmor("brokenleggings", BROKENARMOR, 2, EntityEquipmentSlot.LEGS);
	public static final ItemArmor brokenBoots = new ItemBrokenArmor("brokenboots", BROKENARMOR, 1, EntityEquipmentSlot.FEET);

	
	
//	public static Item amuletOfLost;
*/
}
