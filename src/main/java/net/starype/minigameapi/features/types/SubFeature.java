package net.starype.minigameapi.features.types;

import net.starype.minigameapi.features.actions.GameAction;

public interface SubFeature {

	/**
	 * Generic method that is used to link the instance to an other Feature 
	 *  <p> The parameter GameAction is in case you create a multi subfeature architecture. </p>
	 * E.G : If you want to create a CustomItem linked to the ItemInteractions linked to GameDivision,
	 * you have two options :
	 * <p> You can set the parameter to null. If you do, the CustomItem will be added to the default list
	 * of CustomItem </p>
	 * If you don't want the CustomItem to be added to the default list, you can specify which GameAction you want
	 * to use. In this example, the GameAction has to be an ItemActionnable. The list of CustomItem from this
	 * Actionnable will be automatically completed with this instance that you have just linked
	 */
	<T extends SubFeature> T link(GameAction optionalAction);
	
	/**
	 * 
	 * @return the Feature the instance is linked with
	 */
	Class <? extends StandardFeature> getLinkedTo();
}
