package net.hashcodedevelopement.freelobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import net.hashcodedevelopement.freelobby.util.Utils;

public class CancelledListener implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent entityDamageEvent) {
		if (Utils.singleServer && entityDamageEvent.getEntity().getWorld().getName().equals(Utils.world)) {
			entityDamageEvent.setCancelled(true);
		} else {
			if (!Utils.singleServer) {
				entityDamageEvent.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if (Utils.singleServer && event.getEntity().getWorld().getName().equals(Utils.world)) {
			event.setCancelled(true);
		} else {
			if (!Utils.singleServer) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		if (Utils.singleServer && event.getWorld().getName().equals(Utils.world)) {
			event.setCancelled(true);
		} else {
			if (!Utils.singleServer) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onInteractInInventory(InventoryClickEvent e) {
		if (Utils.singleServer && e.getWhoClicked().getWorld().getName().equals(Utils.world)) {
			if (!Utils.isBuildMode(e.getWhoClicked().getName())) {
				e.setCancelled(true);
			}
		} else {
			if (!Utils.singleServer) {
				if (!Utils.isBuildMode(e.getWhoClicked().getName())) {
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if (Utils.singleServer && e.getPlayer().getWorld().getName().equals(Utils.world)) {
			if (!Utils.isBuildMode(e.getPlayer().getName())) {
				e.setCancelled(true);
			}
		} else {
			if (!Utils.singleServer) {
				if (!Utils.isBuildMode(e.getPlayer().getName())) {
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (Utils.singleServer && e.getPlayer().getWorld().getName().equals(Utils.world)) {
			if (!Utils.isBuildMode(e.getPlayer().getName())) {
				e.setCancelled(true);
			}
		} else {
			if (!Utils.singleServer) {
				if (!Utils.isBuildMode(e.getPlayer().getName())) {
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onBuild(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (Utils.singleServer && player.getWorld().getName().equals(Utils.world)) {
			if (!Utils.isBuildMode(player.getName())) {
				event.setCancelled(true);
			}
		} else {
			if (!Utils.singleServer) {
				if (!Utils.isBuildMode(player.getName())) {
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (Utils.singleServer && player.getWorld().getName().equals(Utils.world)) {
			if (!Utils.isBuildMode(player.getName())) {
				event.setCancelled(true);
			}
		} else {
			if (!Utils.singleServer) {
				if (!Utils.isBuildMode(player.getName())) {
					event.setCancelled(true);
				}
			}
		}
	}
}
