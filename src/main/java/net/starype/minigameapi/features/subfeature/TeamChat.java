package net.starype.minigameapi.features.subfeature;

import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.MultiLinkable;
import net.starype.minigameapi.features.standard.TeamManager;
import net.starype.minigameapi.features.types.StandardFeature;
import net.starype.minigameapi.features.types.SubFeature;
import net.starype.minigameapi.multilinkable.Team;

/**
 * TeamChat is a SubFeature linked to the TeamManager. As a TeamChat concerns all the teams, there is no need
 * to instanciate many TeamChat
 * @author Askigh
 *
 */
// TODO : Replace Bukkit.broadcastMessage (line 51 in event) by a more precise broadcast (only in current world e.g)
public class TeamChat implements SubFeature, Listener {

	private Plugin main;
	private String globalCommand;
	private TeamManager manager;
	private MiniGameCore core;
	public static final String NO_COMMAND = "No command set";

	public TeamChat(MiniGameCore core, Plugin main) {

		this.main = main;
		this.core = core;
	}

	@EventHandler
	public void onSendMessage(AsyncPlayerChatEvent event) {

		Player player = event.getPlayer();
		Optional<Team> potentialTeam = manager.findTeamByPlayer(player);

		event.setCancelled(true);
		String message = event.getMessage();

		if(!potentialTeam.isPresent()) {

			if(message.startsWith(globalCommand) || globalCommand.equals(NO_COMMAND))
				Bukkit.broadcastMessage(globalCommand+" "+message);
			
		}
		
		else {
			
			Team current = potentialTeam.get();
			
			if(!message.startsWith(current.getChatCommand()))
				return;
			
			for(Player teammate : current.getPlayers()) 
				teammate.sendMessage(current.getChatPrefix()+" "+message);

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends SubFeature> T link(MultiLinkable optionalAction) {

		for(Team t : manager.getTeams())
			if(t.getChatCommand() == null) throw new IllegalStateException("Command missing in one or more team(s)");

		Optional<TeamManager> tManager = core.getFeature(TeamManager.class);

		if(tManager.isPresent()) {

			this.manager = tManager.get();
			main.getServer().getPluginManager().registerEvents(this, main);
		}
		return (T) this;
	}

	@Override
	public Class<? extends StandardFeature> getLinkedTo() {
		return TeamManager.class;
	}

	public String getGlobalPrefix() {
		return globalCommand;
	}

	public void setGlobalPrefix(String globalPrefix) {
		this.globalCommand = globalPrefix;
	}

}
