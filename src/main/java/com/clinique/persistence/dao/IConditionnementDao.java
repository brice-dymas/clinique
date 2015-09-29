/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.dao;

import com.clinique.persistence.model.Conditionnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
public interface IConditionnementDao extends JpaRepository<Conditionnement, Long>, JpaSpecificationExecutor<Conditionnement>
{

}
