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

public class RogueSword implements Listener {
    private Map<String, Long> cooldown = new HashMap<String, Long>();
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action action = e.getAction();
        try {
            if (!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Rogue Sword"))
                return;
        } catch (Exception ex){
            return;
        }

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {

            if (cooldown.containsKey(p.getName())) {
                if (cooldown.get(p.getName()) > System.currentTimeMillis()) {
                    long remaining = (cooldown.get(p.getName()) - System.currentTimeMillis()) / 1000;
                    p.sendMessage(ChatColor.RED + "Rogue Sword " + ChatColor.GREEN + "will be ready in " + ChatColor.AQUA + remaining + " Second(s)");
                    return;
                }
            }

            cooldown.put(p.getName(), System.currentTimeMillis() + (20 * 1000));

            PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 10 * 20, 2);
            p.addPotionEffect(speed);

            p.playSound(p, Sound.ENTITY_LEASH_KNOT_PLACE, SoundCategory.PLAYERS, 1f, 3f);


        }
    }
}
