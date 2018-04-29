package net.hashcodedevelopement.freelobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.hashcodedevelopement.coinsapi.CoinsAPI;
import net.hashcodedevelopement.freelobby.Lobbysystem;
import net.hashcodedevelopement.freelobby.util.ItemBuilder;
import net.hashcodedevelopement.freelobby.util.Utils;

public class CMD_Profil implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Inventory inventory = Bukkit.createInventory(null, 9, "§b§lProfil");
			Player player = (Player) sender;
			
			switch (Lobbysystem.language){
			case DE:
				if(Lobbysystem.friendAddon != null){
					inventory.setItem(0, new ItemBuilder(Material.SKULL_ITEM, 0).setAmount(1).setDisplayname("§aFreunde").build());	
				} else inventory.setItem(0, new ItemBuilder(Material.BARRIER, 0).setAmount(1).setDisplayname("§aFreunde").setLore("§4✗").build());
				if(Lobbysystem.coinsAddon != null){
					inventory.setItem(2, new ItemBuilder(Material.GOLD_NUGGET, 0).setAmount(1).setDisplayname("§6Coins:").setLore("§e"+new CoinsAPI().getCoins(player.getUniqueId())).build());	
				} else inventory.setItem(2, new ItemBuilder(Material.BARRIER, 0).setAmount(1).setDisplayname("§6Coins:").setLore("§4✗").build());
				if(Lobbysystem.chatAddon != null){
					inventory.setItem(3, new ItemBuilder(Material.BOOK, 0).setAmount(1).setDisplayname("§bChat "+Utils.getChatStateString(player.getUniqueId())).build());
				} else inventory.setItem(3, new ItemBuilder(Material.BARRIER, 0).setAmount(1).setDisplayname("§bChat").setLore("§4✗").build());
				break;
			case EN:
				if(Lobbysystem.friendAddon != null){
					inventory.setItem(0, new ItemBuilder(Material.SKULL_ITEM, 0).setAmount(1).setDisplayname("§aFriends").build());	
				} else inventory.setItem(0, new ItemBuilder(Material.BARRIER, 0).setAmount(1).setDisplayname("§aFriends").setLore("§4✗").build());
				if(Lobbysystem.coinsAddon != null){
					inventory.setItem(2, new ItemBuilder(Material.GOLD_NUGGET, 0).setAmount(1).setDisplayname("§6Coins:").setLore("§e"+new CoinsAPI().getCoins(player.getUniqueId())).build());	
				} else inventory.setItem(2, new ItemBuilder(Material.BARRIER, 0).setAmount(1).setDisplayname("§6Coins:").setLore("§4✗").build());
				if(Lobbysystem.chatAddon != null){
					inventory.setItem(3, new ItemBuilder(Material.BOOK, 0).setAmount(1).setDisplayname("§bChat "+Utils.getChatStateString(player.getUniqueId())).build());
				} else inventory.setItem(3, new ItemBuilder(Material.BARRIER, 0).setAmount(1).setDisplayname("§bChat").setLore("§4✗").build());
				break;
			default:
				break;
			}
			
			player.openInventory(inventory);
		}
		return false;
	}

}
