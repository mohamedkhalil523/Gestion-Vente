package com.fst.mini_projet_gestion_de_vente.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @NotBlank
    private String nom ;
    @NotNull
    private String adresse ;
    @NotNull
    @Size(min=7,max=30)
    private String email ;
    @OneToMany(mappedBy = "fournisseur")
    private List<Produit> produits;
}
