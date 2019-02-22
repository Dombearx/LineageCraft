package com.dombear.lineagecraft.capabilities;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ExtendedInventoryProvider extends ItemStackHandler implements ICapabilityProvider{
	
	public ExtendedInventoryProvider(int size) {
		super(size);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		  if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
		    return true;
		  }
		  return false;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		  if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
		    return (T) stacks;
		  }
		  return null;
	}

}
