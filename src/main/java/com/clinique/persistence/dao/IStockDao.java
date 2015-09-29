/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.dao;

import com.clinique.persistence.model.Stock;
import java.util.Date;
import java.util.List;
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
public interface IStockDao extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock>
{

    Page<Stock> findByDatePeremption(Date datePeremption, Pageable pageable);

    @Query("SELECT S FROM Stock S WHERE S.produit.id=:id")
    List<Stock> filterByProduit(@Param("id") long id);
}
