package net.starype.minigameapi.features.actions;

import java.util.List;

import net.starype.minigameapi.features.polyvalent.listed.Respawn;

/**
 * RespawnAction is an interface used to add Respawn instances
 * @author Askigh
 *
 */
public interface RespawnAction extends GameAction {
		
	List<Respawn> getRespawns();
}