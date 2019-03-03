package com.dombear.lineagecraft.capabilities;

import javax.annotation.Nullable;

import org.w3c.dom.events.EventTarget;

import com.dombear.lineagecraft.utils.CapabilityUtils;
import com.dombear.lineagecraft.utils.LineageCraftReferences;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.items.IItemHandler;

public class CapabilityDeadItems {

	/**
	 * The {@link Capability} instance.
	 */
	@CapabilityInject(IItemHandler.class)
	public static final Capability<IItemHandler> ITEM_HANDLER_CAPABILITY  = null;

	/**
	 * The default {@link EnumFacing} to use for this capability.
	 */
	public static final EnumFacing DEFAULT_FACING = null;

	/**
	 * The ID of this capability.
	 */
	public static final ResourceLocation ID = new ResourceLocation(LineageCraftReferences.MOD_ID, "DeadItems");


	public static void register() {
		CapabilityManager.INSTANCE.register(IItemHandler.class, new Capability.IStorage<IItemHandler>() {
			@Override
			public NBTBase writeNBT(final Capability<IItemHandler> capability, final IItemHandler instance, final EnumFacing side) {
				NBTTagCompound tag = new NBTTagCompound();
		        NBTTagList tagList = new NBTTagList();

		        for(int i = 0; i < instance.getSlots(); ++i)
		        {
		            ItemStack itemStack = instance.getStackInSlot(i);

		            if(!itemStack.isEmpty())
		            {
		                NBTTagCompound compound = itemStack.writeToNBT(new NBTTagCompound());
		                tagList.appendTag(compound);
		            }
		        }

		        if(!tagList.hasNoTags())
		        {
		            tag.setTag("Items", tagList);
		        }

		        return tag;
			}

			@Override
			public void readNBT(final Capability<IItemHandler> capability, final IItemHandler instance, final EnumFacing side, final NBTBase nbt) {
				NBTTagList tagList = ((NBTTagCompound) nbt).getTagList("Items", 10);
				
				for (int i = 0; i < tagList.tagCount(); i++) {
					ItemStack itemStack = new ItemStack((NBTTagCompound) tagList.get(i));
					instance.insertItem(i, itemStack, false);
				}
			}
		}, () -> new ItemStackHandler(null));
	}

	/**
	 * Get the {@link IMaxHealth} from the specified entity.
	 *
	 * @param entity The entity
	 * @return The IMaxHealth
	 */
	@Nullable
	public static IItemHandler getInventory(final EntityLivingBase entity) {
		return CapabilityUtils.getCapability(entity, ITEM_HANDLER_CAPABILITY, DEFAULT_FACING);
	}

	/**
	 * Create a provider for the specified {@link IMaxHealth} instance.
	 *
	 * @param maxHealth The IMaxHealth
	 * @return The provider
	 */
	public static ICapabilityProvider createProvider(final IItemHandler inventory) {
		return new CapabilityProviderSerializable<>(ITEM_HANDLER_CAPABILITY, DEFAULT_FACING, inventory);
	}

	/**
	 * Format a max health value.
	 *
	 * @param maxHealth The max health value
	 * @return The formatted text.
	 */
	public static String formatMaxHealth(final float maxHealth) {
		return ItemStack.DECIMALFORMAT.format(maxHealth);
	}

	/**
	 * Event handler for the {@link IMaxHealth} capability.
	 */
	@SuppressWarnings("unused")
	@Mod.EventBusSubscriber(modid = LineageCraftReferences.MOD_ID)
	private static class EventHandler {

		/**
		 * Attach the {@link IMaxHealth} capability to all living entities.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof EntityPlayer) {
				final ItemStackHandler inventory = new ItemStackHandler((EntityPlayer) event.getObject());
				event.addCapability(ID, createProvider(inventory));
			}
		}

		/**
		 * Copy the player's bonus max health when they respawn after dying or returning from the end.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void playerClone(final PlayerEvent.Clone event) {
			final ItemStackHandler oldInventory = (ItemStackHandler) getInventory(event.getOriginal());
			final ItemStackHandler newInventory = (ItemStackHandler) getInventory(event.getEntityPlayer());

			if (newInventory != null && oldInventory != null) {
				newInventory.setInventory(oldInventory.getInventory());
				event.getEntityPlayer().inventory = newInventory.getInventory();
			}
		}
		
		@SubscribeEvent
		public static void playerDead(final LivingDamageEvent event) {
			if(event.getEntityLiving() instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				ItemStackHandler newInventory = (ItemStackHandler) getInventory(player);
				for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
					newInventory.insertItem(i, player.inventory.getStackInSlot(i), false);
				}
				System.out.println("inventory updated");
			}


		}

		/**
		 * Synchronise a player's max health to watching clients when they change dimensions.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void playerChangeDimension(final PlayerChangedDimensionEvent event) {
			final IItemHandler inventory = getInventory(event.player);

			if (inventory != null) {
				//inventory.synchronise();
			}
		}
}
}
