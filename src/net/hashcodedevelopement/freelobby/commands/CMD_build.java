package net.hashcodedevelopement.freelobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.hashcodedevelopement.freelobby.Lobbysystem;
import net.hashcodedevelopement.freelobby.util.Utils;

public class CMD_build implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (player.hasPermission("lobby.cmd.build")) {
				if (Utils.isBuildMode(player.getName())) {
					Utils.buildmode.remove(player.getName());
					
					switch (Lobbysystem.language){
					case DE:
						player.sendMessage(Utils.prefix + "Der Baumodus wurde §cdeaktiviert!");
						break;
					case EN:
						player.sendMessage(Utils.prefix + "The buildmode was §cdeactivated!");
						break;
					default:
						break;
					}
				} else {
					Utils.buildmode.add(player.getName());
					
					switch (Lobbysystem.language){
					case DE:
						player.sendMessage(Utils.prefix + "Der Baumodus wurde §aaktiviert!");
						break;
					case EN:
						player.sendMessage(Utils.prefix + "The buildmode was §aactivated!");
						break;
					default:
						break;
					}
				}
			} else {
				Utils.noPermissions(player);
			}
		}
		return false;
	}
}
