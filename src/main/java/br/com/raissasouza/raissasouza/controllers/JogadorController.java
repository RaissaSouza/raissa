package br.com.raissasouza.raissasouza.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 12147 on 8/2/2018.
 */

@Controller
public class JogadorController {

    @RequestMapping("/cadastrar")
    public String cadastroJogador(){
        return "jogador/cadastroJogador";
    }

}
