package com.spectrasonic.essentialZ.Commands.Heal;

import com.spectrasonic.essentialZ.Main;
import com.spectrasonic.essentialZ.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    private final Main plugin;

    public FeedCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("feed")) {
            if (!sender.hasPermission("essentialz.command.feed")) {
                MessageUtils.sendPermissionMensagge(sender);
                return true;
            }

            if (args.length == 0 && sender instanceof Player) {
                feedPlayer((Player) sender);
                MessageUtils.sendMessage(sender, "&aYou have been &dFeeded.");
                return true;
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("all")) {
                    healAllPlayers();
                    MessageUtils.broadcastMessage("&aAll players have been &dFeeded.");
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        feedPlayer(target);
                        MessageUtils.sendMessage(sender, "&aFeed player: " + target.getName());
                        MessageUtils.sendMessage(target, "&aYou have been &dFeeded &aby &f&lSERVER&r");

                    } else {
                        MessageUtils.sendMessage(sender, "&cPlayer not found.");
                    }
                }
                return true;
            }

            return false;
        }
        return false;
    }

    /**
     * Heal a specific player to full health
     * @param player Player to heal
     */
    private void feedPlayer(Player player) {
        player.setFoodLevel(20);
        player.setSaturation(20);
    }

    private void healAllPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            feedPlayer(player);
        }
    }

}
