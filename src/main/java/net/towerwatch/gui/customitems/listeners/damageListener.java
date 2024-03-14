package net.towerwatch.gui.customitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class damageListener implements Listener {
    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e){
        Player p = (Player) e.getDamager();
        Bukkit.broadcastMessage(String.valueOf(e.getFinalDamage()));
    }
}
