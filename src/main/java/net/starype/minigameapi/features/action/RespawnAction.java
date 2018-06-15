package net.starype.minigameapi.features.action;

import java.util.List;

import net.starype.minigameapi.features.polyvalent.Respawn;

public interface RespawnAction {
	
	void createRespawns();	
	
	List<Respawn> getRespawns();
}