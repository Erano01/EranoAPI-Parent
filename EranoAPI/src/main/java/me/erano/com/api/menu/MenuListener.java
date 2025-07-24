package me.erano.com.api.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;


//GUIListener
public class MenuListener implements Listener {

    private final MenuManager menuManager;

    public MenuListener(MenuManager menuManager) {
        this.menuManager = menuManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        this.menuManager.handleClick(event);
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        this.menuManager.handleOpen(event);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        this.menuManager.handleClose(event);
    }

}
