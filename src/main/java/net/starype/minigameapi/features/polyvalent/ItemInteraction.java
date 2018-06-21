package net.starype.minigameapi.features.polyvalent;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.MultiLinkable;
import net.starype.minigameapi.features.actions.ItemActionnable;
import net.starype.minigameapi.features.standard.GameDivision;
import net.starype.minigameapi.features.subfeature.CustomItem;
import net.starype.minigameapi.features.types.PolyvalentFeature;
import net.starype.minigameapi.features.types.StandardFeature;
import net.starype.minigameapi.features.types.SubFeature;

public class ItemInteraction implements PolyvalentFeature, Listener {

	private MiniGameCore core;
	private Plugin main;

	//Whether the instance is linked or not. See link() method for further information
	private boolean linked;

	/*
	 * Whether the instance use the default actions even if it's linked
	 * See withDefaultActions(boolean b) method for further information
	 */
	private boolean defaultActionIfLinked = false;
	private GameDivision division;
	private List<CustomItem> defaultItems;

	public ItemInteraction(MiniGameCore source, Plugin main) {

		this.core = source;
		this.main = main;
		this.defaultItems = new ArrayList<>();
	}
	
	public void addDefaultItem(CustomItem item) {
		
		defaultItems.add(item);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
				
		if(linked && division.getCurrentState() instanceof ItemActionnable) {
			
			for(CustomItem item : ((ItemActionnable) division.getCurrentState()).getItems())
				if(canExecute(event, item))
					item.execute(event);
		}
		
		else if(!linked || (linked && defaultActionIfLinked))
			for(CustomItem item : defaultItems)
				if(canExecute(event, item))
					item.execute(event);
	}
	
	private boolean canExecute(PlayerInteractEvent event, CustomItem item) {
				
		if(event.getItem().equals(item.getItem()) || item.getClickOption() == null)
			return true;
		
		for(Action action : item.getClickOption())
			if(action == event.getAction())
				return true;
		
		return false;
		
	}

	@Override
	public Class<? extends StandardFeature> getFeature() {
		return getClass();
	}

	@Override
	public void addAsFeature() {
		
		if(!linked)
			core.getFeatures().add(this);
		else System.err.println("Cannon add a linked feature");
		
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends SubFeature> T link(MultiLinkable optionalAction) {

		linked = true;
		
		if(core.getFeature(GameDivision.class).isPresent())
			division = core.getFeature(GameDivision.class).get();

		return (T) this;
	}

	@Override
	public Class<? extends StandardFeature> getLinkedTo() {
		return GameDivision.class;
	}
}
