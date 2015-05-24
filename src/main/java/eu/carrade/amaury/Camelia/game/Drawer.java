package eu.carrade.amaury.Camelia.game;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import eu.carrade.amaury.Camelia.Camelia;
import eu.carrade.amaury.Camelia.drawing.drawTools.core.DrawTool;

import java.util.UUID;

public class Drawer {

	private final UUID playerID;
	private final Player player;

	private boolean drawing = false;

	public Drawer(UUID playerID) {
		this.playerID = playerID;
		this.player = Bukkit.getPlayer(this.playerID);
	}

	/**
	 * The player's UUID
	 *
	 * @return The UUID
	 */
	public UUID getPlayerID() {
		return playerID;
	}

	/**
	 * The Player object
	 *
	 * @return The object. May be null.
	 */
	public Player getPlayer() {
		return Bukkit.getPlayer(playerID);
	}

	/**
	 * Is this player online?
	 *
	 * @return True if online
	 */
	public boolean isOnline() {
		return Bukkit.getOfflinePlayer(playerID).isOnline();
	}

	/**
	 * Is this player currently drawing?
	 *
	 * @return True if he is drawing
	 */
	public boolean isDrawing() {
		//return drawing;
		return true; // TODO testing purposes only.
	}

	/**
	 * Set the drawing status
	 *
	 * @param drawing True if he is drawing
	 */
	public void setDrawing(boolean drawing) {
		this.drawing = drawing;
	}
	
	public void fillInventory() {
		this.getPlayer().getInventory().clear();
		if(this.isDrawing()) {
			for(int i = 0; i < 9; i++) {
				DrawTool tool = Camelia.getInstance().getDrawingManager().getDrawTools().get(i);
				if(tool != null)
				this.player.getInventory().setItem(i, tool.constructIcon());
			}
		}
	}
}
