/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service;

import com.clinique.persistence.IOperations;
import com.clinique.persistence.model.Stock;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
public interface IStockService extends IOperations<Stock>
{

    Page<Stock> findByDatePeremption(Date datePeremption, int page, Integer size);

    List<Stock> filterByProduit(long idProduit);
}
