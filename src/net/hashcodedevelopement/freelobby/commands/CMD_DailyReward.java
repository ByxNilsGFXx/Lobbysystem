package net.hashcodedevelopement.freelobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.hashcodedevelopement.freelobby.Lobbysystem;
import net.hashcodedevelopement.freelobby.listeners.DailyReward;
import net.hashcodedevelopement.freelobby.util.Utils;

public class CMD_DailyReward implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (DailyReward.isDailyRewardEnabled()) {
				if (DailyReward.canGainReward(player.getUniqueId())) {
					DailyReward.setReward(player);
					switch(Lobbysystem.language){
					case DE:
						player.sendMessage(Utils.prefix+"Du hast deine Tagliche Belohnung erhalten.");
						break;
					case EN:
						player.sendMessage(Utils.prefix+"You got your daily reward.");
						break;
					default:
						break;
					}
				} else {
					switch(Lobbysystem.language){
					case DE:
						player.sendMessage(Utils.prefix+"§cDu hast deine Tägliche Belohnung bereits abgeholt! Du kannst deine Belohnung erneut in §b"+DailyReward.getRemainingTime(DailyReward.getRemainingTimeRAW(DailyReward.getTime(player.getUniqueId())), Lobbysystem.language)+" §7abholen.");
						break;
					case EN:
						player.sendMessage(Utils.prefix+"§cYou already got your daily reward! You can get your reward again in §b"+DailyReward.getRemainingTime(DailyReward.getRemainingTimeRAW(DailyReward.getTime(player.getUniqueId())), Lobbysystem.language)+"§7.");
						break;
					default:
						break;
					}
				}
			} else {
				switch(Lobbysystem.language){
				case DE:
					player.sendMessage(Utils.prefix+"§cDie Tägliche Belohnung ist nicht aktiviert!");
					break;
				case EN:
					player.sendMessage(Utils.prefix+"§cThe daily reward is not enabled!");
					break;
				default:
					break;
				}
			}
		}
		return false;
	}

}
