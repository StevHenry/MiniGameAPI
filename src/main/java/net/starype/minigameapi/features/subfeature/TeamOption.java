package net.starype.minigameapi.features.subfeature;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.MultiLinkable;
import net.starype.minigameapi.features.standard.TeamManager;
import net.starype.minigameapi.features.types.StandardFeature;
import net.starype.minigameapi.features.types.SubFeature;
import net.starype.minigameapi.multilinkable.Team;

public abstract class TeamOption implements SubFeature, Listener {
	
	private Team source;
	private MiniGameCore core;
	private Plugin main;
	
	public TeamOption(Plugin main, MiniGameCore core) {
		
		this.main = main;
		this.core = core;
	}
		
	@EventHandler
	public void damage(EntityDamageByEntityEvent event) {
		
		if(!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player))
			return;
		
		TeamManager manager = core.getFeature(TeamManager.class).get();
		Player pDamager = (Player) event.getDamager();
		Player pEntity = (Player) event.getEntity();
		
		if(!manager.findTeamByPlayer(pDamager).get().equals(source) 
				|| !manager.findTeamByPlayer(pEntity).get().equals(source))
			return;
		
		manageFriendlyFire(event);
		
	}

	
	protected void manageFriendlyFire(EntityDamageByEntityEvent event) {
		
		event.setCancelled(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends SubFeature> T link(MultiLinkable optionalLink) {
		
		if(!(optionalLink instanceof Team)) 
			
			throw new IllegalStateException("Cannot link to something else that a Team");
		
		this.source = (Team) optionalLink;
		
		main.getServer().getPluginManager().registerEvents(this, main);
		return (T) this;
	}
	
	@Override
	public Class<? extends StandardFeature> getLinkedTo() {
		return null;
	}

}
