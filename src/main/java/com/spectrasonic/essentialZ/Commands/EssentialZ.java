package com.spectrasonic.essentialZ.Commands;

import com.spectrasonic.essentialZ.Main;
import com.spectrasonic.essentialZ.Utils.MessageUtils;
import com.spectrasonic.essentialZ.Utils.SoundUtils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

public class EssentialZ implements CommandExecutor {

    private final Main plugin;

    public EssentialZ(Main plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("version")) {
            if (sender instanceof Player player) {
                SoundUtils.playerSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
            }
            MessageUtils.sendMessage(sender, "Version: &d%s", plugin.getDescription().getVersion());
            MessageUtils.sendMessage(sender, "Developed by: &c%s", plugin.getDescription().getAuthors());
            MessageUtils.sendMessage(sender, "A Plugin of &lNineBlock Studio");
            return true;
        }
        if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
            if (sender instanceof Player player) {
                SoundUtils.playerSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
            }
            sendHelpMessage(sender);
            return true;
        }
        return false;
    }

    public static void sendHelpMessage(CommandSender sender) {
        String[] helpMessages = {
                "&7=== &aEssential &cZ &6Help &7===&r",
                "&7=== Available Commands &6===",
                "",
                "&a/essentialz",
                "&a/repair",
                "&a/gmc",
                "&a/gms",
                "&a/gma",
                "&a/gmsp",
                "&a/gm0",
                "&a/gm1",
                "&a/gm2",
                "&a/gm3",
                "&a/fly",
                "&a/feed",
                "&a/heal",
                "&a/enderchest",
                "&a/workbench",
                "&a/warp",
                "&a/warps",
                "&a/setwarp",
                "&a/delwarp",
        };
        for (String message : helpMessages) {
            MessageUtils.sendMessage(sender, message);
        }
    }
}
