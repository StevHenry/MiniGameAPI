package net.starype.minigameapi.multilinkable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.MultiLinkable;
import net.starype.minigameapi.features.standard.TeamManager;
import net.starype.minigameapi.features.subfeature.TeamOption;

/**
 * <p>Team is a basic class containing all the information you could need to add teams into your game</p>
 * <p>Do not call the constructor unless you have a good reason, you should use <code>addNewTeam</code> from {@link TeamManager}  
 * instead.</p>
 * 
 * @author Askigh
 * @author Steven
 */
public class Team implements MultiLinkable {
	
	private String name;
	private ChatColor color;
	private Location spawn;
	private List<Player> players;
	private String chatPrefix;
	private String chatCommand;
	private TeamOption option;
	
	public Team(String name, ChatColor color, MiniGameCore core) {
		
		this.setName(name);
		this.setColor(color);
		this.players = new ArrayList<>();
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

	public String getChatPrefix() {
		return chatPrefix;
	}

	public Team withChatPrefix(String chatPrefix) {
		
		this.chatPrefix = chatPrefix;
		return this;
	}

	public String getChatCommand() {
		return chatCommand;
	}

	public Team withChatCommand(String chatCommand) {
		this.chatCommand = chatCommand;
		return this;
	}

	public TeamOption getOption() {
		return option;
	}

	public void setOption(TeamOption option) {
		this.option = option;
	}

}
