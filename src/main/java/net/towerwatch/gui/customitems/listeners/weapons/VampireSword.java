package net.towerwatch.gui.customitems.listeners.weapons;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class VampireSword implements Listener {

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player p){
            ItemStack hand = p.getInventory().getItemInMainHand();
            try {
                if (!hand.getItemMeta().getDisplayName().equals(ChatColor.RED + "Vampire Sword")) return;
            } catch (Exception ex){
                return;
            }

                double damage = e.getDamage();
                double heal = damage / 1.3;
                double health = p.getHealth();
                double maxHealth = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                double statement = heal + health;

                if (statement <= maxHealth){
                    p.setHealth(health + heal);
                    p.playSound(p, Sound.ENTITY_PUFFER_FISH_BLOW_OUT, SoundCategory.PLAYERS, 1f, 1.5f);
                }
                else if(statement == maxHealth){
                    p.setHealth(maxHealth);
                }
                else if (statement > maxHealth){
                    p.setHealth(maxHealth);
                    p.playSound(p, Sound.ENTITY_PUFFER_FISH_BLOW_OUT, SoundCategory.PLAYERS, 1f, 1.5f);
                }

                if(health != maxHealth) return;

                e.setDamage(damage * 1.3);
                p.sendMessage("Blood Rush damage: " + (damage * 1.3));

        }
    }
}
