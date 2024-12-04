package com.spectrasonic.essentialZ.Commands.Warps;

import com.spectrasonic.essentialZ.Main;
import com.spectrasonic.essentialZ.Utils.MessageUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    private final Main plugin;

    public SetWarpCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                Location location = player.getLocation();
                plugin.getWarpManager().setWarp(args[0], location);
                MessageUtils.sendMessage(player, "&aWarp &6%s&a set!", args[0]);
                return true;
            } else {
                MessageUtils.sendMessage(player, "&cYou must specify a name.");
            }
        }
        return false;
    }
}
