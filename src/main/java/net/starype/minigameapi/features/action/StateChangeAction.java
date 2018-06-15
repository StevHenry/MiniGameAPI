package net.starype.minigameapi.features.action;


/**
 * Interface that contains a single method named executeWhenSet()
 * </p>
 * This method is called whenever the state changes from an other to the one that contains this action
 * @author Askigh & Steven
 * 
 */
public interface StateChangeAction {

	void executeWhenSet();
}
