package net.starype.minigameapi.tests;

import org.bukkit.plugin.java.JavaPlugin;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.GameDivision;
import net.starype.minigameapi.features.events.JoinLeaveManager;

/**
 * 
 * @usage : This class is just used to do tests, it's not usefull at all
 *
 */
public class Test extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		GameDivision div = new GameDivision(core);
		//TODO ajouter la feature au core
		div.addStep(new ExampleState()); //là, c'est un state qui gère les events
		div.addStep(new ExampleState2()); //là non
		
		
		/*
		 On peut créer une class dédiée mais pour l'exemple j'instancie direct comme ça 
		 */
	}
	
	private MiniGameCore core;
	private static final int WAITING = 0;
	private static final int STARTING = 1;
	private static final int PLAYING = 2;
	
	public void test() {
		
		core.getFeature(null);
		GameDivision div = new GameDivision(core);
		div.addAsFeature();
		
		div.addStep(null, WAITING);
		div.addStep(null, STARTING);
		div.addStep(null, PLAYING);
		
	}
	
	public void otherTest() {
		
		@SuppressWarnings("unused")
		JoinLeaveManager jlManager = new JoinLeaveManager(core, null, null)
				.link()
				.withDefaultActions(true);
	}
	

}
