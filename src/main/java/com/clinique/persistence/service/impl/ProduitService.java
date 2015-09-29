/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service.impl;

import com.clinique.persistence.dao.IConditionnementDao;
import com.clinique.persistence.dao.IProduitDao;
import com.clinique.persistence.dao.IStockDao;
import com.clinique.persistence.model.Produit;
import com.clinique.persistence.model.Stock;
import com.clinique.persistence.service.IProduitService;
import com.clinique.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Service("produitService")
public class ProduitService extends AbstractService<Produit> implements IProduitService
{

    @Autowired
    IStockDao stockDao;

    @Autowired
    IConditionnementDao cdao;

    @Autowired
    IProduitDao produitDao;

    @Override
    protected PagingAndSortingRepository<Produit, Long> getDao()
    {
        return produitDao;
    }

    @Override
    public Produit create(Produit entity)
    {
        final Produit produit = produitDao.save(entity);

        for (Stock stock : produit.getStocks())
        {
            stock.setConditionnement(cdao.findOne(stock.getConditionnement().getId()));
            stock.setProduit(produit);
            stockDao.save(stock);
        }
        return produit;
    }

    @Override
    public Page<Produit> filterByNom(String nom, int page, Integer size)
    {
        return produitDao.filterByNom("%" + nom + "%", new PageRequest(page, size));
    }

    @Override
    public Produit update(Produit entity)
    {
        Produit produit = produitDao.findOne(entity.getId());
        produit.setNom(entity.getNom());
        produit.setReference(entity.getReference());
        return produitDao.save(produit);
    }

}
