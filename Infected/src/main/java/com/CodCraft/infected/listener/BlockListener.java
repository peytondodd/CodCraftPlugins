package com.CodCraft.infected.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;

import com.CodCraft.api.modules.Weapons;
import com.CodCraft.infected.CodCraft;

public class BlockListener extends CodCraft implements Listener {
	private CodCraft plugin;

	   public BlockListener(CodCraft plugin) {
	      this.plugin = plugin;
	   }
    @EventHandler
    public void onExsplotion(EntityExplodeEvent  e) {
    	e.blockList().clear();
    }
    @EventHandler
    public void OnBreak(BlockBreakEvent event) {
    	Player p = event.getPlayer();
    	if (p.getGameMode() == GameMode.CREATIVE) {	
    	} else {
    		if(event.getBlock().getType() == Material.THIN_GLASS) {
    		} else {
        		event.setCancelled(true);
    		}
    	}
    }
    @SuppressWarnings("deprecation")
	@EventHandler
    public void SwitchPlaceE(BlockPlaceEvent e) {
    	if(e.getBlockPlaced().getType() == Material.LEVER) {
    		plugin.api.getModuleForClass(Weapons.class).setC4Spot(e.getPlayer(), e.getBlockPlaced().getLocation());
    		e.getPlayer().getInventory().setItemInHand(new ItemStack(Material.STICK));
    		e.getPlayer().updateInventory();
    	}
    }
}
