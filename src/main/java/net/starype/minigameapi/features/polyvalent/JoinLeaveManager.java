package net.starype.minigameapi.features.polyvalent;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.GameAction;
import net.starype.minigameapi.features.actions.JoinLeaveAction;
import net.starype.minigameapi.features.standard.GameDivision;
import net.starype.minigameapi.features.types.StandardFeature;
import net.starype.minigameapi.features.types.PolyvalentFeature;
import net.starype.minigameapi.features.types.SubFeature;

/**
 * 
 * <p>JoinLeaveManager is a class that can be used as a feature alone or as an under feature
 * depending of the {@link GameDivision} instance, to manage connections and disconnections of players.</p>
 * 
 * <p>It also implements the {@link Listener} interface because it contains a PlayerJoinEvent and a PlayerQuitEvent.</p>
 * @author Askigh
 * @author Steven
 */
public class JoinLeaveManager implements Listener, PolyvalentFeature {

	// null if JoinLManager is not linked
	private GameDivision divisor;
	
	private Plugin main;
	/*
	 *  Default action containing onJoin() and onLeave() if current state isn't a {@link State} 
	 *  Defined in the constructor below
	 *  Used in onJoin() and onLeave() local methods
	 */
	private JoinLeaveAction defaultAction;
	
	// The MiniGame instance you want to complete with this feature, defined in the constructor
	private MiniGameCore core;
	
	//Whether the instance is linked or not. See link() method for further information
	private boolean linked;
	
	/*
	 * Wheter the instance use the default actions even if it's linked
	 * See withDefaultActions(boolean b) method for further information
	 */
	private boolean defaultActionIfLinked = false;
	
	/**
	 * 
	 * @param core
	 * 	The MiniGame instance you want to complete with this feature
	 * @param main
	 * 	The Instance of the class extending JavaPlugin. Necessary to register the events
	 * @param defaultAction
	 * 	Define the default action applied, see onJoin / onLeave functions for further information. 
	 * May be null without any problems. 
	 */
	public JoinLeaveManager(MiniGameCore core, Plugin main, JoinLeaveAction defaultAction) {
		
		this.defaultAction = defaultAction;
		this.core = core;
		this.main = main;
		linked = false;
	}

	/**
	 * If link function is called, it means that the defaultAction won't be called anymore except if
	 * withDefaultActions(true) is called, and no action is defined for the current state.
	 * @throws IllegalStateException if GameDivision has not already been added as a feature
	 * in the MiniGameCore instance
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends SubFeature> T link(GameAction optionalAction) {
		
		linked = true;
		divisor = core.getFeature(GameDivision.class).get();
		
		if(divisor == null) throw new IllegalStateException("Cannot link if GameDivision is undefined");
		return (T) this;
	}
	
	/**
	 * 
	 * @param withDefaultAction : Whether the programm should use the default action if instance if linked
	 * but no specific action is defined for the current state
	 * @return the instance of JoinLManager used to call this function 
	 */
	public JoinLeaveManager withDefaultActions(boolean withDefaultAction) {
		
		defaultActionIfLinked = withDefaultAction;
		return this;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		/* getCurrentState() return either the instance of a user class which implements StateChangeAction
		 * or an instance of StateSample / JoinLeaveSample (See net.starype.minigameapi.samples)
		 * This instance may also implements JoinLeaveState, if it's the case we can
		 * cast and apply onJoin() function.
		 */
		if (linked && divisor.getCurrentState() instanceof JoinLeaveAction) {
			((JoinLeaveAction) divisor.getCurrentState()).onJoin(e);
		} else
			/*
			 * If the manager is not linked, we always want to call the default onJoin(),
			 * except if it's null. Although, we could also want to call the default onJoin()
			 * if the manager is linked but no precise action is defined for the current state and
			 * defaultActionIfLinked is true
			 */
			if(!linked || (linked && defaultActionIfLinked))
				if(defaultAction != null)
					defaultAction.onJoin(e);
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		
		/* getCurrentState() return either the instance of a user class which implements StateChangeAction
		 * or an instance of StateSample / JoinLeaveSample (See net.starype.minigameapi.samples)
		 * This instance may also implements JoinLeaveState, if it's the case we can
		 * cast and apply onLeave() function.
		 */
		if (linked && divisor.getCurrentState() instanceof JoinLeaveAction) {
			
			((JoinLeaveAction) divisor.getCurrentState()).onLeave(e);
		} else
			/*
			 * If the manager is not linked, we always want to call the default onLeave(),
			 * except if it's null. Although, we could also want to call the default onLeave()
			 * if the manager is linked but no precise action is defined for the current state and
			 * defaultActionIfLinked is true
			 */
			if(!linked || (linked && defaultActionIfLinked))
				if(defaultAction != null)
					defaultAction.onLeave(e);
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
			core.getFeatures().add(this);
		else System.err.println("Cannot add a linked feature");
		
		Bukkit.getPluginManager().registerEvents(this, main);
	}

	@Override
	public Class<? extends StandardFeature> getLinkedTo() {
		return GameDivision.class;
	}

}
