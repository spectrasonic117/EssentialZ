package com.spectrasonic.essentialZ.Commands.Warps;

import com.spectrasonic.essentialZ.Main;
import com.spectrasonic.essentialZ.Utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class WarpsCommand implements CommandExecutor {

    private final Main plugin;

    public WarpsCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfigs();
                MessageUtils.sendMessage(sender, "&aConfiguraciones recargadas!");
                return true;
            }
        }
        MessageUtils.sendMessage(sender, "&cUso: /warps <reload>");
        return false;
    }


}
