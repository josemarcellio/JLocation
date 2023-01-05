package com.josemarcellio.jlocation.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.josemarcellio.jlocation.location.Location;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LocationUtils {

    public static Location getLocation(
            String ip) {

        try {
            String urlString = "http://ip-api.com/json/" + ip;
            URL url = new URL(urlString);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));

                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return getLocationFromResponse(
                        response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Location getLocationFromResponse(
            String response) {

        JsonElement element = new JsonParser()
                .parse(response);

        JsonObject object = element.getAsJsonObject();

        String city = object.get("city") == null
                ? "Unknown" : object.get("city").getAsString();

        String region = object.get("regionName") == null
                ? "Unknown" : object.get("regionName").getAsString();

        String country = object.get("country") == null
                ? "Unknown" : object.get("country").getAsString();

        String isp = object.get("isp") == null
                ? "Unknown" : object.get("isp").getAsString();

        String as = object.get("as") == null
                ? "Unknown" : object.get("as").getAsString();

        String asname = object.get("asname") == null
                ? "Unknown" : object.get("asname").getAsString();

        String lat = object.get("lat") == null
                ? "Unknown" : object.get("lat").getAsString();

        String lon = object.get("lon") == null
                ? "Unknown" : object.get("lon").getAsString();

        return new Location(city, region, country,
                isp, as, asname, lat, lon);
    }
}