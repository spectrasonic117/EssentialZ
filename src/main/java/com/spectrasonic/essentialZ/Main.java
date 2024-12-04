package com.spectrasonic.essentialZ;

import org.bukkit.Bukkit;

// Commands
import com.spectrasonic.essentialZ.Commands.EssentialZ;
import com.spectrasonic.essentialZ.Commands.RepairItem.RepairItem;
import com.spectrasonic.essentialZ.Commands.FlyCommand.FlyCommand;
import com.spectrasonic.essentialZ.Commands.FlyCommand.FlyTabCompleter;
import com.spectrasonic.essentialZ.Commands.GamemodeSwitcher.GameModeSwitcher;
import com.spectrasonic.essentialZ.Commands.Workbenchs.EnderChestCommand;
import com.spectrasonic.essentialZ.Commands.Workbenchs.WorkbenchCommand;
import com.spectrasonic.essentialZ.Commands.Heal.FeedCommand;
import com.spectrasonic.essentialZ.Commands.Heal.HealCommand;
import com.spectrasonic.essentialZ.Commands.Warps.DelWarpCommand;
import com.spectrasonic.essentialZ.Commands.Warps.SetWarpCommand;
import com.spectrasonic.essentialZ.Commands.Warps.WarpCommand;
import com.spectrasonic.essentialZ.Commands.Warps.WarpsCommand;
// Manager
import com.spectrasonic.essentialZ.Manager.WarpManager;

// Listeners
import com.spectrasonic.essentialZ.Listeners.FallDamageListener;

// Utils
import com.spectrasonic.essentialZ.Utils.MessageUtils;

// Other
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    
    private WarpManager warpManager;

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();

        saveDefaultConfig();
        warpManager = new WarpManager(this);

        MessageUtils.sendStartupMessage(this);

    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
    }

    public void registerCommands() {
        // Main Command
        Objects.requireNonNull(getCommand("essentialz")).setExecutor(new EssentialZ(this));

        // Repair Item Command
        Objects.requireNonNull(getCommand("repair")).setExecutor(new RepairItem(this));

        // Gamemode Switcher
        String[] gmCommands = new String[]{"gmc", "gms", "gma", "gmsp", "gm0", "gm1", "gm2", "gm3"};
        GameModeSwitcher commandExecutor = new GameModeSwitcher();

        for (String command : gmCommands) {
            Objects.requireNonNull(getCommand(command), "Command " + command + " not found").setExecutor(commandExecutor);
        }

        // Fly Command
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand(this));
        Objects.requireNonNull(getCommand("fly")).setTabCompleter(new FlyTabCompleter());

        // Heal Command
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand(this));
        Objects.requireNonNull(getCommand("feed")).setExecutor(new FeedCommand(this));

        // Enderchest Command
        Objects.requireNonNull(getCommand("enderchest")).setExecutor(new EnderChestCommand(this));
        Objects.requireNonNull(getCommand("workbench")).setExecutor(new WorkbenchCommand(this));

        // Warp Command
        Objects.requireNonNull(getCommand("warp")).setExecutor(new WarpCommand(this));
        Objects.requireNonNull(getCommand("warps")).setExecutor(new WarpsCommand(this));
        Objects.requireNonNull(getCommand("setwarp")).setExecutor(new SetWarpCommand(this));
        Objects.requireNonNull(getCommand("delwarp")).setExecutor(new DelWarpCommand(this));
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new FallDamageListener(), this);
    }

    public void reloadConfigs() {
        reloadConfig();
        warpManager.reloadWarpsConfig();
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }
}