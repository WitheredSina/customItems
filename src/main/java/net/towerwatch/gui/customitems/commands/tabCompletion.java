package net.towerwatch.gui.customitems.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class tabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if(command.getName().equals("ci")){
            if (args.length == 1) {
                list.add("healthy armor");
                list.add("vampire sword");
                list.add("greedy sword");
                list.add("hasty pickaxe");
                list.add("crimson dagger");
                list.add("rogue sword");
                list.add("intelligence sword");
                list.add("ragnarok warhammer");
                list.add("rangers longbow");
                list.add("giants hammer");
            }
        }
        return list;
    }
}
