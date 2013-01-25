package com.CodCraft.infected.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.CodCraft.infected.CodCraft;


public class BuddyCommand implements CommandExecutor {
    private CodCraft plugin;
    
    public BuddyCommand(CodCraft plugin) {
    	this.plugin = plugin;
    }
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("buddy")) {
			if(args[0].equalsIgnoreCase("add")) {
				Player player = (Player) sender;
				Player ki = Bukkit.getPlayer(args[1]);
				plugin.getApi().getBuddy().AddBuddy(player, ki);
				return true;
			}
			if(args[0].equalsIgnoreCase("remove")) {
				Player p = (Player) sender;
				Player k = Bukkit.getPlayer(args[1]);
				plugin.getApi().getBuddy().RemoveBuddy(p,k);
				return true;
			}
			if(args[0].equalsIgnoreCase("list")) {
				Player p = (Player) sender;
				String message = "Your buddies are ";
				for (String s : plugin.getApi().getBuddy().getBuddys(p)) {
					message = message +s + ", ";
				}
				message = message+ ".";
				return true;
			}
		}
		return false;	
	}
}
