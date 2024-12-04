package com.spectrasonic.essentialZ.Commands.Workbenchs;

import com.spectrasonic.essentialZ.Main;
import com.spectrasonic.essentialZ.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EnderChestCommand implements CommandExecutor {

    private final Main plugin;

    public EnderChestCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player playerSender)) {
            MessageUtils.sendMessage(sender, "&cThis command can only be used by players.");
            return true;
        }

        if (!playerSender.hasPermission("essentialz.command.enderchest")) {
            MessageUtils.sendPermissionMensagge(playerSender);
            return true;
        }

        if (args.length != 1) {
            MessageUtils.sendMessage(playerSender, "&eUsage: /enderchest <player>");
            return true;
        }

        Player targetPlayer = Bukkit.getPlayerExact(args[0]);
        if (targetPlayer == null) {
            MessageUtils.sendMessage(playerSender, "&cPlayer not found.");
            return true;
        }

        Inventory enderChest = targetPlayer.getEnderChest();
        playerSender.openInventory(enderChest);

        return true;
    }
}
