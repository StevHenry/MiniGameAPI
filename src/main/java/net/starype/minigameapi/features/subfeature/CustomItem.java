package net.starype.minigameapi.features.subfeature;

import java.util.Optional;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.GameAction;
import net.starype.minigameapi.features.actions.ItemActionnable;
import net.starype.minigameapi.features.polyvalent.ItemInteraction;
import net.starype.minigameapi.features.types.StandardFeature;
import net.starype.minigameapi.features.types.SubFeature;

public abstract class CustomItem implements SubFeature {
	
	private ItemStack item;
	private MiniGameCore core;
	
	public CustomItem(MiniGameCore core, ItemStack item) {
		
		this.item = item;
		this.core = core;
	}
	
	public abstract void execute(PlayerInteractEvent event);

	public ItemStack getItem() {
		return item;
	} 
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends SubFeature> T link(GameAction optionalAction) {
		
		Optional<ItemInteraction> interaction = core.getFeature(ItemInteraction.class);
		
		if(optionalAction == null) {
			
			if(interaction.isPresent())
				interaction.get().addDefaultItem(this);
			
			return (T) this;
		}
		
		if(!(optionalAction instanceof ItemActionnable)) {
			
			System.err.println("Error : The GameAction is not an ItemActionnable");
			return (T) this;
		} 
		
		else 
			((ItemActionnable) optionalAction).getItems().add(this);
		
		return (T) this;
	}
	
	@Override
	public Class<? extends StandardFeature> getLinkedTo() {
		return ItemInteraction.class;
	}
}
