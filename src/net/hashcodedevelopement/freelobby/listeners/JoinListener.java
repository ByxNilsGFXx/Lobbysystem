package net.hashcodedevelopement.freelobby.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.hashcodedevelopement.freelobby.Lobbysystem;
import net.hashcodedevelopement.freelobby.commands.CMD_Playerhider;
import net.hashcodedevelopement.freelobby.commands.CMD_Playerhider.HIDE;
import net.hashcodedevelopement.freelobby.util.ItemCreator;
import net.hashcodedevelopement.freelobby.util.TablistAPI;
import net.hashcodedevelopement.freelobby.util.Utils;
import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		CMD_Playerhider.hashMap.put(player.getUniqueId(), HIDE.SHOW_ALL);

		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		for (String key : Utils.lobbyitemCfg.getKeys(false)) {
			player.getInventory().setItem(Utils.lobbyitemCfg.getInt(key + ".Slot") - 1,
					ItemCreator.CreateItemwithMaterial(
							Material.getMaterial(Utils.lobbyitemCfg.getString(key + ".Material")), 0, 1,
							ChatColor.translateAlternateColorCodes('&', Utils.lobbyitemCfg.getString(key + ".Name")),
							null));
		}
		TablistAPI.sendTabTitle(player, Utils.tablistHeader, Utils.tablistFooter);

		File file = new File("plugins//Lobbysystem//Playerdata//" + player.getUniqueId() + ".yml");
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		
		if (!configuration.contains("Chat")) {
			configuration.set("Chat", true);
			try {
				configuration.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (Utils.joinMessage) {
			if (Utils.firstMessage) {
				if (!player.hasPlayedBefore()) {
					event.setJoinMessage(null);
					for (int i = 0; i < Utils.firstjoinMsg.size(); i++) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&',
								Utils.firstjoinMsg.get(i).replace("%prefix%", Utils.prefix)
										.replace("%PlayerCount%", Utils.playerCount + "")
										.replace("%PlayerName%", player.getName())));
					}
				} else {
					event.setJoinMessage(ChatColor.translateAlternateColorCodes('&',
							Utils.joinMsg.replace("%PlayerName%", player.getName()).replace("%prefix%", Utils.prefix)));
				}
			} else {
				event.setJoinMessage(ChatColor.translateAlternateColorCodes('&',
						Utils.joinMsg.replace("%PlayerName%", player.getName()).replace("%prefix%", Utils.prefix)));
			}
		} else {
			event.setJoinMessage(null);
		}

		if (!player.hasPlayedBefore()) {

			if (Utils.firstMessage) {
				player.sendMessage(Utils.prefix + "Hallo, §a" + player.getName()
						+ "! §7Es sieht so aus, als würdest du hier das erste Mal spielen. Deshalb werde ich für dich erstmal deine persönlichen Dateien erstellen!");
			}

			if (!configuration.contains("Chat")) {
				configuration.set("Chat", true);
				try {
					configuration.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (Utils.firstMessage) {
				player.sendMessage(Utils.prefix
						+ "So, der Vorgang ist dann soweit abgeschlossen! Ich wünsche dir weiterhin noch viel Spaß bei uns!");
			}

			Bukkit.getScheduler().scheduleSyncDelayedTask(Lobbysystem.getInstance(), new Runnable() {

				@Override
				public void run() {
					player.performCommand("spawn");
				}
			}, 5);
		} else
			player.performCommand("spawn");
	}
}
