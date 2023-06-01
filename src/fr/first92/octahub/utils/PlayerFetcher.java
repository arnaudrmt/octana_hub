package fr.first92.octahub.utils;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;


public class PlayerFetcher {

    public String getUUID(String name) {

        String nameURL = "https://api.mojang.com/users/profiles/minecraft/";

        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(nameURL+name));
            if(UUIDJson.isEmpty()) return null;
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            String id = UUIDObject.get("id").toString();
            return id;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getTexture(String player) {

        String url = "https://sessionserver.mojang.com/session/minecraft/profile/"
                + getUUID(player) + "?unsigned=false";

        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));
            if(UUIDJson.isEmpty()) return null;
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);

            String s = UUIDObject.get("properties").toString().split("value")[1].split(",")[0].substring(3);
            String substring = UUIDObject.get("properties").toString().split("signature")[1].substring(3);

            return s.substring(0, s.length() - 3) + "," + substring.substring(0, substring.length() - 3);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
