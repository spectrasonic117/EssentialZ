package com.spectrasonic.essentialZ.Commands.FlyCommand;

import com.spectrasonic.essentialZ.Main;
import com.spectrasonic.essentialZ.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private final Main plugin;

    public FlyCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!sender.isOp()) {
            MessageUtils.sendPermissionMensagge(sender);
            return true;
        }

        if (args.length == 0) {
            if (sender instanceof Player) {
                toggleFly((Player) sender);
            } else {
                MessageUtils.sendMessage(sender, "&cThis command can only be used by players.");
            }
        } else {
            if (args[0].equalsIgnoreCase("all")) {
                toggleFlyForAll(sender);
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    toggleFly(target);
                } else {
                    MessageUtils.sendMessage(sender, "&cPlayer %s is not online.", args[0]);
                }
            }
        }
        return true;
    }

    private void toggleFly(Player player) {
        boolean canFly = !player.getAllowFlight();
        player.setAllowFlight(canFly);
        MessageUtils.sendMessage(player, "Fly mode is %s.", canFly ? "&aON" : "&cOFF");
    }

    private void toggleFlyForAll(CommandSender sender) {
        Bukkit.getOnlinePlayers().forEach(this::toggleFly);
        MessageUtils.sendMessage(sender, "Fly mode has been changed for all players.");
    }
}
