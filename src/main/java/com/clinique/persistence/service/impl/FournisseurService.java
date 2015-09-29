/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service.impl;

import com.clinique.persistence.dao.IFournisseurDao;
import com.clinique.persistence.model.Fournisseur;
import com.clinique.persistence.service.IFournisseurService;
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
@Service("fournisseurService")
public class FournisseurService extends AbstractService<Fournisseur> implements IFournisseurService
{

    @Autowired
    IFournisseurDao dao;

    @Override
    protected PagingAndSortingRepository<Fournisseur, Long> getDao()
    {
        return dao;
    }

    @Override
    public Page<Fournisseur> filterByNom(String nom, int page, Integer size)
    {
        return dao.filterByNom("%" + nom + "%", new PageRequest(page, size));
    }

    @Override
    public Fournisseur update(Fournisseur entity)
    {
        Fournisseur fournisseur = dao.findOne(entity.getId());
        fournisseur.setNom(entity.getNom());
        return dao.save(fournisseur);
    }

}
