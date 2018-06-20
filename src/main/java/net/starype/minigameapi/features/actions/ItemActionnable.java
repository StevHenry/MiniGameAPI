package net.starype.minigameapi.features.actions;

import java.util.List;

import net.starype.minigameapi.features.subfeature.CustomItem;

public interface ItemActionnable extends GameAction {
	
	List<CustomItem> getItems();
}
