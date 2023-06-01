package fr.first92.octahub.utils;

import fr.first92.octaapi.network.data.redis.messages.RedisMessageSender;
import fr.first92.octahub.Hub;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class PacketReader {

    Player player;
    Channel channel;

    public PacketReader(Player player) {
        this.player = player;
    }

    public void inject() {

        CraftPlayer cPlayer = (CraftPlayer) this.player;
        channel = cPlayer.getHandle().playerConnection.networkManager.channel;
        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<Packet<?>>() {

            @Override
            protected void decode(ChannelHandlerContext arg0, Packet<?> packet, List<Object> arg2) throws Exception {

                arg2.add(packet);
                readPacket(packet);
            }
        });
    }

    public void unInject() {

        if(channel.pipeline().get("PacketInjector") != null) {
            channel.pipeline().remove("PacketInjector");
        }
    }

    public void readPacket(Packet<?> packet) {

        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {

            int id = (Integer)getValue(packet, "a");

            Hub.getInstance().getNpcs().forEach(rs -> System.out.println(rs.getEntityID()));

            Hub.getInstance().getNpcs().stream().filter(rs -> rs.getEntityID() == id)
                    .forEach(rs -> {
                rs.setPlayer(player);
                rs.animation(0);
                new RedisMessageSender().send(Arrays.asList("docker", "server",
                        ChatColor.stripColor(rs.getName()).toLowerCase(), player.getName()));
            });
        }
    }

    public void setValue(Object obj, String name, Object value) {

        try {
            Field field = null;
            field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getValue(Object obj, String name) {

        try {
            Field field = null;
            field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
