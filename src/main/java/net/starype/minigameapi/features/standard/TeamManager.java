package net.starype.minigameapi.features.standard;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import net.starype.minigameapi.core.Feature;
import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.subfeature.Team;

/**
 * TeamManager is a simple Feature which allows you to add teams in your game.
 * This class contains also a list of {@link Team} as under features
 * 
 * @author Askigh
 *
 */
public class TeamManager implements Feature {

	private MiniGameCore source;
	private List<Team> teams;
	
	public TeamManager(MiniGameCore source) {
		this.source = source;
	}
	
	public Team addNewTeam(String name, ChatColor color) {
		
		return addNewTeam(name, color, null);
	}
	
	public Team addNewTeam(String name, ChatColor color, Location spawn) {
		
		Team team = new Team(name, color);
		team.addSpawn(spawn);
		teams.add(team);
		return team;
	}
	
	public Team getTeamByName(String name) {
		
		for(Team t : teams)
			if(t.getName().equals(name))
				return t;
		
		return null;
	}
	
	public Team getTeamByColor(ChatColor color) {
		
		for(Team t : teams)
			if(t.getColor().equals(color))
				return t;
		
		return null;
	}
	
	@Override
	public Class<? extends Feature> getFeature() {
		return getClass();
	}

	@Override
	public void addAsFeature() {
		
		source.getFeatures().add(this);
	}
	
	public MiniGameCore getSource() {
		
		return source;
	}

}
