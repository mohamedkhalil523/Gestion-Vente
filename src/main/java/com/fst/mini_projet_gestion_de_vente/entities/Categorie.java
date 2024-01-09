package com.fst.mini_projet_gestion_de_vente.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @NotNull
    @Size(min=5,max=15)
    private String nom;
    @NotNull
    @Size(min=5,max=30)
    private String description ;
    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;
}
