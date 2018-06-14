package net.starype.minigameapi.tests;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.GameDivision;
import net.starype.minigameapi.features.GameDivision.StateChangeAction;
import net.starype.minigameapi.features.teams.TeamManager;

/**
 * 
 * @usage : This class is just used to do tests, it's not usefull at all
 *
 */
public class Test implements Listener {
	
	private MiniGameCore core;
	public void test() {
		
		core = new MiniGameCore();
		GameDivision division = new GameDivision(3, core);
		division.addAsFeature();

		division.addStep("STARTING", new StateChangeAction() {
			
			@Override
			public void execute() {
			}
		});
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		TeamManager manager = new TeamManager(core);
		manager.addAsFeature();
		
		if(true) {
			
			GameDivision division = core.getFeature(GameDivision.class);
			division.execute();
		}
	}

}
