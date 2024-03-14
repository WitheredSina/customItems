package net.towerwatch.gui.customitems.listeners.weapons;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;

public class CrimsonDagger implements Listener {

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player p){
            Entity entity = e.getEntity();
            try {
                if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Crimson Dagger")) {
                    if (p.getLocation().distance(entity.getLocation()) > 2.5) {
                        //p.sendMessage("more than 2 blocks");
                        e.setCancelled(true);
                    }
                }
            }catch (Exception ex){
                return;
            }
        }
    }
}
