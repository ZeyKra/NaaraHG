package fr.zeykra.naaraHG.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import java.util.UUID;

public class YamlInventoryObject {
    int slot;
    ItemStack item;

    //optional
    byte itemByte;
    String name, lore, action, texture;
    boolean unbreakable = false;

    public YamlInventoryObject(int slot, String itemType, String name, int itemByte, String lore, String action) {
        this.slot = slot;
        this.name = name;
        this.item = new ItemStack(Material.matchMaterial(itemType), 1, (byte) itemByte);
        this.itemByte = (byte) itemByte;
        this.lore = lore;
        this.action = action;
    }

    public YamlInventoryObject(int slot, String itemType, String name, String lore, String action) {
        this.slot = slot;
        this.name = name;
        this.item = new ItemStack(Material.matchMaterial(itemType), 1);
        this.lore = lore;
        this.action = action;
    }

    public YamlInventoryObject(int slot, String itemType, String name, String lore, String action, String texture ) {
        this.slot = slot;
        this.name = name;
        this.texture = texture;
        this.item = new ItemStack(Material.matchMaterial(itemType), 1);
        this.lore = lore;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public byte getItemByte() {
        return itemByte;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getAction() {
        return action;
    }

    public String getLore() {
        return lore;
    }

    public String getTexture() {
        return texture;
    }

    public void setUnbreakable(boolean unbreakable) { this.unbreakable = unbreakable; }

    //set le byte ex color d'un item
    public void setItemByte(int itemByte) {
        this.item = new ItemStack(item.getType(), 1, (byte) itemByte);
    }
    public void setTexture(String texture) { this.texture = texture; }

    public ItemStack generateItem(Player p) {
        if(this.texture != null  && !this.texture.equalsIgnoreCase("player")) this.item = makeSkull(getTexture());
        if(texture != null) {
            if(texture.equalsIgnoreCase("player")) generatePlayerHead(p);
        };

        ItemStack item = this.getItem();
        ItemMeta meta = item.getItemMeta();


        if(!this.lore.isEmpty()) {
            String[] lore = Lang.format(getLore()).split("\\|\\|");
            meta.setLore(Arrays.asList(lore));
        }

        if(!name.isEmpty()) {
            meta.setDisplayName(Lang.format(getName()));
        }

        meta.spigot().setUnbreakable(unbreakable);

        item.setAmount(1);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack generateItem() {
        if(this.texture != null  && !this.texture.equalsIgnoreCase("player")) this.item = makeSkull(getTexture());

        ItemStack item = this.getItem();
        ItemMeta meta = item.getItemMeta();


        if(!this.lore.isEmpty()) {
            String[] lore = Lang.format(getLore()).split("\\|\\|");
            meta.setLore(Arrays.asList(lore));
        }

        if(!name.isEmpty()) {
            meta.setDisplayName(Lang.format(getName()));
        }

        meta.spigot().setUnbreakable(unbreakable);

        item.setAmount(1);
        item.setItemMeta(meta);

        return item;
    }

    /**
     * @deprecated
     *
     * */
    public ItemStack generateSkull() {
        this.item = makeSkull(getTexture());
        return generateItem();
    }

    public void generatePlayerHead(Player player) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner(player.getName());
        skull.setItemMeta(skullMeta);
        this.item = skull;
    }

    private ItemStack makeSkull(String base64EncodedString) {
        final ItemStack skull = new ItemStack(Material.SKULL);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        assert meta != null;
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64EncodedString));
        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(meta);
        return skull;
    }

}