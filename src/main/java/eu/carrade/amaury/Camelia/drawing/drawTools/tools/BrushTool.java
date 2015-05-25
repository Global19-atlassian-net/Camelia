package eu.carrade.amaury.Camelia.drawing.drawTools.tools;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import eu.carrade.amaury.Camelia.Camelia;
import eu.carrade.amaury.Camelia.drawing.colors.core.GameBlock;
import eu.carrade.amaury.Camelia.drawing.drawTools.core.ContinuousDrawTool;
import eu.carrade.amaury.Camelia.game.Drawer;

public class BrushTool extends ContinuousDrawTool {

	@Override
	public String getDisplayName() {
		return ChatColor.AQUA + "" + ChatColor.BOLD + "Pinceau";
	}

	@Override
	public String getDescription() {
		return ChatColor.GRAY + "Peint une ligne d'épaisseur variable sur le tableau";
	}

	@Override
	public ItemStack getIcon(Drawer drawer) {
		return new ItemStack(Material.DIAMOND_SPADE);
	}

	@Override
	public int getSlot() {
		return 0;
	}

	@Override
	public void onRightClick(Location targetOnScreen, Drawer drawer) {
		GameBlock block = drawer.getColor().getBlock();
		Camelia.getInstance().getWhiteboard().setBlock(targetOnScreen, drawer.getColor());
	}

	@Override
	public void onLeftClick(Location targetOnScreen, Drawer drawer) {
	}

}