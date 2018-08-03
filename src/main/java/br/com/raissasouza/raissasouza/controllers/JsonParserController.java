package br.com.raissasouza.raissasouza.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 12147 on 8/3/2018.
 */
public class JsonParserController {

    public List<String> getJasonList() throws IOException, JSONException {
        StringBuilder sb = null;
        URL url = new URL("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json");
        URLConnection urlCon;
        try {
            urlCon = url.openConnection();
            BufferedReader in = null;
            if (urlCon.getHeaderField("Content-Encoding") != null
                    && urlCon.getHeaderField("Content-Encoding").equals("gzip")) {
               // LOGGER.info("reading data from URL as GZIP Stream");
                in = new BufferedReader(new InputStreamReader(new GZIPInputStream(urlCon.getInputStream())));
            } else {
               // LOGGER.info("reading data from URL as InputStream");
                in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            }
            String inputLine;
            sb = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
           // LOGGER.info("Exception while reading JSON from URL - {}", e);
        }
        List<String> codinomesList = new ArrayList<>();
        if (sb != null) {
            codinomesList = extractCodinomes(new JSONObject(sb.toString()));

        } else {
            //LOGGER.warn("No JSON Found in given URL");
        }
        return codinomesList;
    }

    public List<String> extractCodinomes(JSONObject vingadores) throws JSONException {
        List<String> extractedCodinomes = new ArrayList<>();
        try {

            JSONArray codinomes = vingadores.getJSONArray("vingadores");
            for(int i=0; i<codinomes.length();i++){
                extractedCodinomes.add((String) codinomes.getJSONObject(i).get("codinome"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return extractedCodinomes;
    }


}
