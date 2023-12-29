package com.fst.mini_projet_gestion_de_vente.Repositories;

import com.fst.mini_projet_gestion_de_vente.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Categorierepository extends JpaRepository<Categorie,Long> {
}
