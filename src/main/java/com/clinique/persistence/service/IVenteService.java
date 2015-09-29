/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service;

import com.clinique.persistence.IOperations;
import com.clinique.persistence.model.Vente;
import java.util.Date;
import org.springframework.data.domain.Page;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
public interface IVenteService extends IOperations<Vente>
{

    Page<Vente> filterByDate(Date dateOperation, int page, Integer size);

    Page<Vente> filterByUser(long idUser, int page, Integer size);
}
