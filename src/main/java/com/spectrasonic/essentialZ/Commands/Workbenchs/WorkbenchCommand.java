package com.spectrasonic.essentialZ.Commands.Workbenchs;

import com.spectrasonic.essentialZ.Main;
import com.spectrasonic.essentialZ.Utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkbenchCommand implements CommandExecutor {
    private final Main plugin;

    public WorkbenchCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cOnly players can use this command.");
            MessageUtils.sendMessage(sender, "&cThis command can only be used by players.");
            return true;
        }

        // Check permission
        if (!player.hasPermission("essentialz.command.workbench")) {
            MessageUtils.sendPermissionMensagge(player);
            return true;
        }

        // Open workbench inventory
        player.openWorkbench(null, true);

        return true;
    }
}