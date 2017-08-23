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
@ToolLocator(slot = 4)
public class PaintingsTools extends ClicDrawTool {

	public PaintingsTools(Drawer drawer) {
		super(drawer);
	}

	@Override
	public String getDisplayName() {
		return ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Fonds pré-définis";
	}

	@Override
	public String getDescription() {
		return ChatColor.GRAY + "Vous permet de changer le fond avec une sélection de belles illustrations";
	}

	@Override
	public ItemStack getIcon(Drawer drawer) {
		return new ItemStack(Material.PAINTING);
	}

	@Override
	public void onRightClick(WhiteboardLocation targetOnScreen, Drawer drawer) {
		drawer.getPlayer().openInventory(Camelia.getInstance().getDrawingGuiManager().getBackgroundInventory(drawer));
	}

	@Override
	public void onLeftClick(WhiteboardLocation targetOnScreen, Drawer drawer) {
		onRightClick(targetOnScreen, drawer);
	}
}
