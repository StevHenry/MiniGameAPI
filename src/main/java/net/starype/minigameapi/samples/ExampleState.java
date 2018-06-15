package net.starype.minigameapi.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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
		event.getPlayer().sendMessage("join");
	}

	@Override
	public void onLeave(PlayerQuitEvent event) {
		Bukkit.broadcastMessage("leave");
	}

	@Override
	public void createRespawns() {

		List<Respawn> respawns = new ArrayList<>();

		Respawn forPlayers = new Respawn(Arrays.asList(Bukkit.getPlayer("Askigh"))) {

			@Override
			public void execute(Player target) {
				target.setGameMode(GameMode.SPECTATOR);
			}
		};
		Respawn forSpecs = new Respawn(Arrays.asList(Bukkit.getPlayer("Lolilolulolilol"))) {

			@Override
			public void execute(Player target) {
				target.setGameMode(GameMode.SPECTATOR);
			}
		};

		respawns.add(forPlayers);
		respawns.add(forSpecs);
		this.respawns = respawns;
	}

	@Override
	public List<Respawn> getRespawns() {

		return respawns;
	}


}
