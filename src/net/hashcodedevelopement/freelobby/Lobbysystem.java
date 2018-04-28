package net.hashcodedevelopement.freelobby;


import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.hashcodedevelopement.freelobby.commands.CMD_Navigator;
import net.hashcodedevelopement.freelobby.commands.CMD_Playerhider;
import net.hashcodedevelopement.freelobby.commands.CMD_Profil;
import net.hashcodedevelopement.freelobby.commands.CMD_Spawn;
import net.hashcodedevelopement.freelobby.commands.CMD_Warp;
import net.hashcodedevelopement.freelobby.commands.CMD_build;
import net.hashcodedevelopement.freelobby.listeners.CancelledListener;
import net.hashcodedevelopement.freelobby.listeners.CommandPreprocessListener;
import net.hashcodedevelopement.freelobby.listeners.InteractListener;
import net.hashcodedevelopement.freelobby.listeners.JoinListener;
import net.hashcodedevelopement.freelobby.listeners.QuitListener;
import net.hashcodedevelopement.freelobby.util.Utils;

public class Lobbysystem extends JavaPlugin {

	private static Lobbysystem instance;
	
	public static Plugin friendAddon = null;
	public static Plugin coinsAddon = null;
	public static Plugin chatAddon = null;
	
	public void onEnable() {
		System.out.println(" ");
		System.out.println(" _           _     _           _____           _                 ");
		System.out.println("| |     ___ | |__ | |__  _   _\\ `--. _   _ ___| |_ ___ _ __ ___  ");
		System.out.println("| |    / _ \\| '_ \\| '_ \\| | | |`--. \\ | | / __| __/ _ \\ '_ ` _ \\ ");
		System.out.println("| |___| (_) | |_) | |_) | |_| /\\__/ / |_| \\__ \\ ||  __/ | | | | |");
		System.out.println("\\_____/\\___/|_.__/|_.__/ \\__, \\____/ \\__, |___/\\__\\___|_| |_| |_|");
		System.out.println("                          __/ |       __/ |                      ");
		System.out.println("                         |___/       |___/                      ");
		System.out.println(" ");
		System.out.println("Coded by HashCodeDevelopement | Cerus and Mondstation");
		System.out.println(" ");
		
		instance = this;
		registerCommands();
		registerEvents(Bukkit.getPluginManager());
		loadConfigurations();
		loadAddons();
	}

	public void onDisable() {
		System.out.println(" ");
		System.out.println(" _           _     _           _____           _                 ");
		System.out.println("| |     ___ | |__ | |__  _   _\\ `--. _   _ ___| |_ ___ _ __ ___  ");
		System.out.println("| |    / _ \\| '_ \\| '_ \\| | | |`--. \\ | | / __| __/ _ \\ '_ ` _ \\ ");
		System.out.println("| |___| (_) | |_) | |_) | |_| /\\__/ / |_| \\__ \\ ||  __/ | | | | |");
		System.out.println("\\_____/\\___/|_.__/|_.__/ \\__, \\____/ \\__, |___/\\__\\___|_| |_| |_|");
		System.out.println("                          __/ |       __/ |                      ");
		System.out.println("                         |___/       |___/                      ");
		System.out.println(" ");
		System.out.println("Coded by HashCodeDevelopement | Cerus and Mondstation");
		System.out.println(" ");
	}

	private void loadAddons() {
		if(Bukkit.getPluginManager().isPluginEnabled("HashCodeDev-Friends")){
			System.out.println("Modul \"HashCodeDev-Friends\" wurde gefunden. Aktiviere...");
			friendAddon = Bukkit.getPluginManager().getPlugin("HashCodeDev-Friends");
			System.out.println("Modul \"HashCodeDev-Friends\" wurde aktiviert!");
		}
		if(Bukkit.getPluginManager().isPluginEnabled("HashCodeDev-CoinsAPI")){
			System.out.println("Modul \"HashCodeDev-CoinsAPI\" wurde gefunden. Aktiviere...");
			coinsAddon = Bukkit.getPluginManager().getPlugin("HashCodeDev-CoinsAPI");
			System.out.println("Modul \"HashCodeDev-CoinsAPI\" wurde aktiviert!");
		}
		if(Bukkit.getPluginManager().isPluginEnabled("HashCodeDev-ChatAddon")){
			System.out.println("Modul \"HashCodeDev-ChatAddon\" wurde gefunden. Aktiviere...");
			coinsAddon = Bukkit.getPluginManager().getPlugin("HashCodeDev-ChatAddon");
			System.out.println("Modul \"HashCodeDev-ChatAddon\" wurde aktiviert!");
		}
	}
	
	private void registerCommands() {
		getCommand("build").setExecutor(new CMD_build());
		getCommand("navigator").setExecutor(new CMD_Navigator());
		getCommand("warp").setExecutor(new CMD_Warp());
		getCommand("profil").setExecutor(new CMD_Profil());
		getCommand("spawn").setExecutor(new CMD_Spawn());
		getCommand("playerhider").setExecutor(new CMD_Playerhider());
	}

	private void registerEvents(PluginManager pluginManager) {
		pluginManager.registerEvents(new JoinListener(), this);
		pluginManager.registerEvents(new QuitListener(), this);
		pluginManager.registerEvents(new InteractListener(), this);
		pluginManager.registerEvents(new CancelledListener(), this);
		pluginManager.registerEvents(new CommandPreprocessListener(), this);
	}

	private void loadConfigurations() {
		File permissions = new File("plugins//LobbySystem//Permissions//permissions.yml");
		if (!permissions.exists()) {
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(permissions);
			try {
				cfg.save(permissions);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Utils.loadValues();
	}
	
	public static Lobbysystem getInstance() {
		return instance;
	}

//	private void message(String msg) {
//		Bukkit.getConsoleSender().sendMessage(msg);
//	}
}
