package net.towerwatch.gui.customitems.listeners.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;

public class GreedySword implements Listener {

    @EventHandler
    public void onPlayerKill(EntityDeathEvent e){
        if(e.getEntity().getKiller() == null) return;
        Player p = e.getEntity().getKiller();
        try {
            if (!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Greedy Sword"))
                return;
        } catch (Exception ex){
            return;
        }
            Random r = new Random();
            int roll;
            EntityDamageEvent ev = e.getEntity().getLastDamageCause();
            if(ev.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK)) roll = r.nextInt(51);
            else roll = r.nextInt(21);
            ItemStack i = switch (roll) {
                case 1 -> new ItemStack(Material.DIAMOND);
                case 2, 3 -> new ItemStack(Material.EMERALD);
                case 4, 5, 6 -> new ItemStack(Material.GOLD_INGOT);
                default -> null;
            };

            if (i != null) e.getDrops().add(i);

    }
}
