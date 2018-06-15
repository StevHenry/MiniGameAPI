package net.starype.minigameapi.features.polyvalent;

import net.starype.minigameapi.core.Feature;

public interface Linkable {

	/**
	 * Generic method that is used to link the instance to an other Feature
	 */
	void link();
	
	/**
	 * 
	 * @return the Feature the instance is linked with
	 */
	Class <? extends Feature> getLinkedTo();
}
