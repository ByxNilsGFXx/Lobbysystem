package net.hashcodedevelopement.freelobby.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.hashcodedevelopement.freelobby.manager.WarpManager;
import net.hashcodedevelopement.freelobby.util.ItemCreator;
import net.hashcodedevelopement.freelobby.util.Pair;
import net.hashcodedevelopement.freelobby.util.Utils;
import net.md_5.bungee.api.ChatColor;

public class CMD_Navigator implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			if (Utils.singleServer && ((Player) sender).getWorld().getName().equals(Utils.world)) {
				int size = WarpManager.getWarpsSize();
				HashMap<String, Pair<Location, Material>> hashMap = WarpManager.getWarps();
				Inventory inventory;
				Player player = (Player) sender;

				if (size == 0) {
					inventory = Bukkit.createInventory(null, 27, "§6§lNavigator");
					ArrayList<String> lore = new ArrayList<>();
					lore.add("§7Erstelle Warps ganz einfach mit");
					lore.add("§e/warp setWarp <Name> <Slot>");
					inventory.setItem(13, ItemCreator.CreateItemwithMaterial(Material.BARRIER, 0, 1,
							"§4Keine Warps erstellt!", lore));
				} else if (size >= 1) {
					inventory = Bukkit.createInventory(null, 27, "§6§lNavigator");
					for (String key : hashMap.keySet()) {
						Material material = Material.valueOf(WarpManager.cfg.getString(key + ".Icon"));
						inventory.setItem(WarpManager.cfg.getInt(key + ".Slot"),
								ItemCreator.CreateItemwithMaterial(material, WarpManager.cfg.getInt(key + ".SubID"), 1,
										ChatColor.translateAlternateColorCodes('&', key), null));
					}
				} else {
					inventory = Bukkit.createInventory(null, 27, "§6§lNavigator");
					ArrayList<String> lore = new ArrayList<>();
					lore.add("§7Erstelle Warps ganz einfach mit");
					lore.add("§e/warp setWarp <Name> <Slot>");
					inventory.setItem(13, ItemCreator.CreateItemwithMaterial(Material.BARRIER, 0, 1, "§4Fehler", lore));
				}

				player.openInventory(inventory);
			} else {
				if (!Utils.singleServer) {
					int size = WarpManager.getWarpsSize();
					HashMap<String, Pair<Location, Material>> hashMap = WarpManager.getWarps();
					Inventory inventory;
					Player player = (Player) sender;

					if (size == 0) {
						inventory = Bukkit.createInventory(null, 27, "§6§lNavigator");
						ArrayList<String> lore = new ArrayList<>();
						lore.add("§7Erstelle Warps ganz einfach mit");
						lore.add("§e/warp setWarp <Name> <Slot>");
						inventory.setItem(13, ItemCreator.CreateItemwithMaterial(Material.BARRIER, 0, 1,
								"§4Keine Warps erstellt!", lore));
					} else if (size >= 1) {
						inventory = Bukkit.createInventory(null, 27, "§6§lNavigator");
						for (String key : hashMap.keySet()) {
							Material material = Material.valueOf(WarpManager.cfg.getString(key + ".Icon"));
							inventory.setItem(WarpManager.cfg.getInt(key + ".Slot"),
									ItemCreator.CreateItemwithMaterial(material, WarpManager.cfg.getInt(key + ".SubID"), 1,
											ChatColor.translateAlternateColorCodes('&', key), null));
						}
					} else {
						inventory = Bukkit.createInventory(null, 27, "§6§lNavigator");
						ArrayList<String> lore = new ArrayList<>();
						lore.add("§7Erstelle Warps ganz einfach mit");
						lore.add("§e/warp setWarp <Name> <Slot>");
						inventory.setItem(13, ItemCreator.CreateItemwithMaterial(Material.BARRIER, 0, 1, "§4Fehler", lore));
					}

					player.openInventory(inventory);
				}
			}
		}
		return false;
	}

}
