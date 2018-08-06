package br.com.raissasouza.raissasouza.controllers;

import br.com.raissasouza.raissasouza.model.Jogador;
import br.com.raissasouza.raissasouza.repository.JogadorRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12147 on 8/2/2018.
 */

@Controller
public class JogadorController {

    @Autowired
    private JogadorRepository jr;
    @Autowired
    private GroupRequest gr;



    @RequestMapping(value="/cadastrar", method= RequestMethod.GET)
    public String cadastroJogador(){
        return "jogador/cadastroJogador";
    }

    @RequestMapping(value="/cadastrar", method= RequestMethod.POST)
    public String cadastroJogador(Jogador jogador, HttpServletRequest request) throws JSONException, IOException, SAXException, ParserConfigurationException {

        final String selectedGroup = jogador.getGrupo();
        List storedJogadores = getStoredCodinomes(selectedGroup);
        final List codinomes;
        if(selectedGroup.equals("vingadores")){
            codinomes = gr.getVingadores(gr.getGroupInfo("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json"));
        }else{
            codinomes = gr.getLigaDaJustica(gr.getGroupInfo("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml"));
        }


        if(!getAvailableCodinome(codinomes, storedJogadores).isEmpty()){
            jogador.setCodinome(getAvailableCodinome(codinomes, storedJogadores));
            jr.save(jogador);
            System.out.println(jogador.getCodinome()+"Salvo com sucesso");
            request.setAttribute("mensagem", "Jogador salvo com sucesso!");
        }else{
            System.out.println("Nao ha mais codinome disponivel para esse grupo");
            request.setAttribute("mensagem", "Não há codinome disponível para esse grupo!");
        }

        return "redirect:/cadastrar";
    }

    public String getAvailableCodinome(List<String> codinomes, List<String> jogadoresCod){
        if(jogadoresCod.size()==0){
           return (String) codinomes.get(0);
        }else{
            for( String cod: codinomes){
                if(!jogadoresCod.contains(cod)){
                    return cod;
                }
            }
        }
        return "";
    }

    public List getStoredCodinomes(String grupo){
        List<Jogador> jogadores = jr.findByGrupo(grupo);
        List<String> jogCodinomes= new ArrayList<>();
        for(int i=0; i<jogadores.size();i++){
            jogCodinomes.add(jogadores.get(i).getCodinome());
        }
        return jogCodinomes;
    }

}
