package com.codcraft.weapons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ReloadTimer implements Runnable {
	
	private Weapons plugin;
	private Player player;
	private int arrow;
	private double i;
	public ReloadTimer(Weapons plugin, Player player, int arrows, double d) {
		this.plugin = plugin;
		this.player = player;
		this.arrow = arrows;
		this.i = d;
	}
	
	@Override
	public void run() {
		if(player.getExp() <= 0) {
			player.getInventory().addItem(new ItemStack(Material.ARROW, arrow));
			plugin.reloaders.remove(player.getName());
			return;
		} else {
			player.setExp((float) (player.getExp() - i));
			Bukkit.getScheduler().runTaskLater(plugin, new ReloadTimer(plugin, player, arrow, i), 5);
		}

	}

}
