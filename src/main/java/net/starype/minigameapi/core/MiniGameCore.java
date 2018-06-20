package net.starype.minigameapi.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.bukkit.entity.Player;

import net.starype.minigameapi.features.types.StandardFeature;

public class MiniGameCore {
	
	/*
	 * The List of all the players in the game
	 * You mustn't use it if you don't want to
	 * It's a content that can be useful, to escape keeping an instance of a list of players everywhere
	 */
	private List<Player> players;
	

	/**
	 * This Set regroups all the used {@link StandardFeature}s
	 */
	private Set<StandardFeature> features;
	
	/**
	 * Create a simple MiniGameCore instance, and init the features Set + Player list
	 */
	public MiniGameCore() {
		
		this.features = new HashSet<StandardFeature>();
		this.players = new ArrayList<Player>();
	}
	
	/**
	 * @return the list of all the present features.
	 * <p>If you can't find a specific feature in the Set, 
	 * make sure you've called addAsFeature() function earlier</p>
	 */
	public Set<StandardFeature> getFeatures() {	
		return features;
	}
	
	/**
	 * 
	 * @param source
	 * 	The feature you want to get using {@code NameOfFeature.class}
	 * @return The needed feature
	 */
	@SuppressWarnings("unchecked")
	public <T extends StandardFeature> Optional<T> getFeature(Class<T> source) {
		
		for(StandardFeature f : features)
			if(f.getFeature().equals(source))
				return Optional.of((T) f);
		
		return Optional.empty();
	}
	
	/**
	 * 
	 * @return The List of players in the game
	 */
	public List<Player> getPlayers() {
		
		return players;
	}
	
	/**
	 * Adds a player into the list
	 * @param player the player to add
	 */
	public void addPlayer(Player player) {
		
		players.add(player);
	}
	
	/**
	 * Removes a player into the list
	 * @param player the player to remove
	 */
	public void removePlayer(Player player) {
		
		players.remove(player);
	}
}
