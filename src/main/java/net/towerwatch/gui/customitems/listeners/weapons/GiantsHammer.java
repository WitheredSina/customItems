package net.towerwatch.gui.customitems.listeners.weapons;

import net.towerwatch.gui.customitems.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.Random;

public class GiantsHammer implements Listener {
    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e){
        if (!(e.getDamager() instanceof Player p)) return;
        try {
            if (!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Giant's Hammer"))
                return;
        }catch (Exception ex){
            return;
        }
        if (!(e.getEntity() instanceof LivingEntity)) return;


        if(new Random().nextInt(5) != 4) return;

        PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 3 * 10, 2, false, false);
        PotionEffect weak = new PotionEffect(PotionEffectType.WEAKNESS, 3 * 10, 2, false, false);
        PotionEffect blind = new PotionEffect(PotionEffectType.BLINDNESS, 3 * 10, 2, false, false);
        PotionEffect fatigue = new PotionEffect(PotionEffectType.SLOW_DIGGING, 3 * 10, 2, false, false);
        PotionEffect[] effects = {slow, weak, blind, fatigue};

        Entity entity = e.getEntity();
        if(!(entity instanceof LivingEntity livingEntity)) return;

        for(PotionEffect effect : effects) livingEntity.addPotionEffect(effect);

        p.playSound(p, Sound.BLOCK_ANVIL_LAND, SoundCategory.PLAYERS, 1f, 0.3f);
    }
}
