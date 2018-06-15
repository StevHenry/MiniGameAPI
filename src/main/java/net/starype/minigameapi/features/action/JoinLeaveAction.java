package net.starype.minigameapi.features.action;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.starype.minigameapi.features.standard.GameDivision;

/**
 * Interace that is used in the {@link GameDivision#addStep(net.starype.minigameapi.features.GameDivision.StateChangeAction)} function in class 
 * {@link GameDivision} </p>
 * 
 * @author Askigh & Steven
 *
 */
public interface JoinLeaveAction {
	
	void onJoin(PlayerJoinEvent event);
	
	void onLeave(PlayerQuitEvent event);

}