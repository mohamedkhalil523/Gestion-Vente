package com.fst.mini_projet_gestion_de_vente.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @NotNull
    @Size(min = 5,max = 30)
    private String nom;
    @NotBlank
    private String description ;
    @Min(100)
    private double prix ;
    @ManyToOne
    private Categorie categorie;
    @ManyToOne
    private Fournisseur fournisseur;
}
