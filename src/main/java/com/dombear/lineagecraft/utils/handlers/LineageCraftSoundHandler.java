package com.dombear.lineagecraft.utils.handlers;

import com.dombear.lineagecraft.utils.LineageCraftReferences;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class LineageCraftSoundHandler {
	private static int size = 0;
	
	public static SoundEvent SOULSHOT_ACTIVATED;
	
	public static SoundEvent ENCHANT_SCROLL_OPEN;
	public static SoundEvent ENCHANT_SCROLL_ACTIVATED;
	public static SoundEvent ENCHANT_SCROLL_SUCCESSFUL;
	public static SoundEvent ENCHANT_SCROLL_FAILURE;
	
	public static SoundEvent CTRITICAL;
	
	public static SoundEvent INVITE;
	public static SoundEvent REJECT;
	public static SoundEvent LEAVE;
	
	public static void init(){
		size = SoundEvent.REGISTRY.getKeys().size();
		
		SOULSHOT_ACTIVATED = register("item.soulshot.activated");
		
		ENCHANT_SCROLL_OPEN = register("item.enchant_scroll.open");
		ENCHANT_SCROLL_ACTIVATED = register("item.enchant_scroll.activated");
		ENCHANT_SCROLL_SUCCESSFUL = register("item.enchant_scroll.successful");
		ENCHANT_SCROLL_FAILURE = register("item.enchant_scroll.failure");
		
		CTRITICAL = register("critical");
		
		INVITE = register("invite");
		REJECT = register("reject");
		LEAVE = register("leave");
	}
	
	public static SoundEvent register(String name){
		ResourceLocation location = new ResourceLocation(LineageCraftReferences.MOD_ID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
