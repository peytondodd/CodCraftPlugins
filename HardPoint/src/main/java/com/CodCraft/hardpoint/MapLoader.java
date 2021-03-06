package com.CodCraft.hardpoint;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;


public class MapLoader extends CodCraft {
	
	public final static File Worldsyml = new File("./CodCraft/worlds.yml");
	public static HashMap<String, Integer>		mapTime		= new HashMap<String, Integer>();
	public static HashMap<String, Boolean>		mapWeather		= new HashMap<String, Boolean>();
	public static ArrayList<World>				Maps			= new ArrayList<World>();
	public MapLoader() {
		if(!Worldsyml.exists()) {
			try { Worldsyml.createNewFile(); 
			} catch (IOException e) { e.printStackTrace(); }
		}
		YamlConfiguration config = YamlConfiguration.loadConfiguration(Worldsyml);
		for(String s : config.getConfigurationSection("Worlds").getKeys(false)) {
			Bukkit.createWorld(new WorldCreator(s));
			int a = config.getInt("Worlds."+s+".Time");
			String Weather = config.getString("Worlds."+s+".Weather");
			Boolean map = config.getBoolean("Worlds."+s+".Map");
			if(map) {
				World maps = Bukkit.getWorld(s);
				Maps.add(maps);
			}
			if(map == true) {
				
			} else {
				
			}

			Boolean b = null;
			if(Weather.equalsIgnoreCase("sun")) {
				b = false;
			}
			if(Weather.equalsIgnoreCase("rain")) {
				b = true;
			}
			if(Weather.equalsIgnoreCase("sun")) {
				b =true; 
			}
			if(b != null) {
				mapWeather.put(s, b);
				mapTime.put(s, a);
			}			
		}

	}
	@Deprecated
	public static void LoadWorlds() {
		if(!Worldsyml.exists()) {
			try { Worldsyml.createNewFile(); 
			} catch (IOException e) { e.printStackTrace(); }
		}
		YamlConfiguration config = YamlConfiguration.loadConfiguration(Worldsyml);
		for(String s : config.getConfigurationSection("Worlds").getKeys(false)) {
			Bukkit.createWorld(new WorldCreator(s));
			int a = config.getInt("Worlds."+s+".Time");
			String Weather = config.getString("Worlds."+s+".Weather");
			Boolean map = config.getBoolean("Worlds."+s+".Map");
			if(map) {
				World maps = Bukkit.getWorld(s);
				Maps.add(maps);
			}
			if(map == true) {
				
			} else {
				
			}

			Boolean b = null;
			if(Weather.equalsIgnoreCase("sun")) {
				b = false;
			}
			if(Weather.equalsIgnoreCase("rain")) {
				b = true;
			}
			if(Weather.equalsIgnoreCase("sun")) {
				b =true; 
			}
			if(b != null) {
				mapWeather.put(s, b);
				mapTime.put(s, a);
			}			
		}
	}
		
		
}