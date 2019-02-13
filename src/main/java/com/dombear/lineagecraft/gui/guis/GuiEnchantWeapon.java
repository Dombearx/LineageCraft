package com.dombear.lineagecraft.gui.guis;

import java.io.IOException;

import com.dombear.lineagecraft.LineageCraft;
import com.dombear.lineagecraft.gui.ProgressBar;
import com.dombear.lineagecraft.gui.ProgressBar.ProgressBarDirection;
import com.dombear.lineagecraft.gui.containers.ContainerEnchantWeapon;
import com.dombear.lineagecraft.gui.inventories.InventoryEnchantWeapon;
import com.dombear.lineagecraft.items.ItemEnchantScrollWeapon;
import com.dombear.lineagecraft.packets.EnchantArmorPacket;
import com.dombear.lineagecraft.packets.EnchantWeaponPacket;
import com.dombear.lineagecraft.utils.LineageCraftReferences;
import com.dombear.lineagecraft.utils.handlers.LineageCraftSoundHandler;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.enchantment.EnchantmentFishingSpeed;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class GuiEnchantWeapon extends GuiContainer{

	private float xSize;
	private float ySize;
	
	private boolean isWorking = false;
	
	private static final ResourceLocation texture = new ResourceLocation(LineageCraftReferences.MOD_ID, "textures/guis/containers/ewd_container.png");
	
	private static int time = (2 * 20) + 8;
	private int enchantment_protecion_id = Enchantments.PROTECTION.getEnchantmentID(Enchantments.PROTECTION);
	private int enchantment_projectile_protecion_id = Enchantments.PROJECTILE_PROTECTION.getEnchantmentID(Enchantments.PROJECTILE_PROTECTION);
	
	public static float minCooldown = 0, maxCooldown = time;	
	private ProgressBar progressBar;
	private final InventoryEnchantWeapon inventory;
		
	public GuiEnchantWeapon(ContainerEnchantWeapon containerItem) {
		super(containerItem);
		this.inventory = containerItem.inventory;
        
        this.xSize = 176;
        this.ySize = 166;

        this.progressBar = new ProgressBar(texture, ProgressBarDirection.LEFT_TO_RIGHT, 90, 3, 43, 60, 0, 166);
	}

	@Override
	public void onGuiClosed(){
        if (this.mc.player != null){
        	this.inventorySlots.onContainerClosed(this.mc.player);
        }
        minCooldown = 0;
        isWorking = false;
    }
	

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id == 0){	
			button.enabled = false;
			isWorking = true;	
			this.mc.world.playSound(this.mc.player, this.mc.player.getPosition(), LineageCraftSoundHandler.ENCHANT_SCROLL_ACTIVATED, SoundCategory.PLAYERS, 0.3F, 1.0F);
		}
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        if(isWorking) {
	    	minCooldown += partialTicks;
	    }       
	    
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

	@Override
	public void updateScreen() {
		if(inventory.getStackInSlot(0) != ItemStack.EMPTY){
			ItemStack armor = inventory.getStackInSlot(0);
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
			this.buttonList.get(0).enabled = true;
		} else {
			this.isWorking = false;
			minCooldown = 0;
			this.buttonList.get(0).enabled = false;
		}
		if(isWorking){
			if(minCooldown >= time){
				this.isWorking = false;
				minCooldown = 0;
				LineageCraft.network.sendToServer(new EnchantWeaponPacket(((ItemEnchantScrollWeapon)this.inventory.getInvItem().getItem()).getType()));
			}
		}	
		
		super.updateScreen();
	}
	
	@Override
	public void initGui() {
		
		super.initGui();
		this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.guiLeft + 110, this.guiTop + 32, 50, 20, "Enchant"));
        this.mc.world.playSound(this.mc.player, this.mc.player.getPosition(), LineageCraftSoundHandler.ENCHANT_SCROLL_OPEN, SoundCategory.PLAYERS, 0.3F, 1.0F);
	}
	
	
	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	    this.mc.getTextureManager().bindTexture(texture);
	    this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, (int) this.xSize, (int) this.ySize);
	}
	
	
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = inventory.getDisplayName().getUnformattedText();		
	    this.fontRenderer.drawString(s, 88 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);            //#404040
	    this.fontRenderer.drawString("Inventory", 8, 72, 4210752);      //#404040
	
	    this.progressBar.setMin(minCooldown).setMax(maxCooldown);
	    this.progressBar.draw(this.mc);
	    
	    super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	

}
