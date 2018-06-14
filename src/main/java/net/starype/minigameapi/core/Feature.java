package net.starype.minigameapi.core;

public interface Feature {

	Class<? extends Feature> getFeature();
	
	void addAsFeature();
}
