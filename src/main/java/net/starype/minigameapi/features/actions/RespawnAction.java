package net.starype.minigameapi.features.actions;

import java.util.List;

import net.starype.minigameapi.features.subfeature.Respawn;

/**
 * RespawnAction is an interface used to add Respawn instances
 * @author Askigh
 *
 */
public interface RespawnAction {
		
	List<Respawn> getRespawns();
	
	default void addRespawn(Respawn respawn) {
		
		getRespawns().add(respawn);
	}
}