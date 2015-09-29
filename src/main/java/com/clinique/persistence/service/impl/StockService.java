/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service.impl;

import com.clinique.persistence.dao.IConditionnementDao;
import com.clinique.persistence.dao.IProduitDao;
import com.clinique.persistence.dao.IStockDao;
import com.clinique.persistence.model.Stock;
import com.clinique.persistence.service.IStockService;
import com.clinique.persistence.service.common.AbstractService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Service("stockService")
public class StockService extends AbstractService<Stock> implements IStockService
{

    @Autowired
    IConditionnementDao conditionnementDao;

    @Autowired
    IProduitDao produitDao;

    @Autowired
    IStockDao stockDao;

    @Override
    protected PagingAndSortingRepository<Stock, Long> getDao()
    {
        return stockDao;
    }

    @Override
    public Page<Stock> findByDatePeremption(Date datePeremption, int page, Integer size)
    {
        return stockDao.findByDatePeremption(datePeremption, new PageRequest(page, size));
    }

    @Override
    public Stock create(Stock entity)
    {
        entity.setConditionnement(conditionnementDao.findOne(entity.getConditionnement().getId()));
        entity.setProduit(produitDao.findOne(entity.getProduit().getId()));
        return stockDao.save(entity);
    }

    @Override
    public Stock update(Stock entity)
    {
        Stock stock = stockDao.findOne(entity.getId());
        stock.setDatePeremption(entity.getDatePeremption());
        stock.setDateAlerte(entity.getDateAlerte());
        stock.setPrixUnitaire(entity.getPrixUnitaire());
        stock.setQuantiteEnStock(entity.getQuantiteEnStock());
        stock.setQuantiteSeuille(entity.getQuantiteSeuille());
        stock.setConditionnement(conditionnementDao.findOne(entity.getConditionnement().getId()));
        stock.setProduit(produitDao.findOne(entity.getProduit().getId()));
        return stockDao.save(stock);
    }

    @Override
    public List<Stock> filterByProduit(long idProduit)
    {
        return stockDao.filterByProduit(idProduit);
    }

}
