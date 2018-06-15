package net.starype.minigameapi.features.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import net.starype.minigameapi.core.Feature;
import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.GameDivision;

public class JoinExitManager implements Feature, Listener {

	private MiniGameCore source;
	private Plugin main;
	private boolean linked = false;
	private JoinExitAction join;
	private JoinExitAction leave;
	private GameDivision division;
	private List<JoinExitAction> joinActions;
	private List<JoinExitAction> leaveActions;

	public JoinExitManager(MiniGameCore source, Plugin main) {

		this.source = source;
		this.main = main;
		this.joinActions = new ArrayList<>();
		this.leaveActions = new ArrayList<>();
	}
	
	public void addJoinAction(JoinExitAction action, int index) {
		
		if(!linked) {
			System.err.println("Error : JoinExitManager is not linked");
			return;
		}
		joinActions.add(index, action);
	}
	
	public void addLeaveAction(JoinExitAction action, int index) {
		
		if(!linked) {
			System.err.println("Error : JoinExitManager is not linked");
			return;
		}
		
		leaveActions.add(index, action);
	}
	
	public void link() {

		linked = true;
		division = source.getFeature(GameDivision.class);
	}

	public void setGlobalAction(JoinExitAction join, JoinExitAction leave) {

		if(linked) 
			System.err.println("Error in class JoinExitManager : Cannot set a global action "
					+ "if instance is linked");
		else {
			this.join = join;
			this.leave = leave;
		}

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		if(!linked) {
			join.execute(player);
			return;
		}

		int current = division.getCurrentStepIndex();

		joinActions.get(current).execute(player);

	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {

		Player player = event.getPlayer();

		if(!linked) {
			leave.execute(player);
			return;
		}
		int current = division.getCurrentStepIndex();

		leaveActions.get(current).execute(player);
	}

	@Override
	public Class<? extends Feature> getFeature() {
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

	public boolean isLinked() {
		return linked;
	}

	public interface JoinExitAction {

		void execute(Player player);
	}
}
