/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service.impl;

import com.clinique.persistence.dao.IConditionnementDao;
import com.clinique.persistence.model.Conditionnement;
import com.clinique.persistence.service.IConditionnementService;
import com.clinique.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Service("conditionnementService")
public class ConditionnementService extends AbstractService<Conditionnement> implements IConditionnementService
{

    @Autowired
    IConditionnementDao dao;

    @Override
    protected PagingAndSortingRepository<Conditionnement, Long> getDao()
    {
        return dao;
    }

    @Override
    public Conditionnement update(Conditionnement entity)
    {
        Conditionnement conditionnement = dao.findOne(entity.getId());
        conditionnement.setNom(entity.getNom());
        return dao.save(conditionnement);
    }

}
