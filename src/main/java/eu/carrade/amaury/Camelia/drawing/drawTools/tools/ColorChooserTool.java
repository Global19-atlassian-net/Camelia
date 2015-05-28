package eu.carrade.amaury.Camelia.drawing.drawTools.tools;

import eu.carrade.amaury.Camelia.Camelia;
import eu.carrade.amaury.Camelia.drawing.drawTools.core.ClicDrawTool;
import eu.carrade.amaury.Camelia.drawing.drawTools.core.ToolLocator;
import eu.carrade.amaury.Camelia.drawing.whiteboard.WhiteboardLocation;
import eu.carrade.amaury.Camelia.game.Drawer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;


@ToolLocator(slot = 6)
public class ColorChooserTool extends ClicDrawTool {

	public ColorChooserTool(Drawer drawer) {
		super(drawer);
	}

	@Override
	public String getDisplayName() {
		return ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Sélecteur de couleurs";
	}

	@Override
	public String getDescription() {
		return ChatColor.GRAY + "Vous permet de choisir une couleur pour dessiner";
	}

	@Override
	public ItemStack getIcon(Drawer drawer) {
		return drawer.getColor().getBlock().toItemStack(1);
	}

	@Override
	public void onRightClick(WhiteboardLocation targetOnScreen, Drawer drawer) {
		drawer.getPlayer().openInventory(Camelia.getInstance().getGuiManager().getColorInventory(drawer));
	}

	@Override
	public void onLeftClick(WhiteboardLocation targetOnScreen, Drawer drawer) {
		onRightClick(targetOnScreen, drawer);
	}

}
