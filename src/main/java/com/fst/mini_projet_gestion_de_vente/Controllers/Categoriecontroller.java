package com.fst.mini_projet_gestion_de_vente.Controllers;

import com.fst.mini_projet_gestion_de_vente.Repositories.Categorierepository;
import com.fst.mini_projet_gestion_de_vente.Repositories.Produitrepository;
import com.fst.mini_projet_gestion_de_vente.entities.Categorie;
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
public class Categoriecontroller {
    @Autowired
    private Categorierepository categorierepository;
    @Autowired
    private Produitrepository produitrepository;
    @GetMapping("/categorie")
    @PreAuthorize("hasRole('USER')")
    public String list(Model model)
    {
        List<Categorie> categories=categorierepository.findAll();
        model.addAttribute("categorie",categories);
        return "categorie";

    }

    @GetMapping("/supprimercat")
    @PreAuthorize("hasRole('ADMIN')")
    public String supprimerCategorie(long id, Model model) {
        // Vérifier s'il existe des produits liés à la catégorie
        List<Produit> produits = produitrepository.findByCategorieId(id);

        if (!produits.isEmpty()) {
            // Il y a des produits liés à cette catégorie
            model.addAttribute("categorieId", id);
            model.addAttribute("produits", produits);
            return "erreurSuppressionCat";
        }

        // Aucun produit lié, supprimer la catégorie
        categorierepository.deleteById(id);
        return "redirect:categorie";
    }
    @GetMapping("/ajoutcat")
    @PreAuthorize("hasRole('ADMIN')")
    public String ajout(Model model)
    {
        model.addAttribute("categorie",new Categorie());
        return "ajoutcat";

    }
    @PostMapping("/savecat")
    @PreAuthorize("hasRole('ADMIN')")
    public String savecategorie(Model model , @Valid Categorie categorie, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "ajoutcat";
      categorierepository.save(categorie);
        return "redirect:categorie";
    }
    @GetMapping(path = "/modifcat")
    @PreAuthorize("hasRole('ADMIN')")
    public String formpajout(Model model , long id) {
        Categorie categorie=categorierepository.findById(id).orElse(null);
        if(categorie==null) throw new RuntimeException("Categorie introvable");
        model.addAttribute("categorie",categorie);
        return "modifcat";
    }


}
