package br.com.raissasouza.raissasouza.controllers;

import br.com.raissasouza.raissasouza.model.Jogador;
import br.com.raissasouza.raissasouza.repository.JogadorRepository;
import java.io.IOException;
import java.util.List;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 12147 on 8/2/2018.
 */

@Controller
public class JogadorController {

    @Autowired
    private JogadorRepository jr;

    @RequestMapping(value ="/cadastrar", method=RequestMethod.GET)
    public String cadastroJogador(){
        return "jogador/cadastroJogador";
    }

    @RequestMapping(value ="/cadastrar", method=RequestMethod.POST)
    public String cadastroJogador(Jogador jogador) throws IOException, JSONException {
        JsonParserController js = new JsonParserController();
        List<String> vingadoresList =js.getJasonList();
        System.out.println(vingadoresList);

        jr.save(jogador);
        return "redirect:/cadastrar";
    }




}
