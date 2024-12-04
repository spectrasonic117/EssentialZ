package com.spectrasonic.essentialZ.Commands.Warps;

import com.spectrasonic.essentialZ.Main;
import com.spectrasonic.essentialZ.Utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarpCommand implements CommandExecutor {

    private final Main plugin;

    public DelWarpCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                plugin.getWarpManager().delWarp(args[0]);
                MessageUtils.sendMessage(player, "&aWarp &6%s&a deleted!", args[0]);
                return true;
            } else {
                MessageUtils.sendMessage(player, "&cUsage: /delwarp <name>");
            }
        }
        return false;
    }
}
