package net.towerwatch.gui.customitems.listeners.weapons;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class IntelligenceSword implements Listener {
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e){
        Player p = (Player) e.getDamager();
        double damage = e.getDamage();
        double xp = p.getLevel() * 0.5;
        try {
            if (!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Intelligence Sword"))
                return;
        } catch (Exception ex){
                return;
            }
            e.setDamage(damage + xp);
            p.sendMessage("intelligence dmg: " + (damage + xp));

    }

    @EventHandler
    public void onXPGain(PlayerExpChangeEvent e){
        e.getPlayer().sendMessage("XP Gain: " + e.getAmount() + "\nXP Gained: " + e.getAmount() * 1.5);
        e.setAmount((int) (e.getAmount() * 1.5));
    }

    /*
    @EventHandler
    public void preEnchantTable(PrepareItemEnchantEvent e){
        Player p = e.getEnchanter();
        if(!p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Sword of Intelligence")) return;
        EnchantmentOffer[] offer = e.getOffers();

        for (int i = 0; i > offer.length; i++){
            EnchantmentOffer o = offer[i];
            Enchantment a = o.getEnchantment();
            if(a.equals(Enchantment.DAMAGE_ALL) || a.equals(Enchantment.DAMAGE_UNDEAD) || a.equals(Enchantment.DAMAGE_ARTHROPODS)) {
                offer[i] = null;
            }
        }
    }

    @EventHandler
    public void onAnvilEnchant(InventoryClickEvent e){
        if(!(e.getInventory() instanceof AnvilInventory)) return;
        AnvilInventory i = (AnvilInventory) e.getInventory();

        if(!i.getItem(0).getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Sword of Intelligence")) {

            return;
        }
        if(i.getItem(1) == null) return;
        if(!i.getItem(1).getType().equals(Material.ENCHANTED_BOOK)) return;
        if(i.getItem(1).getEnchantments().isEmpty()) return;

        Map<Enchantment, Integer> map = i.getItem(1).getEnchantments();
        if(!map.containsKey(Enchantment.DAMAGE_ALL)) return;
        if(!map.containsKey(Enchantment.DAMAGE_UNDEAD)) return;
        if(!map.containsKey(Enchantment.DAMAGE_ARTHROPODS)) return;

        e.setCancelled(true);
    }
    */
}
