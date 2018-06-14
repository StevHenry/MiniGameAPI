package net.starype.minigameapi.features;

import java.util.HashMap;
import java.util.Map;

import net.starype.minigameapi.core.Feature;
import net.starype.minigameapi.core.MiniGameCore;

public class GameDivision implements Feature {

	private int partAmount;
	private MiniGameCore source;
	private Map<String, StateChangeAction> steps;
	private String currentStep;
	private String defaultStep;
	
	public GameDivision(int partAmount, MiniGameCore source) {
		
		this.partAmount = partAmount;
		this.source = source;
		this.steps = new HashMap<String, StateChangeAction>();
	}
	
	public void addStep(String step, StateChangeAction action) {
		
		steps.put(step, action);
	}
	
	public void addStepAsDefault(String step) {
		
		steps.put(step, null);
		currentStep = step;
		defaultStep = step;
	}

	public void execute() {
		
		for(String str : steps.keySet())
			if(str.equals(currentStep))
				steps.get(str).execute();
	}
	
	public void executeThenSkip() {
		
		execute();
		boolean found = false;
		boolean isLast = true;
		
		for(String s : steps.keySet()) {
			
			if(found) {
				// The last string was current, now we set the next one
				currentStep = s;
				isLast = false;
			}
			
			if(s.equals(currentStep))
				found = true; // means that the next string will be current
		}
		
		if(isLast)
			currentStep = defaultStep;
	}

	@Override
	public Class<? extends Feature> getFeature() {
		
		return GameDivision.class;
	}

	@Override
	public void addAsFeature() {
		
		source.getFeatures().add(this);
	}
	
	public void analyze() {
		
		if(steps.size() != partAmount) throw new IllegalStateException("Incorrect statement : Size of List has to be equal to stepsAmount");
	}
	
	public int getStepsAmount() {
		
		return partAmount;
	}
	
	public MiniGameCore getSource() {
		return source;
	}
	
	public interface StateChangeAction {
		
		void execute();
	}
	
	public String getCurrentStep() {
		
		return currentStep;
	}
	
	public String getDefaultStep() {
		return defaultStep;
	}
}
