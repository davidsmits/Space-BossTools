package net.mrscauthd.boss_tools.gui;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.Rectangle2d;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.boss_tools.gauge.GaugeValueHelper;
import net.mrscauthd.boss_tools.gui.guihelper.GuiHelper;
import net.mrscauthd.boss_tools.machines.BlastingFurnaceBlock.CustomTileEntity;

@OnlyIn(Dist.CLIENT)
public class BlastFurnaceGUIGuiWindow extends ContainerScreen<BlastFurnaceGUIGui.GuiContainerMod> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("boss_tools:textures/blast_furnace_gui.png");
	public static final int FIRE_LEFT = 53;
	public static final int FIRE_TOP = 39;
	public static final int ARROW_LEFT = 74;
	public static final int ARROW_TOP = 37;

	private CustomTileEntity tileEntity;

	public BlastFurnaceGUIGuiWindow(BlastFurnaceGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.tileEntity = container.getTileEntity();
		this.xSize = 177;
		this.ySize = 172;
	}

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);

		if (this.getFireBounds().contains(mouseX, mouseY)) {
			this.renderTooltip(ms, GaugeValueHelper.getBurnTime(this.getTileEntity().getPowerSystemBurnTime()).getText(), mouseX, mouseY);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		CustomTileEntity tileEntity = this.getTileEntity();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.minecraft.getTextureManager().bindTexture(TEXTURE);
		AbstractGui.blit(ms, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		GuiHelper.drawFire(ms, this.guiLeft + FIRE_LEFT, this.guiTop + FIRE_TOP, tileEntity.getPowerSystemBurnTime().getStoredRatio());
		GuiHelper.drawArrow(ms, this.guiLeft + ARROW_LEFT, this.guiTop + ARROW_TOP, tileEntity.getTimerRatio());
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Blast Furnace", 54, 5, 0x333333);
		this.font.drawString(ms, "Inventory", 7, 76, 0x333333);
	}

	public CustomTileEntity getTileEntity() {
		return this.tileEntity;
	}

	public Rectangle2d getFireBounds() {
		return GuiHelper.getFireBounds(this.guiLeft + FIRE_LEFT, this.guiTop + FIRE_TOP);
	}

}
