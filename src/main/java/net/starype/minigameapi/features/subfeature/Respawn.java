package net.starype.minigameapi.features.subfeature;

import java.util.List;

import org.bukkit.entity.Player;

/**
 * 
 * @author Askigh
 *
 */
public abstract class Respawn {
	
	private List<Player> players;

	public Respawn(List<Player> players) {
		
		this.players = players;
	} 

	public abstract void execute(Player target);

	public List<Player> getPlayers() {
		return players;
	}

}
/*	
@SuppressWarnings("unchecked")
@Override
public <T extends SubFeature> T link(MultiLinkable optionalAction) {
	
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
}*/