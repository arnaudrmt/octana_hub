package fr.first92.octahub.event.events;

import fr.first92.commons.Account;
import fr.first92.octaapi.network.data.AccountAccess;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void AsyncPlayerChat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();
        String message = e.getMessage();

        Account account = new AccountAccess(p).getAccount();

        e.setFormat(account.getRank().getColor() + account.getRank().getPrefix() +
                p.getPlayer().getName() + "§r: " + ((account.getRank().getPower() > 60) ? "§7" : "§f")
                + e.getMessage());
    }
}
