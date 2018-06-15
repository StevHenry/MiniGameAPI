package net.starype.minigameapi.core;

/**
 * 
 * @author Askigh & Steven
 * Global interface which regroups all the features you may need in your game
 * The List of the features is linked below :
 * <add link>
 *
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
