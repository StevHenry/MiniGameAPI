package net.starype.minigameapi.features.subfeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.GameAction;
import net.starype.minigameapi.features.standard.TeamManager;
import net.starype.minigameapi.features.types.StandardFeature;
import net.starype.minigameapi.features.types.SubFeature;

/**
 * <p>Team is a basic class containing all the information you could need to add teams into your game</p>
 * <p>Do not call the constructor unless you have a good reason, you should use <code>addNewTeam</code> from {@link TeamManager}  
 * instead.</p>
 * 
 * @author Askigh
 * @author Steven
 */
public class Team implements SubFeature {
	
	private String name;
	private ChatColor color;
	private Location spawn;
	private List<Player> players;
	private TeamManager manager;
	private MiniGameCore core;
	
	public Team(String name, ChatColor color, MiniGameCore core) {
		
		this.setName(name);
		this.setColor(color);
		this.players = new ArrayList<>();
		this.core = core;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends SubFeature> T link(GameAction optionalAction) {
		
		Optional<TeamManager> teamManager = core.getFeature(TeamManager.class);
		
		if(teamManager.isPresent()) {
			this.manager = teamManager.get();
			manager.addNewTeam(this);
		} else throw new IllegalStateException("Cannot link if TeamManager is undefined");
		
		return (T) this;
	}

	@Override
	public Class<? extends StandardFeature> getLinkedTo() {
		
		return TeamManager.class;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ChatColor getColor() {
		return color;
	}

	public void setColor(ChatColor color) {
		this.color = color;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void addSpawn(Location location) {
		this.spawn = location;
	}
	
	public Optional<Location> getSpawn() {
		
		return Optional.ofNullable(spawn);
	}

}
