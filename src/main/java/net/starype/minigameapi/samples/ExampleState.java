package net.starype.minigameapi.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.starype.minigameapi.features.actions.ItemActionnable;
import net.starype.minigameapi.features.actions.JoinLeaveAction;
import net.starype.minigameapi.features.actions.RespawnAction;
import net.starype.minigameapi.features.actions.StateChangeAction;
import net.starype.minigameapi.features.polyvalent.listed.Respawn;
import net.starype.minigameapi.features.subfeature.CustomItem;

public class ExampleState implements StateChangeAction, JoinLeaveAction, RespawnAction, ItemActionnable {

	private List<Respawn> respawns;
	private List<CustomItem> items;

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

	// this doesn't have to be in this class
	public void createRespawns() {

		List<Respawn> respawns = new ArrayList<>();

		Respawn forPlayers = new Respawn(null, Arrays.asList(Bukkit.getPlayer("Askigh"))) {

			@Override
			public void execute(Player target) {
				target.setGameMode(GameMode.ADVENTURE);
			}
		};
		
		Respawn forSpecs = new Respawn(null, Arrays.asList(Bukkit.getPlayer("Lolilolulolilol"))) {

			@Override
			public void execute(Player target) {
				target.setGameMode(GameMode.SPECTATOR);
			}
		};

		respawns.add(forPlayers);
		respawns.add(forSpecs);
		
		this.respawns = respawns;
		forPlayers.link(this);
		forSpecs.link(this);
	}

	@Override
	public List<Respawn> getRespawns() {

		return respawns;
	}

	@Override
	public List<CustomItem> getItems() {
		return items;
	}


}
