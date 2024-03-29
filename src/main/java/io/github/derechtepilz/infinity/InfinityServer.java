package io.github.derechtepilz.infinity;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

@SuppressWarnings("UnstableApiUsage")
public final class InfinityServer extends JavaPlugin implements InfinityAdapter {

    private final Logger logger;
    private String version;

    public InfinityServer() {
        this.logger = new InfinityLogger().getLogger();
        Infinity.setInstance(this);
    }

    @Override
    public void onLoad() {
        version = getPluginMeta().getVersion();
        logger.info("You are currently running Infinity v" + version);

        // TODO: World system
        // TODO: Item system
        // TODO: Data saving system, ideally with compression, etc.
    }

    @Override
    public void onEnable() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));

        new CommandAPICommand("version")
            .executes(info -> {
                CommandSender sender = info.sender();
                sender.sendMessage(Component.text().content("This server is running Infinity v" + getPluginMeta().getVersion())
                    .color(NamedTextColor.GREEN)
                );
            })
            .register();

        CommandAPI.onEnable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public Logger getInfinityLogger() {
        return logger;
    }
}
