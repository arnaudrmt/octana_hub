package fr.first92.octahub.scoreboard;

import fr.first92.octaapi.network.data.AccountAccess;
import fr.first92.octaapi.utils.ScoreboardSign;
import fr.first92.octahub.Hub;
import org.bukkit.entity.Player;

public class MainScoreboard {

    private Player p;

    public MainScoreboard(Player p) {
        this.p = p;
    }

    public void create() {

        ScoreboardSign sb = new ScoreboardSign(p, "§eOctana");

        sb.create();
        sb.setLine(0, "");

        sb.setLine(1, "§c\u2219 Rank: §7" +
                new AccountAccess(p).getAccount().getRank().getName().substring(0, 1).toUpperCase() +
                new AccountAccess(p).getAccount().getRank().getName().substring(1));

        sb.setLine(2, "§e\u2219 Coins: §7" + new AccountAccess(p).getAccount().getCoins());

        sb.setLine(3, " ");

        sb.setLine(4, "§b\u2219 Tokens: §70.7465");

        sb.setLine(5, "§a\u2219 Exp: §7[§b\u25A0\u25A0\u25A0\u25A0§f\u25A0\u25A0§7]");

        sb.setLine(6, "  ");

        sb.setLine(7, "    §eplay.octana.fr");

        Hub.getInstance().getBoards().put(p, sb);
    }

    public void update(int line, String sign) {

        Hub.getInstance().getBoards().entrySet().stream().filter(rs -> rs.getKey().equals(p)).forEach(rs -> {
                    rs.getValue().setLine(line, sign);
                });
    }
}
