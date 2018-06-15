package net.starype.minigameapi.tests;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.starype.minigameapi.features.action.JoinLeaveAction;
import net.starype.minigameapi.features.action.RespawnAction;
import net.starype.minigameapi.features.action.StateChangeAction;
import net.starype.minigameapi.features.polyvalent.Respawn;

public class ExampleState implements StateChangeAction, JoinLeaveAction, RespawnAction {

	private List<Respawn> respawns;
	
	@Override
	public void executeWhenSet() {
		
		System.out.println("State reached !");
	}

	@Override
	public void onJoin(PlayerJoinEvent event) {
		event.getPlayer().sendMessage("wi.");
	}

	@Override
	public void onLeave(PlayerQuitEvent event) {
		Bukkit.broadcastMessage("no.");
	}

	@Override
	public void createRespawns() {
		
		List<Respawn> respawns = new ArrayList<>();
		
		Respawn forPlayers = new Respawn(null) { // set the players, ofc it shouldn't be null
			
			@Override
			public void execute(Player target) {
			}
		};
		
		respawns.add(forPlayers);
		this.respawns = respawns;
	}

	@Override
	public List<Respawn> getRespawns() {
		
		return respawns;
	}
	

}
