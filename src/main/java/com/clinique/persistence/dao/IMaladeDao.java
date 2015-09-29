/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.dao;

import com.clinique.persistence.model.Malade;
import java.util.Date;
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
public interface IMaladeDao extends JpaRepository<Malade, Long>, JpaSpecificationExecutor<Malade>
{

    @Query("SELECT M FROM Malade M WHERE M.nom LIKE :nom AND M.prenom "
            + " LIKE :prenom AND M.lieuDeNaissance LIKE :lieu AND M.dateDeNaissance >= :date ")
    Page<Malade> search(@Param("nom") String nom, @Param("prenom") String prenom,
            @Param("date") Date dateNaissance, @Param("lieu") String lieuNaissance,
            Pageable pageable);

}
