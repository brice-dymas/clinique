/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.dao;

import com.clinique.persistence.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
public interface IProduitDao extends JpaRepository<Produit, Long>, JpaSpecificationExecutor<Produit>
{

    @Query("SELECT P FROM Produit P WHERE P.nom LIKE :nom")
    Page<Produit> filterByNom(@Param("nom") String nom, Pageable pageable);
}
