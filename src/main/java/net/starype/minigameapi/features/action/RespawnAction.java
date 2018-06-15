package net.starype.minigameapi.features.action;

import java.util.List;

import net.starype.minigameapi.features.polyvalent.Respawn;

/**
 * RespawnAction is an interface used to add Respawn instances
 * @author Askigh
 *
 */
public interface RespawnAction {
	
	void createRespawns();	
	
	List<Respawn> getRespawns();
}