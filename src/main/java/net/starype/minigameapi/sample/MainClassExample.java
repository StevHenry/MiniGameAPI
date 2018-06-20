package net.starype.minigameapi.sample;

import java.util.Arrays;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.JoinLeaveAction;
import net.starype.minigameapi.features.polyvalent.ItemInteraction;
import net.starype.minigameapi.features.polyvalent.JoinLeaveManager;
import net.starype.minigameapi.features.polyvalent.RespawnManager;
import net.starype.minigameapi.features.polyvalent.listed.Respawn;
import net.starype.minigameapi.features.standard.GameDivision;

/**
 * <p>Here is a simple example of a game created with the API</p>
 * The rules are : When there is 3 Players, the game starts. The Player who finds
 * a nether star wins the game
 * <p>As this example is a very tiny game, we sometimes instanciate the interfaces / abstract classes
 * directly in this class. However, if the game were bigger, using this way of coding would make the code
 * difficult to be read.
 * Therefore we highly recommand you to create rather extra classes that implement them. </p>
 * 
 * @author Askigh
 */
public class MainClassExample extends JavaPlugin {
	
	private MiniGameCore core;
	private static final int WAITING = 0;
	private static final int PLAYING = 1;
	private static final int END = 2;
	
	@Override
	public void onEnable() {
		
		this.core = new MiniGameCore();
		
		GameDivision div = new GameDivision(core);

		initPolyvalentFeatures();
		
		div.addStep(new WaitingStateExample(core),WAITING);
		div.addStep(new PlayingStateExample(core), PLAYING);
		div.addStep(new EndStateSample(), END);
		div.addAsFeature();		
	}

	private void initPolyvalentFeatures() {
		
		JoinLeaveAction defaultAction = new JoinLeaveAction() {
			
			@Override
			public void onLeave(PlayerQuitEvent event) {
			}
			
			@Override
			public void onJoin(PlayerJoinEvent event) {
				
				Player player = event.getPlayer();
				player.setGameMode(GameMode.SPECTATOR);
				player.sendMessage("The Game has already started...");
			}
		};
		
		new ItemInteraction(core, this).link(null);
		
		new JoinLeaveManager(core, this, defaultAction)
			.withDefaultActions(false)
			.link(null);
		
		new RespawnManager(core, this, Arrays.asList(createRespawns(core)));
	}

	private Respawn createRespawns(MiniGameCore core) {

		List<Player> players = core.getPlayers();
		
		return new Respawn(core, players) {

			@Override
			public void execute(Player target) {
				target.sendMessage("You died !");
				target.setGameMode(GameMode.SURVIVAL);
			}
			
		}.link(null);
	}
}
