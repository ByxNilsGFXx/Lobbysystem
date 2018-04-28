package net.hashcodedevelopement.freelobby.util;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

	private ItemStack stack;

	public ItemBuilder(Material mat, int subID) {
		this.stack = new ItemStack(mat, (short) subID);
	}

	@SuppressWarnings("deprecation")
	public ItemBuilder(int id, int subID) {
		this.stack = new ItemStack(id, (short) subID);
	}

	public ItemBuilder(ItemStack stack) {
		this.stack = stack;
	}

	public ItemBuilder setAmount(int amount) {
		stack.setAmount(amount);
		return this;
	}

	public ItemBuilder setSubID(int subID) {
		stack.setDurability((short) subID);
		return this;
	}

	public ItemBuilder setMeta(ItemMeta meta) {
		stack.setItemMeta(meta);
		return this;
	}

	public ItemBuilder setDisplayname(String name) {
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(name);
		stack.setItemMeta(meta);
		return this;
	}

	public ItemBuilder setMaterial(Material material) {
		stack.setType(material);
		return this;
	}

	public ItemBuilder setLore(ArrayList<String> lore) {
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(lore);
		stack.setItemMeta(meta);
		return this;
	}

	public ItemBuilder setLore(String lore) {
		ArrayList<String> loreArray = new ArrayList<>();
		loreArray.add(lore);
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(loreArray);
		stack.setItemMeta(meta);
		return this;
	}

	public ItemBuilder setLore(String[] lore) {
		ArrayList<String> loreArray = new ArrayList<>();
		for (int i = 0; i < lore.length; i++) {
			loreArray.add(lore[i]);
		}
		ItemMeta meta = stack.getItemMeta();
		meta.setLore(loreArray);
		stack.setItemMeta(meta);
		return this;
	}

	public ItemBuilder addEchantment(Enchantment ench, int level){
		stack.addUnsafeEnchantment(ench, level);
		return this;
	}
	
	public ItemBuilder addFlags(ItemFlag flag){
		ItemMeta meta = stack.getItemMeta();
		meta.addItemFlags(flag);
		stack.setItemMeta(meta);
		return this;
	}
	
	public ItemStack build(){
		return stack;
	}
	
}
