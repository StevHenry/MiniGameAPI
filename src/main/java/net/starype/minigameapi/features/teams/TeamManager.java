package net.starype.minigameapi.features.teams;

import java.util.List;

import org.bukkit.Location;

import net.starype.core.Feature;
import net.starype.core.MiniGameCore;

public class TeamManager implements Feature {

	private MiniGameCore source;
	private List<Team> teams;
	
	public TeamManager(MiniGameCore source) {
		this.source = source;
	}
	
	public Team addNewTeam(String name, String color) {
		
		return addNewTeam(name, color, null);
	}
	
	public Team addNewTeam(String name, String color, Location spawn) {
		
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
	
	public Team getTeamByColor(String color) {
		
		for(Team t : teams)
			if(t.getColor().equals(color))
				return t;
		
		return null;
	}
	
	@Override
	public Class<?> getFeature() {
		return TeamManager.class;
	}

	@Override
	public void addAsFeature() {
		
		source.getFeatures().add(this);
	}
	
	public MiniGameCore getSource() {
		
		return source;
	}

}
