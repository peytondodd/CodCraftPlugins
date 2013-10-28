package com.codcraft.ccommands;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.CodCraft.api.CCAPI;
import com.codcraft.cchat.AntiAd;

public class CCCommands extends JavaPlugin {

	public CCAPI api;
	public Connection con;
	public AntiAd cchat;
	public Chat chat;
	public Permission permi;
	


	public void onEnable() {
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		final Plugin api = this.getServer().getPluginManager().getPlugin("CodCraftAPI");
	      if(api != null || !(api instanceof CCAPI)) {
	         this.api = (CCAPI) api;
	      }
	   final Plugin cchat = this.getServer().getPluginManager().getPlugin("CCChat");
	       if(cchat != null ) {
	    	   this.cchat = (AntiAd) cchat;
		   }
	    getServer().getScheduler().runTaskTimer(this, new Advertisment(), 0, 18000);
	    getServer().getPluginManager().registerEvents(new CCListener(this), this);  
	    YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("./plugins/CCCommands/ads.yml"));
		double x = config.getDouble("x");
		double y = config.getDouble("y");
		double z = config.getDouble("z");
		System.out.println("X: " + x + " Y: " + y + " Z: " + z);
		getCommand("lobby").setExecutor(new SpawnCommand(this, x, y, z));
		getCommand("a").setExecutor(new AdminCommand(this));
		getCommand("gameinfo").setExecutor(new GameInfoCommand(this));
		getCommand("CSP").setExecutor(new CSPCommand(this));
		getCommand("class").setExecutor(new ClassCommand(this));
		getCommand("setwarp").setExecutor(new SetWarpCommand(this)); // Don't forget to add these to plugin.yml! :D
		getCommand("delwarp").setExecutor(new SetWarpCommand(this));
		getCommand("remwarp").setExecutor(new SetWarpCommand(this));
		getCommand("w").setExecutor(new SetWarpCommand(this));
		
		connect();
		Bukkit.getScheduler().runTaskTimer(this, new LeaderBoard(this), 0, 3600);
		setupPermissions();
		setupChat();
		
	}
    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
        	permi = permissionProvider.getProvider();
        }
        return (permi != null);
    }

    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }
	
	
	private boolean connect() {
		con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + "localhost/" + "minecraft", "root", "YDsD9Cz3");
			getLogger().info("Got connection!");
		} catch (SQLException e) {
			getLogger().log(Level.WARNING, "Connection not found");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
