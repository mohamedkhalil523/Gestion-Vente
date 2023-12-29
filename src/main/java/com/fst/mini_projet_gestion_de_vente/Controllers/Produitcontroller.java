package com.fst.mini_projet_gestion_de_vente.Controllers;

import com.fst.mini_projet_gestion_de_vente.Repositories.Produitrepository;
import com.fst.mini_projet_gestion_de_vente.entities.Produit;
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
public class Produitcontroller {
    @Autowired
    private Produitrepository produitrepository;

    @GetMapping("/produit")
    @PreAuthorize("hasRole('USER')")
    public String list(Model model)
    {
        List<Produit> produits=produitrepository.findAll();
        model.addAttribute("produit",produits);
        return "produit";

    }

    @GetMapping("/supprimer")
    @PreAuthorize("hasRole('ADMIN')")
    public String supprimer(long id)
    {
        produitrepository.deleteById(id);
        return "redirect:produit";
    }
    @GetMapping("/ajout")
    @PreAuthorize("hasRole('ADMIN')")
    public String ajout(Model model)
    {
        model.addAttribute("produit",new Produit());
        return "ajout";

    }
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduit(Model model , @Valid Produit produit, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "ajout";
        produitrepository.save(produit);
        return "redirect:produit";
    }
    @GetMapping(path = "/modif")
    @PreAuthorize("hasRole('ADMIN')")
    public String formpajout(Model model , long id) {
        Produit produit=produitrepository.findById(id).orElse(null);
        if(produit==null) throw new RuntimeException("Produit introvable");
        model.addAttribute("produit",produit);
        return "modif";
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public String home(Model model)
    {
        List<Produit> produits=produitrepository.findAll();
        model.addAttribute("produit",produits);
        return "/produit";

    }



}
