/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service;

import com.clinique.persistence.IOperations;
import com.clinique.persistence.model.Malade;
import java.util.Date;
import org.springframework.data.domain.Page;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
public interface IMaladeService extends IOperations<Malade>
{

    Page<Malade> search(String nom, String prenom, Date dateNaissance,
            String lieuNaissance, int page, Integer size);
}
