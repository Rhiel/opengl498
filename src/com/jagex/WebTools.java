package com.jagex;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

/**
 * Created by Chris on 3/19/2017.
 */
public class WebTools {

    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a webpage.
     * @param link the link.
     */
    public static void openWebpage(String link) {
        try {
            openWebpage(new URL(link));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static java.util.List<String> readLink(String link) {
        java.util.List<String> strings = new ArrayList<>();
        URL obj = null;
        try {
            obj = new URL(link);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        HttpURLConnection con;
        try {
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                return strings;
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

}
