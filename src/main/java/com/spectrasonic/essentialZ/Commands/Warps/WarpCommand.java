package com.spectrasonic.essentialZ.Commands.Warps;

import com.spectrasonic.essentialZ.Main;
import com.spectrasonic.essentialZ.Utils.MessageUtils;
import com.spectrasonic.essentialZ.Utils.SoundUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WarpCommand implements CommandExecutor, TabCompleter {

    private final Main plugin;

    public WarpCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (sender instanceof Player player) {
                Location location = plugin.getWarpManager().getWarp(args[0]);
                if (location != null) {
                    player.teleport(location);
                    SoundUtils.playerSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 0.5f);
                    MessageUtils.sendMessage(player, "&aTeleported to &6%s&a.", args[0]);
                    return true;
                } else {
                    MessageUtils.sendMessage(player, "&cWarp not found!");
                }
            } else {
                MessageUtils.sendMessage(sender, "&cYou must specify a name.");
            }
        } else if (args.length == 2) {
            if ("all".equalsIgnoreCase(args[1])) {
                Location location = plugin.getWarpManager().getWarp(args[0]);
                if (location != null) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.teleport(location);
                        MessageUtils.sendMessage(player, "&aTeleported to &6%s&a.", args[0]);
                    }
                    return true;
                } else {
                    MessageUtils.sendMessage(sender, "&cWarp not found!");
                }
            } else {
                Player target = Bukkit.getPlayerExact(args[1]);
                if (target != null) {
                    Location location = plugin.getWarpManager().getWarp(args[0]);
                    if (location != null) {
                        target.teleport(location);
                        MessageUtils.sendMessage(sender, "&aTeleported &6%s&a to &6%s&a.", args[1], args[0]);
                        return true;
                    } else {
                        MessageUtils.sendMessage(sender, "&cWarp not found!");
                    }
                } else {
                    MessageUtils.sendMessage(sender, "&cPlayer not found!");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("warp")) {
            if (args.length == 1) {
                return new ArrayList<>(plugin.getWarpManager().getWarpNames());
            } else if (args.length == 2) {
                List<String> playerNames = plugin.getServer().getOnlinePlayers().stream()
                        .map(Player::getName)
                        .collect(Collectors.toList());
                playerNames.add("all");
                return playerNames;
            }
        } else if (command.getName().equalsIgnoreCase("setwarp") || command.getName().equalsIgnoreCase("delwarp")) {
            if (args.length == 1) {
                return new ArrayList<>(plugin.getWarpManager().getWarpNames());
            }
        }
        return Collections.emptyList();
    }


}
