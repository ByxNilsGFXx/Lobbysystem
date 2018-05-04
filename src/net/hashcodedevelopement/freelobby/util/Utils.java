package net.hashcodedevelopement.freelobby.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.SpigotConfig;

import net.hashcodedevelopement.freelobby.Lobbysystem;
import net.hashcodedevelopement.freelobby.manager.LanguageManager.Language;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class Utils {

	public static ArrayList<Material> items = new ArrayList<>();

	public static boolean joinMessage, quitMessage, firstMessage, commandWhitelist, singleServer, dailyReward;

	public static int playerCount;
	public static String prefix, fehler, joinMsg, quitMsg, tablistHeader, tablistFooter, commandNotFound;
	public static List<String> firstjoinMsg, dailyRewardCommandList;
	public static String world;
	
	public static File file;
	public static FileConfiguration cfg;

	public static File lobbyitemFile;
	public static FileConfiguration lobbyitemCfg;

	public enum DailyRewardMode {
		COMMAND, COINS, OFF;
	}
	
	static {
		fehler = prefix + "§4§lFehler§7:§c ";

		file = new File("plugins//LobbySystem//Einstellungen//config.yml");
		cfg = YamlConfiguration.loadConfiguration(file);

		lobbyitemFile = new File("plugins//LobbySystem//Einstellungen//LobbyItems.yml");
		lobbyitemCfg = YamlConfiguration.loadConfiguration(lobbyitemFile);
	}
	
	public static void sendActionbar(String message, UUID uuid) {
		PlayerConnection connection = ((CraftPlayer) Bukkit.getPlayer(uuid)).getHandle().playerConnection;
		IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat(icbc, (byte) 2);
		connection.sendPacket(packet);
	}

	public static void loadValues() {
		if (!cfg.contains("Prefix")) {
			cfg.options().header("Variables: %prefix% = Prefix, %PlayerName% = Name of the player, %PlayerCount% = Count of the joined players, %NewLine% = New line | DailyReward Modes: COMMAND = List of custom commands wich will be executed by the console, COINS = (Only works with our CoinsAddon!) This will add the player a custom amount of coins, OFF = No DailyReward");
			cfg.set("Prefix", "&6&lL&e&lobby&6&lS&e&lystem &8» &7");
			lobbyitemCfg.set("0.Slot", 1);
			lobbyitemCfg.set("0.Name", "&7Navigator");
			lobbyitemCfg.set("0.Material", Material.COMPASS.toString());
			lobbyitemCfg.set("0.Command", "/navigator");
			lobbyitemCfg.set("0.Action", Action.RIGHT_CLICK_AIR.toString());
			lobbyitemCfg.set("0.SecondAction", Action.RIGHT_CLICK_BLOCK.toString());
			lobbyitemCfg.set("1.Slot", 5);
			lobbyitemCfg.set("1.Name", "&7Spieler verstecken");
			lobbyitemCfg.set("1.Material", Material.BLAZE_ROD.toString());
			lobbyitemCfg.set("1.Command", "/playerhider");
			lobbyitemCfg.set("1.Action", Action.RIGHT_CLICK_AIR.toString());
			lobbyitemCfg.set("1.SecondAction", Action.RIGHT_CLICK_BLOCK.toString());
			lobbyitemCfg.set("2.Slot", 9);
			lobbyitemCfg.set("2.Name", "&7Profil");
			lobbyitemCfg.set("2.Material", Material.DIAMOND.toString());
			lobbyitemCfg.set("2.Command", "/profil");
			lobbyitemCfg.set("2.Action", Action.RIGHT_CLICK_AIR.toString());
			lobbyitemCfg.set("2.SecondAction", Action.RIGHT_CLICK_BLOCK.toString());
			lobbyitemCfg.set("3.Slot", 3);
			lobbyitemCfg.set("3.Name", "&aErstelle deine &eeigenen&a LobbyItems!");
			lobbyitemCfg.set("3.Material", Material.EMERALD.toString());
			lobbyitemCfg.set("3.Command", "/say Alle Items sind in der LobbyItems.yml editierbar.");
			lobbyitemCfg.set("3.Action", Action.RIGHT_CLICK_AIR.toString());
			lobbyitemCfg.set("3.SecondAction", Action.RIGHT_CLICK_BLOCK.toString());
		}
		if (!cfg.contains("Language")) {
			cfg.set("Language", Language.EN.toString());
		}
		if (!cfg.contains("DailyReward")) {
			 cfg.set("DailyReward.Mode", "COMMAND");
			 List<String> list = new ArrayList<>();
			 list.add("money add %PlayerName% 100");
			 cfg.set("DailyReward.Coins", 100);
			 cfg.set("DailyReward.CommandList", list);
		}
		if (!cfg.contains("JoinNachricht")) {
			cfg.set("JoinNachricht.Bool", true);
			cfg.set("JoinNachricht.Nachricht.DE", "%prefix%Der Spieler &b%PlayerName% &7ist nun &aOnline&7!");
			cfg.set("JoinNachricht.Nachricht.EN", "%prefix%The player &b%PlayerName% &7is now &aonline&7!");
		}
		if (!cfg.contains("QuitNachricht")) {
			cfg.set("QuitNachricht.Bool", true);
			cfg.set("QuitNachricht.Nachricht.DE", "%prefix%Der Spieler &b%PlayerName% &7ist nun &cOffline&7!");
			cfg.set("QuitNachricht.Nachricht.EN", "%prefix%The player &b%PlayerName% &7is now &coffline&7!");
		}
		if (!cfg.contains("CommandWhitelist")) {
			List<String> list = new ArrayList<>();
			list.add("navigator");
			list.add("playerhider");
			list.add("profil");
			cfg.set("CommandWhitelist.List", list);
			cfg.set("CommandWhitelist.Bool", true);
		}
		if (!cfg.contains("GejointeSpieler")) {
			cfg.set("GejointeSpieler", 0);
		}
		if (!cfg.contains("Mode")) {
			cfg.set("Mode.BungeeCord", true);
			cfg.set("Mode.SingleServer", false);
			cfg.set("Mode.SingleServerWorld", "world");
		}
		if (!cfg.contains("CommandNotFound")) {
			cfg.set("CommandNotFound.DE", "%prefix%Dieser Command wurde nicht gefunden!");
			cfg.set("CommandNotFound.EN", "%prefix%This command was not found!");
		}
		if (!cfg.contains("FirstjoinNachricht")) {
			List<String> msg1 = new ArrayList<>();
			msg1.add(" ");
			msg1.add("&8&m--------------------");
			msg1.add("%prefix%Der Spieler %PlayerName% ist neu auf dem Server! &b#%PlayerCount%");
			msg1.add("&8&m--------------------");
			msg1.add(" ");

			List<String> msg2 = new ArrayList<>();
			msg2.add(" ");
			msg2.add("&8&m--------------------");
			msg2.add("%prefix%The player %PlayerName% is new on the Server! &b#%PlayerCount%");
			msg2.add("&8&m--------------------");
			msg2.add(" ");
			
			cfg.set("FirstjoinNachricht.Bool", true);
			cfg.set("FirstjoinNachricht.Nachricht.DE", msg1);
			cfg.set("FirstjoinNachricht.Nachricht.EN", msg2);
		}
		if (!cfg.contains("FirstjoinNachricht.Nachricht.EN")) {
			List<String> msg2 = new ArrayList<>();
			msg2.add(" ");
			msg2.add("&8&m--------------------");
			msg2.add("%prefix%The player %PlayerName% is new on the Server! &b#%PlayerCount%");
			msg2.add("&8&m--------------------");
			msg2.add(" ");
			
			cfg.set("FirstjoinNachricht.Nachricht.EN", msg2);
		}
		if (!cfg.contains("Tablist")) {
			cfg.set("Tablist.Header.DE", "&0%NewLine%&8* &7Dein ServerName &8*%NewLine%&7Dein Motto%NewLine%&0");
			cfg.set("Tablist.Footer.DE", "&0%NewLine%&7Teamspeak: &ats.arzania.eu%NewLine%&7Website: &aarzania.eu%NewLine%&7Twitter: &b@ArzaniaEU%NewLine%&0");
			cfg.set("Tablist.Header.EN", "&0%NewLine%&8* &7Your servername &8*%NewLine%&7Your motto%NewLine%&0");
			cfg.set("Tablist.Footer.EN", "&0%NewLine%&7Teamspeak: &ats.arzania.eu%NewLine%&7Website: &aarzania.eu%NewLine%&7Twitter: &b@ArzaniaEU%NewLine%&0");
		}
		
		saveLobbyitemCfg();
		saveCfg();
		
		Lobbysystem.language = Language.valueOf(cfg.getString("Language"));
		
		singleServer = cfg.getBoolean("Mode.SingleServer");
		if(singleServer){
			world = cfg.getString("Mode.SingleServerWorld");
		}
		
		for (String key : lobbyitemCfg.getKeys(false)) {
			items.add(Material.getMaterial(lobbyitemCfg.getString(key + ".Material")));
		}
		
		String string = cfg.getString("DailyReward.Mode");
		if(string.equalsIgnoreCase("false")){
			string = "OFF";
		}
		Lobbysystem.mode = DailyRewardMode.valueOf(string);

		if(Lobbysystem.language == Language.DE){
			joinMessage = cfg.getBoolean("JoinNachricht.Bool");
			quitMessage = cfg.getBoolean("QuitNachricht.Bool");
			firstMessage = cfg.getBoolean("FirstjoinNachricht.Bool");
			commandWhitelist = cfg.getBoolean("CommandWhitelist.Bool");
			
			tablistFooter = ChatColor.translateAlternateColorCodes('&', cfg.getString("Tablist.Footer.DE").replace("%NewLine%", "\n"));
			tablistHeader = ChatColor.translateAlternateColorCodes('&', cfg.getString("Tablist.Header.DE").replace("%NewLine%", "\n"));
			prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefix"));
			playerCount = cfg.getInt("GejointeSpieler");
			joinMsg = cfg.getString("JoinNachricht.Nachricht.DE");
			quitMsg = cfg.getString("QuitNachricht.Nachricht.DE");
			firstjoinMsg = cfg.getStringList("FirstoinNachricht.Nachricht.DE");
			commandNotFound = cfg.getString("CommandNotFound.DE").replace("%prefix%", prefix);
			
			SpigotConfig.unknownCommandMessage = commandNotFound;
		} else if(Lobbysystem.language == Language.EN){
			joinMessage = cfg.getBoolean("JoinNachricht.Bool");
			quitMessage = cfg.getBoolean("QuitNachricht.Bool");
			firstMessage = cfg.getBoolean("FirstjoinNachricht.Bool");
			commandWhitelist = cfg.getBoolean("CommandWhitelist.Bool");
			
			tablistFooter = ChatColor.translateAlternateColorCodes('&', cfg.getString("Tablist.Footer.EN").replace("%NewLine%", "\n"));
			tablistHeader = ChatColor.translateAlternateColorCodes('&', cfg.getString("Tablist.Header.EN").replace("%NewLine%", "\n"));
			prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefix"));
			playerCount = cfg.getInt("GejointeSpieler");
			joinMsg = cfg.getString("JoinNachricht.Nachricht.EN");
			quitMsg = cfg.getString("QuitNachricht.Nachricht.EN");
			firstjoinMsg = cfg.getStringList("FirstoinNachricht.Nachricht.EN");
			commandNotFound = cfg.getString("CommandNotFound.EN").replace("%prefix%", prefix);
			
			SpigotConfig.unknownCommandMessage = commandNotFound;
		}
	}

	public static void saveCfg() {
		try {
			Utils.cfg.save(Utils.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveLobbyitemCfg() {
		try {
			Utils.lobbyitemCfg.save(Utils.lobbyitemFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void noPermissions(Player player) {
		switch (Lobbysystem.language){
		case DE:
			player.sendMessage(Utils.fehler + "Dazu hast du keine §nBerechtigung§c!");
			break;
		case EN:
			player.sendMessage(Utils.fehler + "You do not have §npermissions§c for this!");
			break;
		default:
			break;
		}
		player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1, 1);
	}

	public static boolean checkName(String Name, ItemStack stack) {
		if (stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()
				&& stack.getItemMeta().getDisplayName().equals(Name)) {
			return true;
		} else {
			return false;
		}
	}

	public static ArrayList<String> buildmode = new ArrayList<>();

	public static boolean isBuildMode(String player) {
		return buildmode.contains(player);
	}

	public static boolean getChatState(UUID uuid){
		boolean toReturn = false;
		
		File file = new File("plugins//LobbySystem//Playerdata//"+uuid+".yml");
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		
		if(!configuration.contains("Chat")){
			configuration.set("Chat", true);
			try {
				configuration.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(configuration.getBoolean("Chat")){
			toReturn = true;
		} else toReturn = false;
		
		return toReturn;
	}
	
	public static String getChatStateString(UUID uniqueId) {
		String toReturn = "";
		switch (Lobbysystem.language){
		case DE:
			toReturn = "§8(§4Fehler§8)";
			break;
		case EN:
			toReturn = "§8(§4Error§8)";
			break;
		default:
			break;
		}
		
		File file = new File("plugins//LobbySystem//Playerdata//"+uniqueId+".yml");
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		
		if(!configuration.contains("Chat")){
			configuration.set("Chat", true);
			try {
				configuration.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(configuration.getBoolean("Chat")){
			switch (Lobbysystem.language){
			case DE:
				toReturn = "§8(§a§lAn§8)";
				break;
			case EN:
				toReturn = "§8(§a§lOn§8)";
				break;
			default:
				break;
			}
		} else {
			switch (Lobbysystem.language){
			case DE:
				toReturn = "§8(§c§lAus§8)";
				break;
			case EN:
				toReturn = "§8(§c§lOff§8)";
				break;
			default:
				break;
			}
		}
		
		return toReturn;
	}
}
