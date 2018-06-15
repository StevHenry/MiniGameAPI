package net.starype.minigameapi.features.polyvalent;

import java.util.List;

import org.bukkit.entity.Player;

public abstract class Respawn {
	
	private List<Player> players;

	public Respawn(List<Player> players) {
		
		this.players = players;
	} 

	public abstract void execute(Player target);

	public List<Player> getPlayers() {
		return players;
	}
}
