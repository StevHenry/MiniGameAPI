package net.starype.minigameapi.multilinkable;

import net.starype.minigameapi.features.actions.MultiLinkable;

/**
 * <p>Interface that contains a single method named executeWhenSet()</p>
 * <p>This method is called whenever the state changes from an other to the one that contains this action</p>
 * 
 * @version alpha
 * 
 * @author Askigh
 * @author Steven
 */
public interface StateChangeAction extends MultiLinkable {

	void executeWhenSet();
}
