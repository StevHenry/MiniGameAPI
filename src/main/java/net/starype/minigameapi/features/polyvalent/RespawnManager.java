package net.starype.minigameapi.features.polyvalent;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.MultiLinkable;
import net.starype.minigameapi.features.actions.RespawnAction;
import net.starype.minigameapi.features.standard.GameDivision;
import net.starype.minigameapi.features.subfeature.Respawn;
import net.starype.minigameapi.features.types.StandardFeature;
import net.starype.minigameapi.features.types.PolyvalentFeature;
import net.starype.minigameapi.features.types.SubFeature;
import net.starype.minigameapi.multilinkable.StateChangeAction;

/**
 * <p>RespawnManager is a Polyvalent Feature, therefore it that can be used as a feature alone or as a sub feature
 * depending of the {@link GameDivision} instance, to manage respawns of players.</p>
 * 
 * <p>It also implements Listener interface, because we use a PlayerRespawnEvent and a EntityDamageEvent</p>
 * @author Askigh
 *
 */
public class RespawnManager implements Listener, PolyvalentFeature {
	
	// null if JoinLManager is not linked
	private GameDivision division;
	
	// The MiniGame instance you want to complete with this feature, defined in the constructor
	private MiniGameCore source;
	
	// JavaPlugin instance, defined in the constructor
	private Plugin main;
	
	// Two Respawn detections are available, when the player dies or when his life equals 0
	private RespawnMode mode;
	
	//Whether the instance is linked or not. See link() method for further information
	private boolean linked;
	
	/*
	 * Wheter the instance use the default actions even if it's linked
	 * See withDefaultActions(boolean b) method for further information
	 */
	private boolean defaultActionIfLinked = false;
	
	/*
	 * Default List of actions
	 * Used if the instance is not linked, or if the instance is linked and defaultActionIfLinked is true
	 */
	private List<Respawn> defaultActions; 

	/**
	 * 
	 * @param source
	 * 	The MiniGame instance you want to complete with this feature
	 * @param main
	 * 	The JavaPlugin instance, used to register the Listener
	 */
	public RespawnManager(MiniGameCore source, Plugin main) {

		this.source = source;
		this.main = main;
		this.defaultActions = new ArrayList<Respawn>();
	}

	/**
	 * 
	 * @param withDefaultActions : Whether the programm should use the default actions if instance if linked
	 * but no specific action is defined for the current state
	 * @return the instance of JoinLManager used to call this function 
	 */
	public RespawnManager withDefaultActions(boolean withDefaultActions) {

		this.defaultActionIfLinked = withDefaultActions;
		return this;
	}

	/**
	 * Sets the default actions applied
	 * @param actions : The list of Respawn actions applied if a default action has to be called
	 */
	public void setDefaultActions(List<Respawn> actions) {

		this.defaultActions = actions;
	}

	public void addDefaultAction(Respawn respawn) {
		
		this.defaultActions.add(respawn);
	}
	
	public void addAction(Respawn respawn, StateChangeAction stateAction) {
		
		if(stateAction instanceof RespawnAction)
			((RespawnAction) stateAction).addRespawn(respawn);
		
		else throw new IllegalStateException("The StateChangeAction has to be a RespawnAction as well");
	}

	/**
	 * 
	 * @param target, the player that respawns
	 */
	private void execute(Player target) {

		if(linked && division.getCurrentState() instanceof RespawnAction) {
			for(Respawn respawn : ((RespawnAction) division.getCurrentState()).getRespawns()) {
				
				if(respawn.getPlayers().contains(target))
					respawn.execute(target);
			}
			
		} else if(!linked || (linked && defaultActionIfLinked)) {
			
				for(Respawn defaultAction : defaultActions) {

					if(defaultAction.getPlayers().contains(target))
						defaultAction.execute(target);
				}
				
				return;
			}
	}

	@EventHandler
	public void respawn(PlayerRespawnEvent event) {

		if(mode != RespawnMode.AFTER_DEATH)
			return;
		
		execute(event.getPlayer());
	}

	@EventHandler
	public void fatalDamage(EntityDamageEvent event) {

		if(mode != RespawnMode.ZERO_LIFE_POINTS || !(event.getEntity() instanceof Player))
			return;
		
		if(true/*TODO : Add the correct condition*/)
			execute((Player) event.getEntity());
	}

	/**
	 * @return the respawn mode that can be defined at {@link RespawnManager#setMode(RespawnMode)}
	 */
	public RespawnMode getMode() {
		return mode;
	}

	/**
	 * @param mode, the new mode that has to be defined
	 */
	public void setMode(RespawnMode mode) {
		this.mode = mode;
	}

	/**
	 * @return the mini game source
	 */
	public MiniGameCore getSource() {
		return source;
	}

	/**
	 * Enum that lists the two possible RespawnModes
	 */
	public enum RespawnMode {

		AFTER_DEATH, ZERO_LIFE_POINTS
	}
	
	/**
	 * <p>If link function is called, it means that the defaultAction won't be called anymore except if
	 * withDefaultActions(true) is called, and no action is defined for the current state.</p>
	 * 
	 * @throws IllegalStateException if GameDivision has not already been added as a feature
	 * in the MiniGameCore instance
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends SubFeature> T link(MultiLinkable optionalAction) {

		linked = true;
		division = source.getFeature(GameDivision.class).get();
		
		if(division == null) throw new IllegalStateException("Cannot link if GameDivision is undefined");
		return (T) this;
	}

	@Override
	public Class<? extends StandardFeature> getLinkedTo() {

		return GameDivision.class;
	}
	
	/**
	 *  Overrided method from the Feature interface
	 */
	@Override
	public Class<? extends StandardFeature> getFeature() {
		return getClass();
	}

	/**
	 *  <p>Overrided method from the {@link StandardFeature} interface </p>
	 *  <p>Also adds the instance as an event using the JavaPlugin parameter defined
	 *  in the constructor</p>
	 */
	@Override
	public void addAsFeature() {

		if(!linked)
			source.getFeatures().add(this);
		
		else System.err.println("Cannot add a linked feature");
		
		Bukkit.getPluginManager().registerEvents(this, main);
	}

}