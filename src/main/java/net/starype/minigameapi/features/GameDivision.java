package net.starype.minigameapi.features;

import java.util.ArrayList;
import java.util.List;

import net.starype.minigameapi.core.Feature;
import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.events.JoinLeaveAction;
import net.starype.minigameapi.features.events.JoinLeaveManager;

/**
 * GameDivision is feature allowing you to cut your game in different parts.
 * Each part is defined in a list, belongs to an attribute number corresponding to the index of the list. <p>
 * A part is defined by a {@link StateChangeAction} instance, we highly recommand you to create your own class
 * implementing StateChangeAction but you can also make a direct instance of it and completing the methods overrided.
 * 
 * GameDivisions owns an under feature named JoinLeaveManager. 
 * To use this under feature, you basically
 * have to create a {@link JoinLeaveManager} instance and to link it.
 * Then, when you add a {@link StateChangeAction} you can also
 * implements a {@link JoinLeaveAction}, which is going to be automatically linked with the state.
 * </p>
 * @author Steven, comments added by Askigh
 */
public class GameDivision implements Feature {

	// The MiniGame instance you want to complete with this feature, defined in the constructor
	private MiniGameCore source;
	
	/*
	 * All the actions that have to be executed when the state appear
	 * The State is registered as a number, see above for further information
	 */
	private List<StateChangeAction> steps;

	/*
	 * The current state of the game
	 * Set to 0 (the first state defined) by default
	 */
	private int currentState = 0;

	/**
	 * @param source : The MiniGame instance you want to complete with this feature
	 */
	public GameDivision(MiniGameCore source) {

		this.source = source;
		this.steps = new ArrayList<StateChangeAction>();
	}

	public void addStep(StateChangeAction action, int index) {
		steps.add(index, action);
	}

	public void addStep(StateChangeAction action) {
		steps.add(action);
	}

	public void changeStep() {
		
		if(steps.size() < (currentState - 1)) {
			currentState++;

			steps.get(currentState).executeWhenSet();

		}
	}

	@Override
	public Class<? extends Feature> getFeature() {
		
		return getClass();
	}

	@Override
	public void addAsFeature() {

		source.getFeatures().add(this);
	}

	public int getStepsCount() {
		return steps.size();
	}

	public MiniGameCore getSource() {
		return source;
	}

	public int getCurrentStepIndex() {
		return currentState;
	}

	public StateChangeAction getCurrentState() {
		return steps.get(currentState);
	}

	/**
	 * Interface that contains a single method named executeWhenSet()
	 * </p>
	 * This method is called whenever the state changes from an other to the one that contains this action
	 * @author Askigh & Steven
	 */
	public interface StateChangeAction {

		void executeWhenSet();
	}
}
