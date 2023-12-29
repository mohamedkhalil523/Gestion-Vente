package com.fst.mini_projet_gestion_de_vente.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/notAutorized")
    public String nonautorise(){
        return "/403";
    }
}
