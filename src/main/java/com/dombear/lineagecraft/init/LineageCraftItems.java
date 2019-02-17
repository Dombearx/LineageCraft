package com.dombear.lineagecraft.init;


import java.util.ArrayList;
import java.util.List;

import com.dombear.lineagecraft.items.ItemBrokenArmor;
import com.dombear.lineagecraft.items.ItemEnchantScrollArmor;
import com.dombear.lineagecraft.items.ItemEnchantScrollWeapon;
import com.dombear.lineagecraft.items.ItemSoulShot;
import com.dombear.lineagecraft.utils.LineageCraftTypes.Type;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
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
	*/
	public static final ItemArmor brokenHelmetDiamond = new ItemBrokenArmor(Type.DIAMOND, "brokenhelmetdiamond", BROKENARMOR, 1, EntityEquipmentSlot.HEAD);
	public static final ItemArmor brokenChestplateDiamond = new ItemBrokenArmor(Type.DIAMOND, "brokenchestplatediamond", BROKENARMOR, 1, EntityEquipmentSlot.CHEST);
	public static final ItemArmor brokenLeggingsDiamond = new ItemBrokenArmor(Type.DIAMOND, "brokenleggingsdiamond", BROKENARMOR, 2, EntityEquipmentSlot.LEGS);
	public static final ItemArmor brokenBootsDiamond = new ItemBrokenArmor(Type.DIAMOND, "brokenbootsdiamond", BROKENARMOR, 1, EntityEquipmentSlot.FEET);
	
	public static final ItemArmor brokenHelmetIron = new ItemBrokenArmor(Type.IRON, "brokenhelmetiron", BROKENARMOR, 1, EntityEquipmentSlot.HEAD);
	public static final ItemArmor brokenChestplateIron = new ItemBrokenArmor(Type.IRON, "brokenchestplateiron", BROKENARMOR, 1, EntityEquipmentSlot.CHEST);
	public static final ItemArmor brokenLeggingsIron = new ItemBrokenArmor(Type.IRON, "brokenleggingsiron", BROKENARMOR, 2, EntityEquipmentSlot.LEGS);
	public static final ItemArmor brokenBootsIron = new ItemBrokenArmor(Type.IRON, "brokenbootsiron", BROKENARMOR, 1, EntityEquipmentSlot.FEET);
/*
	
	
//	public static Item amuletOfLost;
*/
}
