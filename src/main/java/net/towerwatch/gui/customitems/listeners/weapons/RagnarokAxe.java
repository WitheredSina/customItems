package net.towerwatch.gui.customitems.listeners.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class RagnarokAxe implements Listener {

    Map<String, Long> cooldown = new HashMap<String, Long>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        try {
            if (!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Ragnarok Warhammer"))
                return;
        } catch (Exception ex){
            return;
        }

        Action action = e.getAction();
        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {

            if (cooldown.containsKey(p.getName())) {
                if (cooldown.get(p.getName()) > System.currentTimeMillis()) {
                    long remaining = (cooldown.get(p.getName()) - System.currentTimeMillis()) / 1000;
                    p.sendMessage(ChatColor.RED + "Ragnarok Warhammer " + ChatColor.GREEN + "will be ready in " + ChatColor.AQUA + remaining + " Second(s)");
                    return;
                }
            }
            cooldown.put(p.getName(), System.currentTimeMillis() + (25 * 1000));


            p.playSound(p, Sound.ENTITY_WITHER_DEATH, SoundCategory.HOSTILE, 1f, 0.5f);
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 8 * 20, 1, false, false));
        }
    }
}
