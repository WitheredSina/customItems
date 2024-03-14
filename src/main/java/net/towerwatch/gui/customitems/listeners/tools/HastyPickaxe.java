package net.towerwatch.gui.customitems.listeners.tools;

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
import java.util.UUID;

public class HastyPickaxe implements Listener {
    private Map<UUID, Long> cooldowns = new HashMap<UUID, Long>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action action = e.getAction();
        if(!(action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR))) return;

        try {
            if (!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Hasty Pickaxe"))
                return;
        } catch (Exception ex){
            return;
        }
        if(cooldowns.containsKey(p.getUniqueId())){
            if(cooldowns.get(p.getUniqueId()) > System.currentTimeMillis()){
                long remaining = (cooldowns.get(p.getUniqueId()) - System.currentTimeMillis()) / 1000;
                p.sendMessage(ChatColor.YELLOW + "Hasty Pickaxe " + ChatColor.GREEN + "will be ready in " + ChatColor.AQUA + remaining + " Second(s)");
                return;
            }
        }
        cooldowns.put(p.getUniqueId(), System.currentTimeMillis() + (15 * 1000));

        final PotionEffect haste = new PotionEffect(PotionEffectType.FAST_DIGGING, 10 * 20, 1);
        p.addPotionEffect(haste);
        p.sendTitle(null, ChatColor.GREEN + "Used " + ChatColor.YELLOW + "Hasty Pickaxe", 10, 60, 10);
        p.playSound(p, Sound.ENTITY_IRON_GOLEM_HURT, SoundCategory.PLAYERS, 1f, 2f);

    }
}
