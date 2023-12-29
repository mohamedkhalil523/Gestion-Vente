package com.fst.mini_projet_gestion_de_vente.Repositories;

import com.fst.mini_projet_gestion_de_vente.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Produitrepository extends JpaRepository<Produit,Long> {
}
