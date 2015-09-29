/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.dao;

import com.clinique.persistence.model.Approvisionnement;
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
public interface IApprovisionnementDao extends JpaRepository<Approvisionnement, Long>, JpaSpecificationExecutor<Approvisionnement>
{

    @Query("SELECT A FROM Approvisionnement A WHERE A.dateOperation =:date")
    Page<Approvisionnement> filterByDate(@Param("date") Date dateOperation, Pageable pageable);

    @Query("SELECT A FROM Approvisionnement A WHERE A.user.id =:id")
    Page<Approvisionnement> filterByUser(@Param("id") long idUser, Pageable pageable);
}
