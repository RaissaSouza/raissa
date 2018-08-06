package br.com.raissasouza.raissasouza.controllers;

import br.com.raissasouza.raissasouza.model.Jogador;
import br.com.raissasouza.raissasouza.repository.JogadorRepository;
import javax.validation.Valid;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    final String VINGADORES="vingadores";
    final String URL_VINGADORES="https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
    final String URL_LIGA="https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";

    @RequestMapping(value="/cadastrar", method= RequestMethod.GET)
    public String cadastroJogador(){
        return "jogador/cadastroJogador";
    }

    @RequestMapping(value="/cadastrar", method= RequestMethod.POST)
    public String cadastroJogador(@Valid Jogador jogador, BindingResult result, RedirectAttributes attributes) throws JSONException, IOException, SAXException, ParserConfigurationException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("message","Verifique os campos!");
            return "redirect:/cadastrar";
        }
        List storedJogadores = getStoredCodinomes(jogador.getGrupo());
        final List codinomes;
        if(jogador.getGrupo().equals(VINGADORES)){
            codinomes = gr.getVingadores(gr.getGroupInfo(URL_VINGADORES));
        }else{
            codinomes = gr.getLigaDaJustica(gr.getGroupInfo(URL_LIGA));
        }

        if(!getAvailableCodinome(codinomes, storedJogadores).isEmpty()){
            jogador.setCodinome(getAvailableCodinome(codinomes, storedJogadores));
            jr.save(jogador);
            attributes.addFlashAttribute("message","Salvo com sucesso!");
        }else{
            attributes.addFlashAttribute("message","Não há codinomes dísponiveis para esse grupo!");
        }

        return "redirect:/cadastrar";
    }


    @RequestMapping(value="jogador/listaJogadores", method = RequestMethod.GET)
    public ModelAndView listaJogadores(HttpServletRequest request){
        List<Jogador> jogadores = (List<Jogador>) jr.findAll();
        ModelAndView mv = new ModelAndView("jogador/listaJogadores");
        mv.addObject("jogadores",jogadores);
        return mv;
    }


    private String getAvailableCodinome(List<String> codinomes, List<String> jogadoresCod){
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

    private List getStoredCodinomes(String grupo){
        List<Jogador> jogadores = jr.findByGrupo(grupo);
        List<String> jogCodinomes= new ArrayList<>();
        for(int i=0; i<jogadores.size();i++){
            jogCodinomes.add(jogadores.get(i).getCodinome());
        }
        return jogCodinomes;
    }

}
