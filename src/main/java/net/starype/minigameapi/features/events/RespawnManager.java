package net.starype.minigameapi.features.events;

import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import net.starype.core.Feature;
import net.starype.core.MiniGameCore;

public class RespawnManager implements Feature, Listener {

	private MiniGameCore source;
	private Plugin main;
	private RespawnMode mode;
	private Map<List<Player>, RespawnAction> actionsByPlayers;

	public RespawnManager(MiniGameCore source, Plugin main) {
		
		this.source = source;
		this.main = main;
	}
	
	public void addAction(List<Player> players, RespawnAction action) {
		
		actionsByPlayers.put(players, action);
	}
	
	public void execute(Player target) {
		
		for(List<Player> players : actionsByPlayers.keySet()) {
			
			if(players.contains(target)) {
				
				for(RespawnAction action : actionsByPlayers.values())
					if(actionsByPlayers.get(players).equals(action))
						action.execute(target);
			}
		}
	}
	
	@Override
	public Class<?> getFeature() {
		return null;
	}

	@Override
	public void addAsFeature() {
		
		source.getFeatures().add(this);
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void respawn(PlayerRespawnEvent event) {
		
		if(mode != RespawnMode.AFTER_DEATH)
			return;
	}
	
	@EventHandler
	public void fatalDamage(EntityDamageEvent event) {
		
		if(mode != RespawnMode.ZERO_LIFE_POINTS || !(event.getEntity() instanceof Player))
			return;
	}

	public RespawnMode getMode() {
		return mode;
	}
	
	public void setMode(RespawnMode mode) {
		this.mode = mode;
	}

	public MiniGameCore getSource() {
		
		return source;
	}
	
	public enum RespawnMode {
		
		AFTER_DEATH, ZERO_LIFE_POINTS
	}
	
	public interface RespawnAction {

		void execute(Player source);
	}
}
