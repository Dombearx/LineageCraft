package com.dombear.lineagecraft.items;

import java.util.List;

import com.dombear.lineagecraft.utils.LineageCraftTypes.Type;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBrokenArmor extends LineageCraftArmorBase{

	private Type type;
	
	
	public ItemBrokenArmor(Type type, String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(name, materialIn, renderIndexIn, equipmentSlotIn);
		this.type = type;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(stack.getTagCompound() != null){
			if(stack.getTagCompound().hasKey("properties")){
				NBTTagCompound nbt = (NBTTagCompound) stack.getTagCompound().getTag("properties");
				tooltip.clear();
				tooltip.add("Broken " + nbt.getString("name"));
				if(nbt.hasKey("EnchantmentsIds")){
					tooltip.add("Enchantments: ");
					for(int i = 0; i < nbt.getIntArray("EnchantmentsIds").length; i++){
						tooltip.add(Enchantment.getEnchantmentByID((nbt.getIntArray("EnchantmentsIds")[i])).getTranslatedName(nbt.getIntArray("EnchantmentsLvls")[i]));
					}	
				}
			}
		}
	}

}
