package com.dombear.lineagecraft.packets;

import com.dombear.lineagecraft.utils.LineageCraftTypes.Type;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EnchantWeaponPacket implements IMessage{
	
	public int enchantmentScrollTypeCoded;
	
	public EnchantWeaponPacket(){
		
	}
	
	/**
	 * 
	 * @param enchantmentScrollType 1 - iron, 2 - diamond, 3 - sgrade
	 */
	public EnchantWeaponPacket(Type enchantmentScrollType){
		switch (enchantmentScrollType) {
		case IRON:
			enchantmentScrollTypeCoded = 1;
			break;
		case DIAMOND:
			enchantmentScrollTypeCoded = 2;
			break;
		default:
			enchantmentScrollTypeCoded = 0;
			break;
		}
		
	}

	public Type getScrollType(){
		switch (enchantmentScrollTypeCoded) {
		case 1:
				return Type.IRON;
		case 2:
				return Type.DIAMOND;
		default:
				return null;
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		enchantmentScrollTypeCoded = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(enchantmentScrollTypeCoded);
	}

}
