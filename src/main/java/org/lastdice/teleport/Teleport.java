package org.lastdice.teleport;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.lastdice.teleport.command.LearnCommand;

public final class Teleport extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().warning("플러그인 활서화");

        //
        Bukkit.getCommandMap().register("LearnCommand", new LearnCommand("test"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().warning("플러그인 비활성화");
    }
}
