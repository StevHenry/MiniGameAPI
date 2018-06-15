package net.starype.minigameapi.tests;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.starype.minigameapi.features.GameDivision.StateChangeAction;
import net.starype.minigameapi.features.events.JoinLeaveManager.JoinLeaveAction;

public class ExampleState implements StateChangeAction, JoinLeaveAction {

	@Override
	public void executeWhenSet() {
		//Koukou
	}

	@Override
	public void onJoin(PlayerJoinEvent event) {
		event.getPlayer().sendMessage("wi.");
	}

	@Override
	public void onLeave(PlayerQuitEvent event) {
		Bukkit.broadcastMessage("no.");
	}

}
