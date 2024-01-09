package com.fst.mini_projet_gestion_de_vente.Repositories;

import com.fst.mini_projet_gestion_de_vente.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Produitrepository extends JpaRepository<Produit,Long> {
    List<Produit> findByCategorieId(Long categorieId);
    List<Produit> findByFournisseurId(Long fournisseurId);

}
