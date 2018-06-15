package net.starype.minigameapi.core;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import net.starype.minigameapi.features.types.StandardFeature;

public class MiniGameCore {

	/**
	 * This Set regroups all the used {@link StandardFeature}s
	 */
	private Set<StandardFeature> features;
	
	/**
	 * Create a simple MiniGameCore instance, and init the features Set
	 */
	public MiniGameCore() {
		
		this.features = new HashSet<StandardFeature>();
	}
	
	/**
	 * @return the list of all the present features.
	 * <p>If you can't find a specific feature in the Set, 
	 * make sure you've called addAsFeature() function earlier</p>
	 */
	public Set<StandardFeature> getFeatures() {	
		return features;
	}
	
	/**
	 * 
	 * @param source
	 * 	The feature you want to get using {@code NameOfFeature.class}
	 * @return The needed feature
	 */
	@SuppressWarnings("unchecked")
	public <T extends StandardFeature> Optional<T> getFeature(Class<T> source) {
		
		for(StandardFeature f : features)
			if(f.getFeature().equals(source))
				return Optional.of((T) f);
		
		return Optional.empty();
	}
}
