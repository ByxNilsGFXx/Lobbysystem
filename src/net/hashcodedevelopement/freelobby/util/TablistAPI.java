package net.hashcodedevelopement.freelobby.util;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class TablistAPI {
	
	public static void sendTabTitle(Player player, String header, String footer) {
		if (header == null) header = "";
	
		if (footer == null) footer = "";
		
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		
		IChatBaseComponent tabTitle = ChatSerializer.a("{\"text\": \"" + header + "\"}");
		
		IChatBaseComponent tabFoot = ChatSerializer.a("{\"text\": \"" + footer + "\"}");
		
		PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
		
	try {
	
		Field field = headerPacket.getClass().getDeclaredField("b");
		field.setAccessible(true);
		field.set(headerPacket, tabFoot);

	} catch (Exception e) {
	
	e.printStackTrace();
	
	} finally {
	
	connection.sendPacket(headerPacket);
		
	}
	}

}
