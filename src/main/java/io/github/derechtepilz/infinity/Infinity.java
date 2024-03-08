package io.github.derechtepilz.infinity;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Infinity extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));

        new CommandAPICommand("version")
            .executes(info -> {
                CommandSender sender = info.sender();
                sender.sendMessage(Component.text().content("This server is running Infinity-" + getPluginMeta().getVersion())
                    .color(NamedTextColor.GREEN)
                );
            })
            .register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
