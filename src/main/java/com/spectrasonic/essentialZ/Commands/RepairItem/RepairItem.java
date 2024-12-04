package com.spectrasonic.essentialZ.Commands.RepairItem;

import com.spectrasonic.essentialZ.Utils.MessageUtils;
import com.spectrasonic.essentialZ.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class RepairItem implements CommandExecutor {

    private final Main plugin;

    public RepairItem(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            MessageUtils.sendMessage(sender, "&cOnly players can use this command.");
            return true;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand.getType() == Material.AIR) {
            MessageUtils.sendMessage(sender, "&eYou must hold an item to repair.");
            return true;
        }

        if (!(itemInHand.getItemMeta() instanceof Damageable)) {
            MessageUtils.sendMessage(sender, "&cThis item cannot be repaired.");
            return true;
        }

        if (!player.hasPermission("essentialz.command.repair")) {
            MessageUtils.sendPermissionMensagge(sender);
            return true;
        }

        repairItem(itemInHand);
        MessageUtils.sendMessage(sender, "&aItem successfully repaired!");
        return true;
    }

    private void repairItem(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta instanceof Damageable damageable) {
            damageable.setDamage(0);
            item.setItemMeta(damageable);
        }
    }
}