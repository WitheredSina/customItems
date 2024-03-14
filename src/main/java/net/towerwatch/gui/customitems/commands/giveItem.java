package net.towerwatch.gui.customitems.commands;

import net.towerwatch.gui.customitems.CustomItems;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class giveItem implements CommandExecutor {
    Player p;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) return false;
        String arg = args[0].toLowerCase();
        p = (Player) sender;
        switch(arg){
            case "vampire", "vampiresword" -> {
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.RED + "Life Steal: ");
                lore.add(ChatColor.GRAY + "Heal for" + ChatColor.AQUA + " 30% " + ChatColor.GRAY + "of damage dealt.");
                lore.add("");
                lore.add(ChatColor.RED + "Blood Rush: ");
                lore.add(ChatColor.GRAY + "Deal " + ChatColor.AQUA +
                        "30% " + ChatColor.GRAY + "more damage when you are at " + ChatColor.GREEN + "Full Health" + ChatColor.GRAY + ".");

                ItemStack itemStack = createItem(lore, Material.NETHERITE_SWORD, ChatColor.RED + "Vampire Sword");
                ItemMeta meta = itemStack.getItemMeta();
                meta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);
                itemStack.setItemMeta(meta);
                p.getInventory().addItem(itemStack);
            }

            case "greedy", "greedysword" -> {
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.GOLD + "Greed: ");
                lore.add(ChatColor.GRAY + "Chance to get " + ChatColor.GOLD + "Loot " + ChatColor.GRAY + "when killing Mobs.");
                ItemStack itemStack = createItem(lore, Material.GOLDEN_SWORD, ChatColor.GOLD + "Greedy Sword");
                ItemMeta meta = itemStack.getItemMeta();

                AttributeModifier damageModifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 8.5,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                AttributeModifier attackSpeedModifier = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -1.8,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

                meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 1, false);
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageModifier);
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedModifier);

                meta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);


                itemStack.setItemMeta(meta);
                p.getInventory().addItem(itemStack);
            }

            case "hasty", "hastypickaxe", "hastypick" -> {
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.YELLOW + "Hasty: ");
                lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right Click" + ChatColor.RESET + " " + ChatColor.GRAY +
                        "to get " + ChatColor.AQUA +
                        "Haste 2" + ChatColor.GRAY + " for " + ChatColor.AQUA + "10 seconds.");
                lore.add("");
                lore.add(ChatColor.GRAY + "Cooldown: " + ChatColor.AQUA + "15 Seconds");
                ItemStack itemStack = createItem(lore, Material.NETHERITE_PICKAXE, ChatColor.YELLOW + "Hasty Pickaxe");
                ItemMeta meta = itemStack.getItemMeta();
                meta.addEnchant(Enchantment.DIG_SPEED, 1, false);
                meta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);
                itemStack.setItemMeta(meta);

                p.getInventory().addItem(itemStack);
            }

            case "healthy", "healthyarmor" -> {

                //HEALTHY HELMET
                ItemStack helm = createItem(new ArrayList<String>(Arrays.asList(ChatColor.GRAY + "Health: " + ChatColor.AQUA + "+2.5")),
                        Material.LEATHER_HELMET, ChatColor.GREEN + "Healthy Helmet");
                LeatherArmorMeta helmMeta = (LeatherArmorMeta) helm.getItemMeta();
                helmMeta.setColor(Color.fromRGB(34, 139, 34));
                helmMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
                        "helm.maxHealth", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
                helmMeta.addItemFlags(ItemFlag.HIDE_DYE);
                helmMeta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);
                helm.setItemMeta(helmMeta);


                //HEALTHY CHESTPLATE
                ItemStack chest = createItem(new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Health: " + ChatColor.AQUA + "+10")),
                        Material.LEATHER_CHESTPLATE, ChatColor.GREEN + "Healthy Chestplate");
                LeatherArmorMeta chestMeta = (LeatherArmorMeta) chest.getItemMeta();
                chestMeta.setColor(Color.fromRGB(34, 139, 34));
                chestMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
                        "chest.maxHealth", 20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
                chestMeta.addItemFlags(ItemFlag.HIDE_DYE);
                chestMeta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);
                chest.setItemMeta(chestMeta);


                //HEALTHY LEGGINGS
                ItemStack leg = createItem(new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Health: " + ChatColor.AQUA + "+5")),
                        Material.LEATHER_LEGGINGS, ChatColor.GREEN + "Healthy Leggings");
                LeatherArmorMeta legMeta = (LeatherArmorMeta) leg.getItemMeta();
                legMeta.setColor(Color.fromRGB(34, 139, 34));
                legMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
                        "leg.maxHealth", 10, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
                legMeta.addItemFlags(ItemFlag.HIDE_DYE);
                legMeta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);
                leg.setItemMeta(legMeta);


                //HEALTHY BOOTS
                ItemStack boots = createItem(new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Health: " + ChatColor.AQUA + "+2.5")),
                        Material.LEATHER_BOOTS, ChatColor.GREEN + "Healthy Boots");
                LeatherArmorMeta bootMeta = (LeatherArmorMeta) helm.getItemMeta();
                bootMeta.setColor(Color.fromRGB(34, 139, 34));
                bootMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
                        "boots.maxHealth", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
                bootMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                bootMeta.addItemFlags(ItemFlag.HIDE_DYE);
                bootMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                bootMeta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);
                boots.setItemMeta(bootMeta);

                ItemStack[] items = {helm, chest, leg, boots};

                for(ItemStack item : items) {
                    p.getInventory().addItem(item);
                }
            }

            case "crimson", "crimsonsword", "crimsondagger" -> {
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "Range:" + ChatColor.AQUA + " 2 Blocks");
                lore.add(ChatColor.GRAY + "Damage:" + ChatColor.AQUA + " 5.5");
                lore.add(ChatColor.GRAY + "Attack Speed:" + ChatColor.AQUA + " 2");


                ItemStack itemStack = createItem(lore, Material.NETHERITE_SWORD, ChatColor.RED + "Crimson Dagger");
                ItemMeta meta = itemStack.getItemMeta();

                //Attack Damage
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
                        "sword.damage", 5.51, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

                //Attack Speed
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(),
                        "sword.speed", 1.75, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));


                meta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);

                itemStack.setItemMeta(meta);

                p.getInventory().addItem(itemStack);
            }

            case "speed", "speedsword", "rogue", "roguesword" -> {

                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.RED + "Assassin's Step: ");
                lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right Click" + ChatColor.RESET + " " + ChatColor.GRAY +
                        "to get " + ChatColor.GREEN +
                        "Speed" + ChatColor.GRAY + " for " + ChatColor.AQUA + "10 seconds.");
                lore.add(ChatColor.GRAY + "Cooldown: " + ChatColor.AQUA + "20 Seconds");

                ItemStack itemStack = createItem(lore, Material.IRON_SWORD, ChatColor.RED + "Rogue Sword");
                ItemMeta meta = itemStack.getItemMeta();
                meta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);
                itemStack.setItemMeta(meta);
                p.getInventory().addItem(itemStack);
            }

            case "intelligencesword", "intelligence", "xp", "xpsword" -> {

                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.AQUA + "Big Brain: ");
                lore.add(ChatColor.GRAY + "Deal increased damage based on your" + ChatColor.GREEN +  " EXP");
                lore.add(ChatColor.GRAY + "And increases" + ChatColor.GREEN + " EXP Gains" + ChatColor.GRAY + "by " + ChatColor.AQUA + "50%");

                ItemStack itemStack = createItem(lore, Material.DIAMOND_SWORD, ChatColor.AQUA + "Intelligence Sword");
                ItemMeta meta = itemStack.getItemMeta();

                meta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);
                itemStack.setItemMeta(meta);
                p.getInventory().addItem(itemStack);

            }

            case "ragnarok", "ragnarokaxe", "ragnaroksword", "ragnarokwarhammer" -> {

                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.RED + "Ragnarok: ");
                lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right Click" + ChatColor.RESET + " " + ChatColor.GRAY +
                        "to get " + ChatColor.GREEN +
                        "Strength 2" + ChatColor.GRAY + " for " + ChatColor.AQUA + "8 seconds.");
                lore.add(ChatColor.GRAY + "Cooldown: " + ChatColor.AQUA + "25 Seconds");

                ItemStack itemStack = createItem(lore, Material.NETHERITE_AXE, ChatColor.RED + "Ragnarok Warhammer");
                ItemMeta meta = itemStack.getItemMeta();

                meta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);

                itemStack.setItemMeta(meta);
                p.getInventory().addItem(itemStack);
            }

            case "ranger", "rangers", "rangerbow", "rangersbow", "rangerlongbow", "rangerslongbow" -> {
                ArrayList<String> list = l();
                list.add(ChatColor.DARK_GREEN + "Ranger: ");
                list.add(ChatColor.GRAY + "Deal more damage based on the" + ChatColor.GREEN + " Distance " + "between you and the target.");

                ItemStack itemStack = createItem(list, Material.BOW, ChatColor.DARK_GREEN + "Ranger's Longbow");
                ItemMeta meta = itemStack.getItemMeta();

                meta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);

                itemStack.setItemMeta(meta);
                add(itemStack);
            }
            case "giantshammer", "giant", "giants", "gianthammer" -> {

                ArrayList<String> list = l();
                list.add(ChatColor.DARK_PURPLE + "Giant: ");
                list.add(ChatColor.AQUA + "25%" + ChatColor.GRAY + " Chance to" + ChatColor.GREEN + " Stagger " +
                        ChatColor.GRAY + "an enemy for " + ChatColor.AQUA + "1.5 seconds" + ChatColor.GRAY + ".");

                ItemStack itemStack = createItem(list, Material.STONE_AXE, ChatColor.DARK_PURPLE + "Giant's Hammer");
                ItemMeta meta = itemStack.getItemMeta();
                AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(),
                        "generic.damage", 7, AttributeModifier.Operation.ADD_NUMBER);
                AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(),
                        "generic.attack.speed", -3, AttributeModifier.Operation.ADD_NUMBER);

                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

                meta.getPersistentDataContainer().set(new NamespacedKey(CustomItems.getPlugin(CustomItems.class),
                        "sinaCustomItem"), PersistentDataType.BOOLEAN, true);

                itemStack.setItemMeta(meta);

                add(itemStack);
            }

        }
        return true;
    }

    ArrayList<String> l(){
        return new ArrayList<>();
    }

    private void add(ItemStack i){
        p.getInventory().addItem(i);
    }

    private ItemStack createItem(ArrayList<String> lore, Material material, String name) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();


        itemMeta.setDisplayName(name);
        itemMeta.setUnbreakable(true);

        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
