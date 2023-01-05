package com.josemarcellio.jlocation.util;

import com.google.gson.stream.JsonReader;
import com.josemarcellio.jlocation.location.Location;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
                BufferedInputStream in = new BufferedInputStream(
                        connection.getInputStream());

                JsonReader reader = new JsonReader(
                        new InputStreamReader(in, StandardCharsets.UTF_8));
                return getLocationFromResponse(reader);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Location getLocationFromResponse(
            JsonReader reader) throws IOException {

        String city = "Unknown";
        String region = "Unknown";
        String country = "Unknown";
        String isp = "Unknown";
        String as = "Unknown";
        String asname = "Unknown";
        String lat = "Unknown";
        String lon = "Unknown";
        reader.beginObject();
        while (reader.hasNext()) {

            String name = reader.nextName();
            switch (name) {
                case "city":
                    city = reader.nextString();
                    break;
                case "regionName":
                    region = reader.nextString();
                    break;
                case "country":
                    country = reader.nextString();
                    break;
                case "isp":
                    isp = reader.nextString();
                    break;
                case "as":
                    as = reader.nextString();
                    break;
                case "asname":
                    asname = reader.nextString();
                    break;
                case "lat":
                    lat = reader.nextString();
                    break;
                case "lon":
                    lon = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Location(city, region, country,
                isp, as, asname, lat, lon);
    }
}
