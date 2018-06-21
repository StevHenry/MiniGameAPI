package net.starype.minigameapi.sample;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.JoinLeaveAction;
import net.starype.minigameapi.features.polyvalent.ItemInteraction;
import net.starype.minigameapi.features.polyvalent.JoinLeaveManager;
import net.starype.minigameapi.features.polyvalent.RespawnManager;
import net.starype.minigameapi.features.standard.GameDivision;
import net.starype.minigameapi.features.standard.TeamManager;
import net.starype.minigameapi.features.subfeature.Respawn;
import net.starype.minigameapi.features.subfeature.TeamOption;
import net.starype.minigameapi.multilinkable.Team;

/**
 * <p>Here is a simple example of a game created with the API</p>
 * The rules are : When there is 3 Players, the game starts. The Player who finds
 * a nether star wins the game
 * <p>As this example is a very tiny game, we sometimes instanciate the interfaces / abstract classes
 * directly in this class. However, if the game were bigger, using this way of coding would make the code
 * difficult to read.
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
		div.addStep(new EndStateExample(), END);
		div.addAsFeature();		
	}

	public void test1() {
		
		// instance of the MiniGameCore
		MiniGameCore core = new MiniGameCore();
		
		// creation of the StandardFeature
		TeamManager tManager = new TeamManager(core);
		// Adding as feature
		tManager.addAsFeature();

		// Creating a Team (note : Team implements MultiLinkable, it is NOT a feature)
		Team reds = new Team("Red team", ChatColor.RED, core);

		// TeamOption is the SubFeature of Team, we have a multi sub feature architecture
		new TeamOption(this, core) {

			@Override
			public void manageFriendlyFire(EntityDamageByEntityEvent event) {

				((Player) event).getPlayer().sendMessage("A Team mate punched you");
			}
		}.link(reds);
		
		tManager.addNewTeam(reds);
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
		.withDefaultActions(true)
		.link(null);

		new RespawnManager(core, this).addDefaultAction(createRespawns(core));;
	}

	private Respawn createRespawns(MiniGameCore core) {

		List<Player> players = core.getPlayers();

		return new Respawn(players) {

			@Override
			public void execute(Player target) {
				target.sendMessage("You died !");
				target.setGameMode(GameMode.SURVIVAL);
			}

		};
	}

	void test() {

		TeamManager tm = new TeamManager(core);

		Team t = new Team("reds", ChatColor.RED, core);
		tm.addNewTeam(t);

		new TeamOption(this,core) {
		}.link(t);

	}
}
