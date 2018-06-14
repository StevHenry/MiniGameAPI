package net.starype.minigameapi.features.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import net.starype.core.Feature;
import net.starype.core.MiniGameCore;

public class JoinExitManager implements Feature, Listener {

	private MiniGameCore source;
	private Plugin main;

	public JoinExitManager(MiniGameCore source, Plugin main) {
		
		this.source = source;
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
	}
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		
	}
	
	@Override
	public Class<?> getFeature() {
		return JoinExitManager.class;
	}

	@Override
	public void addAsFeature() {
		
		source.getFeatures().add(this);
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	public MiniGameCore getSource() {
		return source;
	}

}
