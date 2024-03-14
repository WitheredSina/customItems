package net.towerwatch.gui.customitems;

import net.towerwatch.gui.customitems.bows.RangersLongbow;
import net.towerwatch.gui.customitems.commands.giveItem;
import net.towerwatch.gui.customitems.commands.tabCompletion;
import net.towerwatch.gui.customitems.listeners.weapons.*;
import net.towerwatch.gui.customitems.listeners.tools.HastyPickaxe;
import net.towerwatch.gui.customitems.listeners.damageListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomItems extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager p = getServer().getPluginManager();

        p.registerEvents(new VampireSword(), this);
        p.registerEvents(new GreedySword(), this);
        p.registerEvents(new HastyPickaxe(), this);
        p.registerEvents(new damageListener(), this);
        p.registerEvents(new CrimsonDagger(), this);
        p.registerEvents(new RogueSword(), this);
        p.registerEvents(new IntelligenceSword(), this);
        p.registerEvents(new RagnarokAxe(), this);
        p.registerEvents(new RangersLongbow(), this);
        p.registerEvents(new GiantsHammer(), this);

        getCommand("ci").setExecutor(new giveItem());

        PluginCommand command = getCommand("ci");
        command.setTabCompleter(new tabCompletion());
    }
}
