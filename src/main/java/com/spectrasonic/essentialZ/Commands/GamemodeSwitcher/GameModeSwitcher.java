package com.spectrasonic.essentialZ.Commands.GamemodeSwitcher;

import com.spectrasonic.essentialZ.Utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;


public class GameModeSwitcher implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("&cOnly players can execute this command.");
            return true;
        }
        switch (label.toLowerCase()) {
            case "gmc", "gm0":
                player.setGameMode(GameMode.CREATIVE);
                MessageUtils.sendMessage(player, "&aChanged to &eCreative &amode!");
                break;
            case "gms", "gm1":
                player.setGameMode(GameMode.SURVIVAL);
                MessageUtils.sendMessage(player, "&aChanged to &eSurvival &amode!");
                break;
            case "gma", "gm2":
                player.setGameMode(GameMode.ADVENTURE);
                MessageUtils.sendMessage(player, "&aChanged to &eAdventure &amode!");
                break;
            case "gmsp", "gm3":
                player.setGameMode(GameMode.SPECTATOR);
                MessageUtils.sendMessage(player, "&aChanged to &eSpectator &amode!");
                break;
            default:
                MessageUtils.sendMessage(sender, "&cInvalid GameMode.");
                return false;
        }
        return true;
    }
}
