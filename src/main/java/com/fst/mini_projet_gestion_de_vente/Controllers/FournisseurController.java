package com.fst.mini_projet_gestion_de_vente.Controllers;

import com.fst.mini_projet_gestion_de_vente.Repositories.Fournisseurrepository;
import com.fst.mini_projet_gestion_de_vente.entities.Fournisseur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FournisseurController {
    @Autowired
    private Fournisseurrepository fournisseurrepository;

    @GetMapping("/fournisseur")
    @PreAuthorize("hasRole('USER')")
    public String list(Model model)
    {
        List<Fournisseur> fournisseurs=fournisseurrepository.findAll();
        model.addAttribute("fournisseur",fournisseurs);
        return "fournisseur";

    }

    @GetMapping("/supprimerfor")
    @PreAuthorize("hasRole('ADMIN')")
    public String supprimer(long id)
    {
        fournisseurrepository.deleteById(id);
        return "redirect:fournisseur";
    }
    @GetMapping("/ajoutfor")
    @PreAuthorize("hasRole('ADMIN')")
    public String ajout(Model model)
    {
        model.addAttribute("fournisseur",new Fournisseur());
        return "ajoutfor";

    }
    @PostMapping("/savefor")
    @PreAuthorize("hasRole('ADMIN')")
    public String savefournisseur(Model model , @Valid Fournisseur fournisseur, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "ajoutfor";
        fournisseurrepository.save(fournisseur);
        return "redirect:fournisseur";
    }
    @GetMapping(path = "/modiffor")
    @PreAuthorize("hasRole('ADMIN')")
    public String formpajout(Model model , long id) {
        Fournisseur fournisseur=fournisseurrepository.findById(id).orElse(null);
        if(fournisseur==null) throw new RuntimeException("Produit introvable");
        model.addAttribute("fournisseur",fournisseur);
        return "modiffor";
    }
}

