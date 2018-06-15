package net.starype.minigameapi.features;

import java.util.ArrayList;
import java.util.List;

import net.starype.minigameapi.core.Feature;
import net.starype.minigameapi.core.MiniGameCore;

public class GameDivision implements Feature {

	private MiniGameCore source;
	private List<StateChangeAction> steps;

	private int currentStep = 0;

	public GameDivision(MiniGameCore source) { //Bien vu ! C'était pas loin... Oops!

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
