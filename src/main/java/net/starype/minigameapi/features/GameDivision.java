package net.starype.minigameapi.features;

import java.util.ArrayList;
import java.util.List;

import net.starype.minigameapi.core.Feature;
import net.starype.minigameapi.core.MiniGameCore;
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

	private MiniGameCore source;
	private List<StateChangeAction> steps;

	private int currentStep = 0;

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
		
		if(steps.size() < (currentStep - 1)) {
			currentStep++;

			steps.get(currentStep).executeWhenSet();

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
		return currentStep;
	}

	public StateChangeAction getCurrentState() {
		return steps.get(currentStep);
	}

	public interface StateChangeAction {

		void executeWhenSet();
	}
}
