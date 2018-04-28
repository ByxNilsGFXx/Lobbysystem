package net.hashcodedevelopement.freelobby.manager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import net.hashcodedevelopement.freelobby.util.Pair;

public class WarpManager {

	public static File file = new File("plugins//LobbySystem//Warps//Warps.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	public static HashMap<String, Pair<Location, Material>> getWarps() {
		HashMap<String, Pair<Location, Material>> hashMap = new HashMap<>();
		for(String key : cfg.getKeys(false)){
			Pair<Location, Material> pair = new Pair<>();
			pair.setPair((Location) cfg.get(key+".Location"), Material.valueOf(cfg.getString(key+".Icon")));
			hashMap.put(key, pair);
		}
		return hashMap;
	}
	
	public static Pair<Location, Material> getWarp(String warpName){
		if(existWarp(warpName)){
			Pair<Location, Material> pair = new Pair<>();
			pair.setPair((Location) cfg.get(warpName+".Location"), Material.valueOf(cfg.getString(warpName+".Icon")));
			return pair;
		}
		return null;
	}

	public static int getWarpsSize() {
		return getWarps().size();
	}

	public static boolean addWarp(String warpName, Location location, ItemStack itemStack, int i) {
		if (!existWarp(warpName) && itemStack != null && !itemStack.equals(Material.AIR)) {
			for(String key : cfg.getKeys(false)){
				if(cfg.getInt(key+".Slot") == i){
					return false;
				}
			}
			
			cfg.set(warpName+".Location", location);
			cfg.set(warpName+".Icon", itemStack.getType().toString());
			cfg.set(warpName+".SubID", itemStack.getDurability());
			cfg.set(warpName+".Slot", i);
			try {
				cfg.save(file);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public static boolean deleteWarp(String warpName) {
		if (existWarp(warpName)) {
			cfg.set(warpName, null);
			try {
				cfg.save(file);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public static boolean existWarp(String warpName) {
		return cfg.contains(warpName);
	}

}
