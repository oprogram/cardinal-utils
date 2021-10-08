package org.librenote.mc.cardinal.utils.mechanics;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.librenote.mc.cardinal.utils.Main;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Objects;

public class CountryData {
    private final Gson gson = new Gson();

    private JsonArray countries = null;

    public CountryData() {
        try {
            String fileName = Main.getInstance().getDataFolder().getAbsolutePath() + "/" + Main.getInstance().getConfig().getString("country-list");
            JsonReader reader = new JsonReader(new FileReader(fileName));
            countries = gson.fromJson(reader, JsonArray.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getCountryCode(Player player) {
        JsonObject content = new JsonObject();
        String ip = Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress();

        try {
            URL url = new URL("https://ipinfo.io/" + ip);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inLine;
            StringBuilder builder = new StringBuilder();
            while ((inLine = reader.readLine()) != null) {
                builder.append(inLine);
            }
            content = gson.fromJson(builder.toString(), JsonObject.class);

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.get("country").getAsString();
    }

    public JsonObject getEntry(String countryCode) {
        JsonObject entry = new JsonObject();
        for (JsonElement element : countries) {
            JsonObject object = element.getAsJsonObject();
            if (object.has("code") && object.get("code").getAsString().equals(countryCode)) {
                return object;
            }
        }
        return getEntry("CH");
    }

    public String getName(String countryCode) {
        JsonObject entry = getEntry(countryCode);
        if (entry.has("name")) {
            return entry.get("name").getAsString();
        }
        return "";
    }

    public Location getCoords(String countryCode, World world) {

        JsonObject entry = getEntry(countryCode);
        JsonArray coords = entry.get("coords").getAsJsonArray();

        return new Location(world,
                coords.get(0).getAsInt(),
                coords.get(1).getAsInt(),
                coords.get(2).getAsInt());
    }
}
