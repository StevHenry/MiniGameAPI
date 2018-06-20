package net.starype.minigameapi.sample;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import net.starype.minigameapi.core.MiniGameCore;
import net.starype.minigameapi.features.actions.ItemActionnable;
import net.starype.minigameapi.features.actions.JoinLeaveAction;
import net.starype.minigameapi.features.actions.StateChangeAction;
import net.starype.minigameapi.features.standard.GameDivision;
import net.starype.minigameapi.features.subfeature.CustomItem;

public class WaitingStateExample implements StateChangeAction, JoinLeaveAction, ItemActionnable {

	private List<CustomItem> items;
	private MiniGameCore core;
	
	public WaitingStateExample(MiniGameCore core) {

		setCustomItems(core);
		this.core = core;
	}

	@Override
	public void executeWhenSet() {

		System.out.println("State reached !");
	}

	@Override
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		player.sendMessage("You joined the server !");
		core.addPlayer(player);
		
		if(Bukkit.getOnlinePlayers().size() >= 3) {
			
			Bukkit.broadcastMessage("Game is starting...");
			core.getFeature(GameDivision.class).get().changeStep();
		}
	}

	@Override
	public void onLeave(PlayerQuitEvent event) {
		
		Bukkit.broadcastMessage(event.getPlayer().getName()+" left...");
		core.removePlayer(event.getPlayer());
	}

	private void setCustomItems(MiniGameCore core) {
		
		ItemStack menu = new ItemStack(Material.COMPASS);
		
		new CustomItem(core, menu, Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK) {

			@Override
			public void execute(PlayerInteractEvent event) {
				event.getPlayer().sendMessage("Compass clicked !");
			}
			
		}.link(this);
	}

	@Override
	public List<CustomItem> getItems() {
		return items;
	}
}
