package net.hashcodedevelopement.freelobby.listeners;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.avaje.ebeaninternal.server.transaction.BulkEventListenerMap;

import net.hashcodedevelopement.coinsapi.CoinsAPI;
import net.hashcodedevelopement.freelobby.Lobbysystem;
import net.hashcodedevelopement.freelobby.manager.LanguageManager.Language;
import net.hashcodedevelopement.freelobby.util.Utils;
import net.hashcodedevelopement.freelobby.util.Utils.DailyRewardMode;

public class DailyReward {
	
	public static String getRemainingTimeRAW(long millis) {
		long seconds = millis / 1000;
		long minutes = 0;
		while (seconds > 60) {
			seconds -= 60;
			minutes++;
		}
		long hours = 0;
		while (minutes > 60) {
			minutes -= 60;
			hours++;
		}
		return hours + ":" + minutes + ":" + seconds;
	}

	public static String getRemainingTime(String raw, Language language) {
		String data[] = raw.split(":");
		String hours = data[0];
		String minutes = data[1];
		String seconds = data[2];
		
		switch (language){
		case DE:
			return hours+"Stunde(n), "+minutes+" Minute(n) und "+seconds+" Sekunde(n)";
		case EN:
			return hours+"Hour(s), "+minutes+" Minute(s) and "+seconds+" Second(s)";
		default:
			return hours+"Hour(s), "+minutes+" Minute(s) and "+seconds+" Second(s)";
		}
	}
	
	public static void setReward(Player player){
		File file = new File("plugins//LobbySystem//Playerdata//Rewards.yml");
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		
		configuration.set(player.getUniqueId().toString(), Long.valueOf((System.currentTimeMillis()+86400000)));
		try {
			configuration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch(Lobbysystem.mode){
		case COINS:
			if(Lobbysystem.coinsAddon != null){
				int coins = Utils.cfg.getInt("DailyReward.Coins");
				CoinsAPI coinsAPI = new CoinsAPI();
				coinsAPI.addCoins(player.getUniqueId(), coins);
				
				switch(Lobbysystem.language){
				case DE:
					player.sendMessage(Utils.prefix+"§7Du hast §e"+coins+" §6Coins erhalten§7!");
					break;
				case EN:
					player.sendMessage(Utils.prefix+"§7You got §e"+coins+" §6Coins§7!");
					break;
				default:
					break;
				}
			}
			break;
		case COMMAND:
			List<String> list = Utils.cfg.getStringList("DailyReward.CommandList");
			for(String cmd : list){
				cmd = cmd.replace("%PlayerName%", player.getName()).replace("%Prefix%", Utils.prefix);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
			}
			break;
		case OFF:
			break;
		default:
			break;
		}
	}
	
	public static Long getTime(UUID uuid){
		long l = 0;
		
		File file = new File("plugins//LobbySystem//Playerdata//Rewards.yml");
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		if(configuration.contains(uuid.toString())){
			l = (configuration.getLong(uuid.toString()) - System.currentTimeMillis());
		}
		
		return l;
	}
	
	public static boolean isDailyRewardEnabled(){
		if(Lobbysystem.mode != DailyRewardMode.OFF){
			return true;
		}
		return false;
	}
	
	public static boolean canGainReward(UUID uuid){
		File file = new File("plugins//LobbySystem//Playerdata//Rewards.yml");
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		if(!configuration.contains(uuid.toString())){
			return true;
		}
		
		Long millis = configuration.getLong(uuid.toString());
		if(System.currentTimeMillis() >= millis){
			return true;
		}
		return false;
	}
	
}
