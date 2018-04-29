package net.hashcodedevelopement.freelobby.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.hashcodedevelopement.freelobby.Lobbysystem;
import net.hashcodedevelopement.freelobby.util.Utils;

public class CMD_Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("set")){
					if(sender.hasPermission("lobby.cmd.setSpawn")){
						Utils.cfg.set("Spawn", player.getLocation());
						Utils.saveCfg();
						
						switch (Lobbysystem.language){
						case DE:
							player.sendMessage(Utils.prefix+"Du hast den Spawn gesetzt.");
							break;
						case EN:
							player.sendMessage(Utils.prefix+"You've set the spawn.");
							break;
						default:
							break;
						}
					} else player.performCommand("spawn");
				} else player.performCommand("spawn");
			} else if(args.length == 0){
				if(Utils.cfg.contains("Spawn")){
					Location spawn = (Location) Utils.cfg.get("Spawn");
					player.teleport(spawn);
					switch (Lobbysystem.language){
					case DE:
						player.sendMessage(Utils.prefix+"Du wurdest zum Spawn teleportiert!");
						break;
					case EN:
						player.sendMessage(Utils.prefix+"You got teleported to the spawn!");
						break;
					default:
						break;
					}
				} else {
					switch (Lobbysystem.language){
					case DE:
						player.sendMessage(Utils.prefix+"§cDer Spawn wurde noch nicht gesetzt.");
						if(player.hasPermission("lobby.cmd.setSpawn")){
							player.sendMessage(Utils.prefix+"Setze den Spawn mit §e/spawn set");
						}
						break;
					case EN:
						player.sendMessage(Utils.prefix+"§cThe spawn has not been set yet.");
						if(player.hasPermission("lobby.cmd.setSpawn")){
							player.sendMessage(Utils.prefix+"Set the spawn: §e/spawn set");
						}
						break;
					default:
						break;
					}
				}
			}
		}
		return false;
	}
	
}
