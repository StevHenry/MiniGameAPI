package net.starype.minigameapi.sample;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.starype.minigameapi.multilinkable.StateChangeAction;

public class EndStateExample implements StateChangeAction {

	private Player winner;

	@Override
	public void executeWhenSet() {
		
		Bukkit.broadcastMessage(winner.getName()+" won !");
	}
	
	public void setWinner(Player winner) {
		
		this.winner = winner;
	}

}
