package net.hashcodedevelopement.freelobby.listeners;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.hashcodedevelopement.freelobby.util.Utils;

public class CommandPreprocessListener implements Listener {

	@EventHandler
	public void onCommandPreprocess(PlayerCommandPreprocessEvent e){
		if(Utils.commandWhitelist){
			List<String> list = Utils.cfg.getStringList("CommandWhitelist.List");
			if(!e.getPlayer().hasPermission("lobby.extra.bypassCommandWhitelist")){
				String[] command = e.getMessage().replace("/", "").split(" ");
				if(!list.contains(command[0])){
					e.setCancelled(true);
					e.getPlayer().sendMessage(Utils.commandNotFound);
				}
			}
		}
	}
	
}
