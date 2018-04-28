package net.hashcodedevelopement.freelobby.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.hashcodedevelopement.freelobby.commands.CMD_Playerhider;
import net.hashcodedevelopement.freelobby.manager.WarpManager;
import net.hashcodedevelopement.freelobby.util.Utils;
import net.md_5.bungee.api.ChatColor;

public class InteractListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (Utils.items.contains(e.getPlayer().getItemInHand().getType())) {
			for (String key : Utils.lobbyitemCfg.getKeys(false)) {
				if (Material.getMaterial(Utils.lobbyitemCfg.getString(key + ".Material")) == e.getPlayer()
						.getItemInHand().getType()) {
					if (e.getAction() == Action.valueOf(Utils.lobbyitemCfg.getString(key + ".Action"))) {
						e.setCancelled(true);
						e.getPlayer().chat(Utils.lobbyitemCfg.getString(key + ".Command"));
					} else if (e.getAction() == Action.valueOf(Utils.lobbyitemCfg.getString(key + ".SecondAction"))) {
						e.setCancelled(true);
						e.getPlayer().chat(Utils.lobbyitemCfg.getString(key + ".Command"));
					}
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player player = (Player) e.getWhoClicked();
			ItemStack itemStack = e.getCurrentItem();
			if (e.getInventory().getTitle().equals("§6§lNavigator")) {
				for (String key : WarpManager.cfg.getKeys(false)) {
					if (itemStack.getType() == Material.valueOf(WarpManager.cfg.getString(key + ".Icon"))) {
						if (itemStack.getItemMeta().getDisplayName().replace("§", "&").equals(key)) {
							Location location = (Location) WarpManager.cfg.get(key + ".Location");
							player.teleport(location);
							player.playSound(location, Sound.ENDERMAN_TELEPORT, 3, 1);
							Utils.sendActionbar("§7Warpe zu §e" + ChatColor.translateAlternateColorCodes('&', key),
									player.getUniqueId());
						}
					}
				}
			} else if (e.getInventory().getTitle().equals("§a§lSpieler verstecken")) {
				if (itemStack.getItemMeta().getDisplayName().contains("§7")) {
					CMD_Playerhider.hashMap.remove(player.getUniqueId());
					CMD_Playerhider.hashMap.put(player.getUniqueId(), CMD_Playerhider.HIDE.SHOW_NONE);
					
					for(Player all : Bukkit.getOnlinePlayers()){
						player.hidePlayer(all);
					}
					
					player.performCommand("playerhider");
					player.sendMessage(Utils.prefix+"Dir werden nun keine Spieler angezeigt.");
				} else if (itemStack.getItemMeta().getDisplayName().contains("§5")) {
					CMD_Playerhider.hashMap.remove(player.getUniqueId());
					CMD_Playerhider.hashMap.put(player.getUniqueId(), CMD_Playerhider.HIDE.SHOW_VIP);
					
					for(Player all : Bukkit.getOnlinePlayers()){
						player.hidePlayer(all);
					}
					for(Player all : Bukkit.getOnlinePlayers()){
						if(all.hasPermission("lobby.extra.vip")){
							player.showPlayer(all);
						}
					}
					
					player.performCommand("playerhider");
					player.sendMessage(Utils.prefix+"Dir werden nun nur VIP's angezeigt.");
				} else if (itemStack.getItemMeta().getDisplayName().contains("§a")) {
					CMD_Playerhider.hashMap.remove(player.getUniqueId());
					CMD_Playerhider.hashMap.put(player.getUniqueId(), CMD_Playerhider.HIDE.SHOW_ALL);
					
					for(Player all : Bukkit.getOnlinePlayers()){
						player.showPlayer(all);
					}
					
					player.performCommand("playerhider");
					player.sendMessage(Utils.prefix+"Dir werden nun alle Spieler angezeigt.");
				}
			} else if (e.getInventory().getTitle().equals("§b§lProfil")) {
				if(itemStack.getType().equals(Material.BOOK)){
					File file = new File("plugins//Lobbysystem//Playerdata//"+player.getUniqueId()+".yml");
					FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
					
					if(Utils.getChatState(player.getUniqueId())){
						configuration.set("Chat", false);
						player.sendMessage(Utils.prefix+"Der Chat ist nun für dich ausgeblendet!");
					} else {
						configuration.set("Chat", true);
						player.sendMessage(Utils.prefix+"Der Chat wird dir nun wieder angezeigt!");
					}
					
					try {
						configuration.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					player.performCommand("profil");
				}
			}
		}
	}

}
