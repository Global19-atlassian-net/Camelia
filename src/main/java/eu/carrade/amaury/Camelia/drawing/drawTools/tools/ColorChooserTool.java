package eu.carrade.amaury.Camelia.drawing.drawTools.tools;

import eu.carrade.amaury.Camelia.*;
import eu.carrade.amaury.Camelia.drawing.drawTools.core.*;
import eu.carrade.amaury.Camelia.drawing.whiteboard.*;
import eu.carrade.amaury.Camelia.game.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

/*
 * This file is part of Camelia.
 *
 * Camelia is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Camelia is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Camelia.  If not, see <http://www.gnu.org/licenses/>.
 */
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
		drawer.getPlayer().openInventory(Camelia.getInstance().getDrawingGuiManager().getColorInventory(drawer));

	}

	@Override
	public void onLeftClick(WhiteboardLocation targetOnScreen, Drawer drawer) {
		onRightClick(targetOnScreen, drawer);
	}

}
