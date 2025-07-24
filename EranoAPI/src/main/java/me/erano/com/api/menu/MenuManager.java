package me.erano.com.api.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

//GUIManager
//Oyun içindeki eventler burada menu sistemimize pass ediliyor. InventoryHandler method arayüzlerini incele
public class MenuManager {

    // Her bir inventory için InventoryHandler mapleniyor.
    private final Map<Inventory, InventoryHandler> activeInventories = new HashMap<>();

    //Her bir inventory için InventoryHandler maplemek için olan operasyonumuz budur ve oyuncu için menu açıyor.
    public void openMenu(Menu menu, Player player) {
        this.registerHandledInventory(menu.getInventory(), menu);
        player.openInventory(menu.getInventory());
    }

    public void registerHandledInventory(Inventory inventory, InventoryHandler handler) {
        this.activeInventories.put(inventory, handler);
    }

    public void unregisterInventory(Inventory inventory) {
        this.activeInventories.remove(inventory);
    }

    public void handleClick(InventoryClickEvent event) {
        InventoryHandler handler = this.activeInventories.get(event.getInventory());
        if (handler != null) {
            handler.onClick(event);
        }
    }

    public void handleOpen(InventoryOpenEvent event) {
        InventoryHandler handler = this.activeInventories.get(event.getInventory());
        if (handler != null) {
            handler.onOpen(event);
        }
    }
    //Menu kapatıldığında Inventory ve InventoryHandler key, value mapi cachedan çıkartılıyor.
    public void handleClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        InventoryHandler handler = this.activeInventories.get(inventory);
        if (handler != null) {
            handler.onClose(event);
            this.unregisterInventory(inventory);
        }
    }

}
