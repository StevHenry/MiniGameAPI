package net.starype.minigameapi.core;

import java.util.HashSet;
import java.util.Set;

public class MiniGameCore {

	/**
	 * The Set of all the features added into the game
	 * Has to be a Set, simply because a same Feature can not be added twice
	 */
	private Set<Feature> features;
	
	/**
	 * Create a simple MiniGameCore instance, and init the features Set
	 */
	public MiniGameCore() {
		
		this.features = new HashSet<Feature>();
	}
	
	/**
	 * 
	 * @return the list of all the present features
	 * If you can't find a specific feature in the Set, 
	 * make sure you've called addAsFeature() function earlier
	 */
	public Set<Feature> getFeatures() {	
		return features;
	}
	
	/**
	 * 
	 * @param source : The feature you want to get using {@code NameOfFeature.class}
	 * @return The feature needed
	 */
	@SuppressWarnings("unchecked")
	public <T extends Feature> T getFeature(Class<?> source) {
		
		for(Feature f : features)
			if(f.getFeature().equals(source))
				return (T) f;
		
		return null;
	}
}
