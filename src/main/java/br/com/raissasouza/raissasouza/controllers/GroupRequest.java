package br.com.raissasouza.raissasouza.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupRequest {

    final Integer STATUS_OK=200;

    public String getGroupInfo(String requestUrl){
        HttpURLConnection connection = null;
        String inputLine;
        StringBuffer response = new StringBuffer();

        try {
            URL url = new URL(requestUrl);
            // connection
            connection = (HttpURLConnection) url.openConnection();
            // HTTP request type GET
            connection.setRequestMethod("GET");
            if (connection.getResponseCode()==STATUS_OK) { //success
                // get response stream
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                // add response into the StringBuilder
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    return response.toString();
    }

    public List getVingadores(String pageInfo) throws JSONException {
        //create json Obj
        JSONObject obj = new JSONObject(pageInfo);
        // get Array type
        JSONArray vingadores = obj.getJSONArray("vingadores");
        //extract codinomes to a list
        List<String> codinomes = new ArrayList<>();
        for(int i=0; i<vingadores.length();i++){
            codinomes.add(vingadores.getJSONObject(i).getString("codinome"));
        }
        return codinomes;
    }

    public List getLigaDaJustica(String pageInfo) throws ParserConfigurationException, IOException, SAXException {
        // create builder doc
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder b = f.newDocumentBuilder();
        // parse the input in UTF-8
        Document doc = b.parse(new ByteArrayInputStream(pageInfo.getBytes("UTF-8")));
        // get the node called codinomes
        NodeList codinomes = doc.getElementsByTagName("codinomes");
        // get the first element
        Element codinome = (Element) codinomes.item(0);
        //extract codinomes to a list
        List<String> ligaDaJustica = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
             Node cod1 = codinome.getElementsByTagName("codinome").item(i);
             ligaDaJustica.add(cod1.getTextContent());
        }
        return ligaDaJustica;
    }

    }

