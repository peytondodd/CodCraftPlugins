package com.CodCraft.sad.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.CodCraft.api.modules.GameManager;
import com.CodCraft.sad.CodCraft;

public class EndRoundCommand implements CommandExecutor {
   private CodCraft plugin;

   public EndRoundCommand(CodCraft plugin) {
      this.plugin = plugin;
   }

   @Override
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if(label.equalsIgnoreCase("endround") && args.length == 0) {
         plugin.getApi().getModuleForClass(GameManager.class).endRound();
         plugin.getGame().Savedata();
      }
      return false;
   }
}
