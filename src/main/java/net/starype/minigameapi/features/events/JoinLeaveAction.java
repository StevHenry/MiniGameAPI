package net.starype.minigameapi.features.events;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public interface JoinLeaveAction {
	
	void onJoin(PlayerJoinEvent event);
	
	void onLeave(PlayerQuitEvent event);

}