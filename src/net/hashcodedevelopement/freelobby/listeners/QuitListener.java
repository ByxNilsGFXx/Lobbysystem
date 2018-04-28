package net.hashcodedevelopement.freelobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.hashcodedevelopement.freelobby.commands.CMD_Playerhider;
import net.hashcodedevelopement.freelobby.util.Utils;
import net.md_5.bungee.api.ChatColor;

public class QuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		CMD_Playerhider.hashMap.remove(player.getUniqueId());
		
		if (Utils.quitMessage) {
			event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', Utils.quitMsg.replace("%PlayerName%", player.getName()).replace("%prefix%", Utils.prefix)));
		} else {
			event.setQuitMessage(null);
		}
	}
}
