package com.dombear.lineagecraft;

import com.dombear.lineagecraft.proxy.CommonProxy;
import com.dombear.lineagecraft.utils.LineageCraftReferences;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = LineageCraftReferences.MOD_ID, name = LineageCraftReferences.NAME, version = LineageCraftReferences.VERSION, acceptedMinecraftVersions = LineageCraftReferences.ACCEPTED_VERSIONS)
public class LineageCraft {
	@Instance
	public static LineageCraft instance;
	
	@SidedProxy(clientSide = LineageCraftReferences.CLIENT_PROXY_CLASS, serverSide = LineageCraftReferences.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	public static final CreativeTabs CREATIVE_TAB = new LineageCraftCreativeTab();

//	LineageCraftEventHandler eventHandler = new LineageCraftEventHandler();
	public static SimpleNetworkWrapper network;
	
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		System.out.println("LineageCraft preInit phaze");
		
		
		OBJLoader.INSTANCE.addDomain(LineageCraftReferences.MOD_ID);
		
		
//		ModItems.init();
//		ModItems.register();
//		
//		ModBlocks.init();
//		ModBlocks.register();
//		
//		ModArmor.init();
//		ModArmor.register();
//		
//		ModStructures.register();
//		
//		AchievementHandler.registerAchievements();
//		
//		proxy.preInit();
		
//		LineageCraftTileEntities.init();
		
//		LineageCraftEntities.registerEntities();
		
//		LineageCraftRenderHandler.testOBJRenders();
		
		//LineageCraftRenderHandler.registerEntityRenders();
		
//		network = NetworkRegistry.INSTANCE.newSimpleChannel(LineageCraftReferences.MOD_ID);
//		network.registerMessage(new EWDPacketHandler(), EWDPacket.class, 1, Side.SERVER);
//		network.registerMessage(new EADPacketHandler(), EADPacket.class, 2, Side.SERVER);
//		network.registerMessage(new CRYTablePacketHandler(), CRYTablePacket.class, 3, Side.SERVER);
//		network.registerMessage(new SATablePacketHandler(), SATablePacket.class, 4, Side.SERVER);
	}
	
	@EventHandler
	public static void serverLoad(FMLServerStartingEvent event) {
//		event.registerServerCommand(new InviteCommand());
//		event.registerServerCommand(new GetPartyInfo());
//		event.registerServerCommand(new ClearParties());
//		event.registerServerCommand(new KickCommand());
//		event.registerServerCommand(new LeaveCommand());
//		event.registerServerCommand(new PartyLootCommand());
//		event.registerServerCommand(new AcceptPartyCommand());
//		event.registerServerCommand(new rejectPartyCommand());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		System.out.println("----------init-----------------");
		
//		proxy.init();
//		proxy.registerGuis();
//		
//		ModCrafting.register();
//		
//		eventHandler.registerEvents();
		
//		LineageCraftRegisterHandler.initRegisters();
		
		//LineageCraftSoundHandler.init();
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		System.out.println("----------post init-----------------");

	}
}
