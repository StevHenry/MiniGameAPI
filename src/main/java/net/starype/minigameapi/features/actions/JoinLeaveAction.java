package net.starype.minigameapi.features.actions;

import net.starype.minigameapi.multilinkable.StateChangeAction;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.starype.minigameapi.features.standard.GameDivision;

/**
 * <p>Interace that is used in the {@link GameDivision#addStep(StateChangeAction)} function in class
 * {@link GameDivision}</p>
 * 
 * @author Askigh
 * @author Steven
 */
public interface JoinLeaveAction {
	
	void onJoin(PlayerJoinEvent event);
	
	void onLeave(PlayerQuitEvent event);

}