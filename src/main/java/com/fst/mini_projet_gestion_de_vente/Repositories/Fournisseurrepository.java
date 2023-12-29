package com.fst.mini_projet_gestion_de_vente.Repositories;

import com.fst.mini_projet_gestion_de_vente.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Fournisseurrepository extends JpaRepository<Fournisseur,Long> {
}
