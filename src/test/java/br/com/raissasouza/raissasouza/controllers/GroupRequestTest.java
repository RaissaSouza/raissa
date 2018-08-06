package br.com.raissasouza.raissasouza.controllers;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

/**
 * Created by 12147 on 8/6/2018.
 */
public class GroupRequestTest {

    @Test
    public void getVingadores() throws Exception {
        GroupRequest gr = new GroupRequest();
        String pageInfo = "{\n"
                + "\t\"vingadores\": [{\n"
                + "\t\t\t\"codinome\": \"Hulk\"\n"
                + "\t\t},\n"
                + "\t\t{\n"
                + "\t\t\t\"codinome\": \"Capitão América\"\n"
                + "\t\t},\n"
                + "\t\t{\n"
                + "\t\t\t\"codinome\": \"Pantera Negra\"\n"
                + "\t\t},\n"
                + "\t\t{\n"
                + "\t\t\t\"codinome\": \"Homem de Ferro\"\n"
                + "\t\t},\n"
                + "\t\t{\n"
                + "\t\t\t\"codinome\": \"Thor\"\n"
                + "\t\t},\n"
                + "\t\t{\n"
                + "\t\t\t\"codinome\": \"Feiticeira Escarlate\"\n"
                + "\t\t},\n"
                + "\t\t{\n"
                + "\t\t\t\"codinome\": \"Visão\"\n"
                + "\t\t}\n"
                + "\t]\n"
                + "}";
        List<String> codinomes = gr.getVingadores(pageInfo);

        assertEquals(codinomes.get(0),"Hulk");
        assertEquals(codinomes.get(1),"Capitão América");
        assertEquals(codinomes.get(2),"Pantera Negra");
        assertEquals(codinomes.get(3),"Homem de Ferro");
        assertEquals(codinomes.get(4),"Thor");
        assertEquals(codinomes.get(5),"Feiticeira Escarlate");
        assertEquals(codinomes.get(6),"Visão");


    }

    @Test
    public void getLigaDaJustica() throws Exception {
        GroupRequest gr = new GroupRequest();
        String pageInfo = "<liga_da_justica><codinomes>"
			+"<codinome>Lanterna Verde</codinome>"
			+"<codinome>Flash</codinome>"
			+"<codinome>Aquaman</codinome>"
			+"<codinome>Batman</codinome>"
			+"<codinome>Superman</codinome>"
			+"<codinome>Mulher Maravilha</codinome></codinomes></liga_da_justica>";
        List<String> codinomes = gr.getLigaDaJustica(pageInfo);

        assertEquals(codinomes.get(0),"Lanterna Verde");
        assertEquals(codinomes.get(1),"Flash");
        assertEquals(codinomes.get(2),"Aquaman");
        assertEquals(codinomes.get(3),"Batman");
        assertEquals(codinomes.get(4),"Superman");
        assertEquals(codinomes.get(5),"Mulher Maravilha");

    }

}