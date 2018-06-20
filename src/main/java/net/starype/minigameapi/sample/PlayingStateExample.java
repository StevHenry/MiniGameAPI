package net.starype.minigameapi.sample;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.ItemActionnable;
import net.starype.minigameapi.features.actions.StateChangeAction;
import net.starype.minigameapi.features.standard.GameDivision;
import net.starype.minigameapi.features.subfeature.CustomItem;

public class PlayingStateExample implements StateChangeAction, ItemActionnable {

	private List<CustomItem> items;
	
	public PlayingStateExample(MiniGameCore core) {
		
		this.items = new ArrayList<CustomItem>();
		createEndItem(core);
	}
	
	private void createEndItem(MiniGameCore core) {
		
		ItemStack endItem = new ItemStack(Material.NETHER_STAR);
		
		new CustomItem(core, endItem, (Action) null) {
			
			@Override
			public void execute(PlayerInteractEvent event) {
				
				GameDivision division = core.getFeature(GameDivision.class).get();
				StateChangeAction endAction = division.getStateByIndex(division.getCurrentStepIndex() + 1);
				((EndStateExample) endAction).setWinner(event.getPlayer());
				
				division.changeStep();
			}
		}.link(this);
	}

	@Override
	public void executeWhenSet() {

		System.out.println("The Game has started !");
	}

	@Override
	public List<CustomItem> getItems() {
		return items;
	}
}
