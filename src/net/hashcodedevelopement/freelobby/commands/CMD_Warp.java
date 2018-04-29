package net.hashcodedevelopement.freelobby.commands;

import java.util.regex.Pattern;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.hashcodedevelopement.freelobby.Lobbysystem;
import net.hashcodedevelopement.freelobby.manager.WarpManager;
import net.hashcodedevelopement.freelobby.util.Utils;
import net.md_5.bungee.api.ChatColor;

public class CMD_Warp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			if (sender.hasPermission("lobby.cmd.warp")) {
				Player player = (Player) sender;
				switch (Lobbysystem.language){
				case DE:

					if (args.length == 0) {

					} else if (args.length == 1) {

					} else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("removeWarp")) {
							if (WarpManager.existWarp(args[1])) {
								if (WarpManager.deleteWarp(args[1])) {
									player.sendMessage(Utils.prefix + "Der Warp §e" + args[1] + " §7wurde gelöscht!");
								} else {
									player.sendMessage(Utils.prefix + "§cEin Fehler ist aufgetreten. Mögliche Ursachen: §8-§7 Dieser Warp existiert nicht");
								}
							}
						} else if (args[0].equalsIgnoreCase("listWarps")) {
							for (String key : WarpManager.getWarps().keySet()) {
								player.sendMessage("§8- §b" + ChatColor.translateAlternateColorCodes('&', key));
							}
						}
					} else if (args.length == 3) {
						if (args[0].equalsIgnoreCase("setWarp")) {
							if (Pattern.matches("[a-zA-Z]+", args[2]) == false && Integer.parseInt(args[2]) < 28 && Integer.parseInt(args[2]) > 0) {
								if (!WarpManager.existWarp(args[1].toUpperCase())) {
									if (player.getItemInHand() == null) {
										player.sendMessage(Utils.prefix + "§cEin Fehler ist aufgetreten. Mögliche Ursachen: §8-§7 Du hältst kein Item in der Hand");
										return false;
									}
									if (WarpManager.addWarp(args[1], player.getLocation(), player.getItemInHand(), (Integer.parseInt(args[2])-1))) {
										player.sendMessage(Utils.prefix + "Der Warp §e" + args[1] + " §7wurde erstellt!");
									} else {
										player.sendMessage(Utils.prefix + "§cEin Fehler ist aufgetreten. Mögliche Ursachen: §8-§7 Dieser Warp existiert bereits §8-§7 Du hältst kein Item in der Hand §8-§7 Der angegebene Slot ist bereits belegt");
									}
								}
							} else player.sendMessage(Utils.prefix + "§cEin Fehler ist aufgetreten. Mögliche Ursachen: §8-§7 Dein letztes Argument ist keine Zahl §8-§7 Die Zahl ist kleiner als 1 oder größer als 27");
						}
					}
					break;
				case EN:

					if (args.length == 0) {

					} else if (args.length == 1) {

					} else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("removeWarp")) {
							if (WarpManager.existWarp(args[1])) {
								if (WarpManager.deleteWarp(args[1])) {
									player.sendMessage(Utils.prefix + "The warp §e" + args[1] + " §7was deleted!");
								} else {
									player.sendMessage(Utils.prefix + "§cAn error occured. Possible causes: §8-§7 This warp doesnt exist");
								}
							}
						} else if (args[0].equalsIgnoreCase("listWarps")) {
							for (String key : WarpManager.getWarps().keySet()) {
								player.sendMessage("§8- §b" + ChatColor.translateAlternateColorCodes('&', key));
							}
						}
					} else if (args.length == 3) {
						if (args[0].equalsIgnoreCase("setWarp")) {
							if (Pattern.matches("[a-zA-Z]+", args[2]) == false && Integer.parseInt(args[2]) < 28 && Integer.parseInt(args[2]) > 0) {
								if (!WarpManager.existWarp(args[1].toUpperCase())) {
									if (player.getItemInHand() == null) {
										player.sendMessage(Utils.prefix + "§cAn error occured. Possible causes: §8-§7 You do not hold an item in your hand.");
										return false;
									}
									if (WarpManager.addWarp(args[1], player.getLocation(), player.getItemInHand(), (Integer.parseInt(args[2])-1))) {
										player.sendMessage(Utils.prefix + "Der Warp §e" + args[1] + " §7wurde erstellt!");
									} else {
										player.sendMessage(Utils.prefix + "§cAn error occured. Possible causes: §8-§7 This warp already exists §8-§7 You do not hold an item in your hand §8-§7 The given slot is already in use");
									}
								}
							} else player.sendMessage(Utils.prefix + "§cAn error occured. Possible causes: §8-§7 Your last argument is not a number §8-§7 The given number is smaller than 1 or bigger than 27");
						}
					}
					break;
				default:
					break;
				}
			}
		}
		return false;
	}

}
