package net.towerwatch.gui.customitems.bows;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class RangersLongbow implements Listener {
    @EventHandler
    public void onHittingEntityWithArrow(EntityDamageByEntityEvent e){
        if (!(e.getDamager() instanceof Arrow arrow)) return;
        Player p = (Player) arrow.getShooter();

        //Bukkit.broadcastMessage(e.getDamager().getType().name() + "\ndamage: " + e.getDamage() + "\nplayer: " + p.getName());
        try {
            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Ranger's Longbow")) {
                Entity entity = e.getEntity();
                double distance = entity.getLocation().distance(p.getLocation());

                e.setDamage(e.getDamage() + (distance / 2));
                p.sendMessage("damage: " + e.getDamage());
                p.sendMessage("real damage: " + (e.getDamage() + (distance / 2)));
            }
        } catch (Exception ex) {
            return;
        }
    }
}
