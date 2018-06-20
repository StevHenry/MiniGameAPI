package net.starype.minigameapi.features.polyvalent.listed;

import java.util.List;
import java.util.Optional;

import org.bukkit.entity.Player;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.GameAction;
import net.starype.minigameapi.features.actions.RespawnAction;
import net.starype.minigameapi.features.polyvalent.RespawnManager;
import net.starype.minigameapi.features.types.StandardFeature;
import net.starype.minigameapi.features.types.SubFeature;

public abstract class Respawn implements SubFeature {
	
	private List<Player> players;
	private MiniGameCore core;

	public Respawn(MiniGameCore core, List<Player> players) {
		
		this.players = players;
		this.core = core;
	} 

	public abstract void execute(Player target);

	public List<Player> getPlayers() {
		return players;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends SubFeature> T link(GameAction optionalAction) {
		
		Optional<RespawnManager> respawnManager = core.getFeature(RespawnManager.class);
		
		if(optionalAction == null) {
			
			if(respawnManager.isPresent())
				respawnManager.get().addDefaultAction(this);
			else
				System.err.println("Error : RespawnManager is undefined");
			
			return (T) this;
		} 
		
		if(optionalAction instanceof RespawnAction)
			((RespawnAction) optionalAction).getRespawns().add(this);
		else 
			System.err.println("Error : GameAction has to be a RespawnAction as well");
		
		return (T) this;
	}
	
	@Override
	public Class<? extends StandardFeature> getLinkedTo() {
		return RespawnManager.class;
	}
}
