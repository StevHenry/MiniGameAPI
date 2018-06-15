package net.starype.minigameapi.tests;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.starype.minigameapi.core.Feature;
import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.GameDivision;
import net.starype.minigameapi.features.events.JoinExitManager;
import net.starype.minigameapi.features.events.JoinLManager;
import net.starype.minigameapi.features.events.JoinLeaveState;
import net.starype.minigameapi.samples.JoinLeaveSample;

/**
 * 
 * @usage : This class is just used to do tests, it's not usefull at all
 *
 */
public class Test extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		new JoinLeaveSample() {
			
			@Override
			public void onLeave(PlayerQuitEvent event) {
			}
			
			@Override
			public void onJoin(PlayerJoinEvent event) {
			}
			
			@Override
			public void executeWhenSet() {
			}
		};
		GameDivision div = new GameDivision(core);
		//TODO ajouter la feature au core
		div.addStep(new ExampleState()); //là, c'est un state qui gère les events
		div.addStep(new ExampleState2()); //là non
		
		Bukkit.getPluginManager().registerEvents(new JoinLManager(core, null, new JoinLeaveState() {
			
			@Override
			public void onLeave(PlayerQuitEvent event) {
				event.setQuitMessage("il a leave lekon");
			}
			
			@Override
			public void onJoin(PlayerJoinEvent event) {
				event.setJoinMessage("il a join lekon");
			}
		}), this);
		
		/*
		 On peut créer une class dédiée mais pour l'exemple j'instancie direct comme ça 
		 */
	}
	
	private MiniGameCore core;
	private static final int WAITING = 0;
	private static final int STARTING = 1;
	private static final int PLAYING = 2;
	
	public void test() {
		
		GameDivision div = new GameDivision(core);
		div.addAsFeature();
		
		div.addStep(null, WAITING);
		div.addStep(null, STARTING);
		div.addStep(null, PLAYING);

		JoinExitManager joinexit = new JoinExitManager(core, null);
		joinexit.link();
		
		joinexit.addJoinAction(null, WAITING);
		joinexit.addJoinAction(null, STARTING);
		joinexit.addJoinAction(null, PLAYING);
		
		joinexit.addLeaveAction(null, WAITING);
		joinexit.addLeaveAction(null, STARTING);
		joinexit.addLeaveAction(null, PLAYING);
		
		
	}
	
	public void otherTest() {
		
		JoinLManager jlManager = new JoinLManager(core, null, null)
				.link()
				.withDefaultActions(true);
	}
	

}
