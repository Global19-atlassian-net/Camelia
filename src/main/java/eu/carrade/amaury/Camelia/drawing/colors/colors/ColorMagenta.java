package eu.carrade.amaury.Camelia.drawing.colors.colors;

import eu.carrade.amaury.Camelia.drawing.colors.core.*;
import org.bukkit.*;


public class ColorMagenta extends PixelColor {

	public ColorMagenta(ColorType type) {
		super(new GameBlock(Material.WOOL, DyeColor.MAGENTA), new GameBlock(Material.STAINED_CLAY, DyeColor.MAGENTA), new GameBlock(Material.PRISMARINE, (byte) 1));
		this.type = type;
	}

	@Override
	public String getBasicDisplayName() {
		return ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Magenta";
	}

	@Override
	public String getBetterDisplayName() {
		return ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Magenta";
	}

	@Override
	public String getRoughDisplayName() {
		return ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Magenta Bleuté";
	}
}
