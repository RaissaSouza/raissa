package br.com.raissasouza.raissasouza;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 12147 on 8/1/2018.
 */
@Controller
public class indexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
