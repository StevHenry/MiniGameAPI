package net.starype.minigameapi.features.types;

public interface SubFeature {

	/**
	 * Generic method that is used to link the instance to an other Feature
	 */
	<T extends SubFeature> T link();
	
	/**
	 * 
	 * @return the Feature the instance is linked with
	 */
	Class <? extends StandardFeature> getLinkedTo();
}
