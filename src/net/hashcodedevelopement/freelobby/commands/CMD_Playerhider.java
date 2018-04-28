package net.hashcodedevelopement.freelobby.commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import net.hashcodedevelopement.freelobby.util.ItemBuilder;
import net.hashcodedevelopement.freelobby.util.Utils;

public class CMD_Playerhider implements CommandExecutor {

	public enum HIDE {
		SHOW_ALL, SHOW_VIP, SHOW_NONE;
	}

	public static HashMap<UUID, HIDE> hashMap = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			if (Utils.singleServer && ((Player) sender).getWorld().getName().equals(Utils.world)) {
				Player player = (Player) sender;
				Inventory inventory = Bukkit.createInventory(null, InventoryType.BREWING, "§a§lSpieler verstecken");

				switch (hashMap.get(player.getUniqueId())) {
				case SHOW_ALL:
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(10)
							.setDisplayname("§aAlle Spieler zeigen").addEchantment(Enchantment.DURABILITY, 1)
							.addFlags(ItemFlag.HIDE_ENCHANTS).build());
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(13)
							.setDisplayname("§5Zeige nur VIP's").build());
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(8)
							.setDisplayname("§7Alle Spieler ausblenden").build());
					break;
				case SHOW_NONE:
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(10)
							.setDisplayname("§aAlle Spieler zeigen").build());
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(13)
							.setDisplayname("§5Zeige nur VIP's").build());
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(8)
							.setDisplayname("§7Alle Spieler ausblenden").addEchantment(Enchantment.DURABILITY, 1)
							.addFlags(ItemFlag.HIDE_ENCHANTS).build());
					break;
				case SHOW_VIP:
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(10)
							.setDisplayname("§aAlle Spieler zeigen").build());
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(13)
							.setDisplayname("§5Zeige nur VIP's").addEchantment(Enchantment.DURABILITY, 1)
							.addFlags(ItemFlag.HIDE_ENCHANTS).build());
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(8)
							.setDisplayname("§7Alle Spieler ausblenden").build());
					break;
				default:
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(10)
							.setDisplayname("§aAlle Spieler zeigen").addEchantment(Enchantment.DURABILITY, 1)
							.addFlags(ItemFlag.HIDE_ENCHANTS).build());
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(13)
							.setDisplayname("§5Zeige nur VIP's").build());
					inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(8)
							.setDisplayname("§7Alle Spieler ausblenden").build());
					break;
				}

				player.openInventory(inventory);
			} else {
				if (!Utils.singleServer) {
					Player player = (Player) sender;
					Inventory inventory = Bukkit.createInventory(null, InventoryType.BREWING, "§a§lSpieler verstecken");

					switch (hashMap.get(player.getUniqueId())) {
					case SHOW_ALL:
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(10)
								.setDisplayname("§aAlle Spieler zeigen").addEchantment(Enchantment.DURABILITY, 1)
								.addFlags(ItemFlag.HIDE_ENCHANTS).build());
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(13)
								.setDisplayname("§5Zeige nur VIP's").build());
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(8)
								.setDisplayname("§7Alle Spieler ausblenden").build());
						break;
					case SHOW_NONE:
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(10)
								.setDisplayname("§aAlle Spieler zeigen").build());
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(13)
								.setDisplayname("§5Zeige nur VIP's").build());
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(8)
								.setDisplayname("§7Alle Spieler ausblenden").addEchantment(Enchantment.DURABILITY, 1)
								.addFlags(ItemFlag.HIDE_ENCHANTS).build());
						break;
					case SHOW_VIP:
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(10)
								.setDisplayname("§aAlle Spieler zeigen").build());
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(13)
								.setDisplayname("§5Zeige nur VIP's").addEchantment(Enchantment.DURABILITY, 1)
								.addFlags(ItemFlag.HIDE_ENCHANTS).build());
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(8)
								.setDisplayname("§7Alle Spieler ausblenden").build());
						break;
					default:
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(10)
								.setDisplayname("§aAlle Spieler zeigen").addEchantment(Enchantment.DURABILITY, 1)
								.addFlags(ItemFlag.HIDE_ENCHANTS).build());
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(13)
								.setDisplayname("§5Zeige nur VIP's").build());
						inventory.addItem(new ItemBuilder(Material.INK_SACK, 0).setAmount(1).setSubID(8)
								.setDisplayname("§7Alle Spieler ausblenden").build());
						break;
					}

					player.openInventory(inventory);
				}
			}
		}
		return false;
	}

}
