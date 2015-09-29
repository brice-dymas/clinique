/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service;

import com.clinique.persistence.IOperations;
import com.clinique.persistence.model.Fournisseur;
import org.springframework.data.domain.Page;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
public interface IFournisseurService extends IOperations<Fournisseur>
{

    Page<Fournisseur> filterByNom(String nom, int page, Integer size);
}
