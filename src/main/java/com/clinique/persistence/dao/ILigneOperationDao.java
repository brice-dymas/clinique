/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.dao;

import com.clinique.persistence.model.LigneOperation;
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
public interface ILigneOperationDao extends JpaRepository<LigneOperation, Long>, JpaSpecificationExecutor<LigneOperation>
{

    @Query("SELECT L FROM LigneOperation L WHERE L.vente.id=:id")
    Page<LigneOperation> filterByOperationsId(@Param("id") long idOperation, Pageable pageable);
}
