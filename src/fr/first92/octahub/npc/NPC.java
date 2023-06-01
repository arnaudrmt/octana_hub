package fr.first92.octahub.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.first92.octaapi.utils.Reflections;
import fr.first92.octahub.utils.PlayerFetcher;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class NPC extends Reflections {

    String name;
    Player p;
    int entityID;
    Location location;
    GameProfile gameProfile;

    public NPC(Player p, String name, Location location) {
        this.name = name;
        this.p = p;
        entityID = (int)Math.ceil(Math.random() * 1000) + 2000;
        gameProfile = new GameProfile(UUID.randomUUID(), name);
        this.location = location;
    }

    public void spawn() {

        PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn();

        setValue(packet, "a", entityID);
        setValue(packet, "b", gameProfile.getId());
        setValue(packet, "c", getFixLocation(location.getX()));
        setValue(packet, "d", getFixLocation(location.getY()));
        setValue(packet, "e", getFixLocation(location.getZ()));

        setValue(packet, "f", getFixRotation(location.getYaw()));
        setValue(packet, "g", getFixRotation(location.getPitch()));

        setValue(packet, "h", 0);

        DataWatcher w = new DataWatcher(null);
        w.a(6, (float)20);
        w.a(10, (byte)127);

        setValue(packet, "i", w);

        addToTab();

        sendPacket(packet, p);
    }

    public void setPlayer(Player p) {
        this.p = p;
    }

    public void destroy() {

        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] {entityID});

        removeFromTab();

        sendPacket(packet, p);
    }

    public void setSkin(String player) {

        String texture = new PlayerFetcher().getTexture(player);

        gameProfile.getProperties().put("textures", new Property("textures", texture.split(",")[0],
                texture.split(",")[1]));
    }

    public void addToTab() {

        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameProfile, 1,
                WorldSettings.EnumGamemode.NOT_SET, CraftChatMessage.fromString(gameProfile.getName())[0]);

        List<PacketPlayOutPlayerInfo.PlayerInfoData> players =
                (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");

        players.add(data);

        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
        setValue(packet, "b", players);

        sendPacket(packet);
    }

    public void removeFromTab() {

        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameProfile, 1,
                WorldSettings.EnumGamemode.NOT_SET, CraftChatMessage.fromString(gameProfile.getName())[0]);

        List<PacketPlayOutPlayerInfo.PlayerInfoData> players =
                (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");

        players.remove(data);

        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
        setValue(packet, "b", players);

        sendPacket(packet);
    }

    public void sleep(boolean state) {

        if(state) {

            Location bedLocation = new Location(location.getWorld(), 1, 1, 1);

            PacketPlayOutBed packet = new PacketPlayOutBed();

            setValue(packet, "a", entityID);
            setValue(packet, "b", new BlockPosition(bedLocation.getX(), bedLocation.getY(), bedLocation.getZ()));

            p.sendBlockChange(bedLocation, Material.BED_BLOCK, (byte)0);

            sendPacket(packet, p);
            teleport(location.clone().add(0, .3, 0));

        } else {

            animation(2);
            teleport(location.clone().subtract(0, .3, 0));
        }
    }

    public void equip(int slot, ItemStack item) {

        PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment();

        setValue(packet, "a", entityID);
        setValue(packet, "b", slot);
        setValue(packet, "c", item);

        sendPacket(packet, p);
    }

    public void animation(int animation) {

        PacketPlayOutAnimation packet = new PacketPlayOutAnimation();

        setValue(packet, "a", entityID);
        setValue(packet, "b", (byte)animation);

        sendPacket(packet, p);
    }

    public void status(int status) {

        PacketPlayOutEntityStatus packet = new PacketPlayOutEntityStatus();

        setValue(packet, "a", entityID);
        setValue(packet, "b", (byte)status);

        sendPacket(packet, p);
    }

    public void teleport(Location location) {

        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport();

        setValue(packet, "a", gameProfile.getId());
        setValue(packet, "b", getFixLocation(location.getX()));
        setValue(packet, "b", getFixLocation(location.getY()));
        setValue(packet, "b", getFixLocation(location.getZ()));
        setValue(packet, "b", getFixRotation(location.getYaw()));
        setValue(packet, "b", getFixRotation(location.getPitch()));

        sendPacket(packet);
        headRotation(location.getYaw(), location.getPitch());

        this.location = location;
    }

    public void headRotation(float yaw, float pitch) {

        PacketPlayOutEntity.PacketPlayOutEntityLook packet = new
                PacketPlayOutEntity.PacketPlayOutEntityLook(entityID,
                getConvertedRotation(yaw), getConvertedRotation(pitch), true);

        PacketPlayOutEntityHeadRotation packet2 = new PacketPlayOutEntityHeadRotation();

        setValue(packet2, "a", entityID);
        setValue(packet2, "b", getConvertedRotation(yaw));

        sendPacket(packet, p);
        sendPacket(packet2, p);
    }

    public String getName() {
        return name;
    }

    public int getEntityID() {
        return entityID;
    }

    public int getFixLocation(double pos) {
        return MathHelper.floor(pos * 32.0D);
    }

    public byte getFixRotation(float rot) {

        return (byte) ((int) (rot * 256.0F / 360.0F));
    }

    public byte getConvertedRotation(float a) {
        return (byte) (int) (a * (256.0D / 360.0D));
    }
}
