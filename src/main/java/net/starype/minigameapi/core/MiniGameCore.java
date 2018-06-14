package net.starype.minigameapi.core;

import java.util.HashSet;
import java.util.Set;

public class MiniGameCore {

	private Set<Feature> features;
	
	public MiniGameCore() {
		
		this.features = new HashSet<Feature>();
	}
	
	public Set<Feature> getFeatures() {
		
		return features;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Feature> T getFeature(Class<?> source) {
		
		for(Feature f : features)
			if(f.getClass().equals(source))
				return (T) f;
		
		return null;
	}
}
