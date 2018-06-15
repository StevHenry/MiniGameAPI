package net.starype.minigameapi.core;

import net.starype.minigameapi.features.standard.GameDivision;

/**
 * 
 * @author Askigh
 * @author Steven
 * <p>Global interface which regroups all the features you may need in your game</p>
 * <p>The List of the features is linked below :<p>
 * <ul>
 * 	<li>{@link GameDivision}</li>
 * </ul>
 */
public interface Feature {

	/**
	 * Abstract method used to get which Feature you target
	 * May be replaced in the future by getClass() method from Object class
	 * @return the feature from the class put in parameter
	 */
	Class<? extends Feature> getFeature();
	
	/**
	 * Adds the instance in the MiniGameCore defined in the constructor
	 * Can also register the event, if a Plugin parameter was needed
	 */
	void addAsFeature();
}
