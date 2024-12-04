package com.spectrasonic.essentialZ.Commands.Heal;

import com.spectrasonic.essentialZ.Main;
import com.spectrasonic.essentialZ.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HealCommand implements CommandExecutor {

    public HealCommand(Main plugin) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("heal")) {
            if (!sender.hasPermission("essentialz.command.heal")) {
                MessageUtils.sendPermissionMensagge(sender);
                return true;
            }

            if (args.length == 0 && sender instanceof Player) {
                healPlayer((Player) sender);
                MessageUtils.sendMessage(sender, "&aYou have been &dHealed");
                return true;
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("all")) {
                    healAllPlayers();
                    MessageUtils.broadcastMessage("&aAll players have been &dhealed");
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        healPlayer(target);
                        sender.sendMessage("&aHealed player: " + target.getName());
                        target.sendMessage("&aYou have been &dhealed &aby &f&lSERVER&r");
                    } else {
                        sender.sendMessage("&cPlayer not found.");
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
    private void healPlayer(Player player) {
        player.setHealth(Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getDefaultValue());

        player.setFoodLevel(20);
        player.setSaturation(20);

        player.setFireTicks(0);
    }

    private void healAllPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            healPlayer(player);
        }
    }

}
