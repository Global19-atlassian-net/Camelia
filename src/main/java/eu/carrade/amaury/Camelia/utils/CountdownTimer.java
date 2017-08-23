package eu.carrade.amaury.Camelia.utils;

import eu.carrade.amaury.Camelia.*;
import net.samagames.api.games.*;
import net.samagames.tools.*;
import org.bukkit.*;
import org.bukkit.entity.*;

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
public class CountdownTimer {

	private int seconds = 30;
	private int task = -1;
	private boolean isEnabled = false;

	private final Integer COUNTER_REDUCTION_WHEN_HALF_FULL;
	private final Integer COUNTER_REDUCTION_WHEN_FULL;

	public CountdownTimer() {
		COUNTER_REDUCTION_WHEN_HALF_FULL = Camelia.getInstance().getArenaConfig().getInt("map.waiting") / 2;
		COUNTER_REDUCTION_WHEN_FULL = Camelia.getInstance().getArenaConfig().getInt("map.waiting") / 5;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void cancelTimer() {
		Camelia.getInstance().getServer().getScheduler().cancelTask(task);
		isEnabled = false;

		Camelia.getInstance().getGameManager().setStatus(Status.WAITING_FOR_PLAYERS);

		for (Player player : Camelia.getInstance().getServer().getOnlinePlayers()) {
			player.setLevel(0);
		}
	}

	public void restartTimer() {
		Camelia.getInstance().getServer().getScheduler().cancelTask(task);
		isEnabled = true;

		Camelia.getInstance().getGameManager().setStatus(Status.READY_TO_START);

		seconds = Camelia.getInstance().getGameManager().getCountdownTime();
		task = Camelia.getInstance().getServer().getScheduler().runTaskTimer(Camelia.getInstance(), () -> {
			// Timer reduction if players count is high
			int playersCount = Camelia.getInstance().getGameManager().getConnectedPlayers();
			boolean changed = false;

			// Half-full
			if (playersCount == Math.max(Camelia.getInstance().getGameManager().getMinPlayers(), Camelia.getInstance().getGameManager().getMaxPlayers() / 2)
					&& seconds > COUNTER_REDUCTION_WHEN_HALF_FULL) {
				seconds = COUNTER_REDUCTION_WHEN_HALF_FULL;
				changed = true;
			}
			// Full
			else if (playersCount == Camelia.getInstance().getGameManager().getMaxPlayers() && seconds > COUNTER_REDUCTION_WHEN_FULL) {
				seconds = COUNTER_REDUCTION_WHEN_FULL;
				changed = true;
			}

			// Message if counter changed
			if (changed) {
				Camelia.getInstance().getServer().broadcastMessage(
						Camelia.getInstance().getCoherenceMachine().getGameTag() + ChatColor.YELLOW + "Il y a désormais " + ChatColor.RED + playersCount
								+ ChatColor.YELLOW + " joueurs en jeu."
				);
				Camelia.getInstance().getServer().broadcastMessage(
						Camelia.getInstance().getCoherenceMachine().getGameTag() + ChatColor.YELLOW + "Le compteur a été raccourci à "
								+ ChatColor.RED + seconds + ChatColor.YELLOW + " secondes."
				);
			}

			// Counter display (chat + title)
			if (seconds == 120 || seconds == 60 || seconds == 30 || seconds == 15 || seconds == 10 || seconds <= 5
					&& seconds != 0) {

				// Messages
				if (!changed) {
					if (seconds == 1) {
						Camelia.getInstance().getServer().broadcastMessage(
								Camelia.getInstance().getCoherenceMachine().getGameTag() + ChatColor.YELLOW + "Le jeu commence dans " + ChatColor.RED
										+ seconds + ChatColor.YELLOW + " seconde");
					} else {
						Camelia.getInstance().getServer().broadcastMessage(
								Camelia.getInstance().getCoherenceMachine().getGameTag() + ChatColor.YELLOW + "Le jeu commence dans " + ChatColor.RED
										+ seconds + ChatColor.YELLOW + " secondes");
					}
				}

				// Sound
				if (seconds <= 10) {
					for (Player player : Camelia.getInstance().getServer().getOnlinePlayers()) {
						player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
					}
				}

				// Title
				ChatColor color;
				if (seconds >= 30) {
					color = ChatColor.GREEN;
				} else if (seconds >= 4) {
					color = ChatColor.YELLOW;
				} else {
					color = ChatColor.RED;
				}

				for (Player player : Camelia.getInstance().getServer().getOnlinePlayers()) {
					Titles.sendTitle(player, 2, 16, 2, color + "" + seconds, "");
				}
			}

			// Seconds in XP
			for (Player player : Camelia.getInstance().getServer().getOnlinePlayers()) {
				player.setLevel(seconds);
			}

			// Start
			if (seconds < 1) {
				Camelia.getInstance().getServer().getScheduler().cancelTask(task);

				Camelia.getInstance().getGameManager().startGame();
			}

			seconds--;
		}, 30L, 20L).getTaskId();
	}

	/**
	 * Returns the seconds left before the game.
	 */
	public int getSecondsLeft() {
		return seconds;
	}
}
