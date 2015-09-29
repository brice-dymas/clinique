/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service.impl;

import com.clinique.persistence.dao.IApprovisionnementDao;
import com.clinique.persistence.dao.IFournisseurDao;
import com.clinique.persistence.dao.IRoleDao;
import com.clinique.persistence.model.Approvisionnement;
import com.clinique.persistence.model.Role;
import com.clinique.persistence.service.IApprovisionnementService;
import com.clinique.persistence.service.common.AbstractService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Service("approvisionnementService")
public class ApprovisionnementService extends AbstractService<Approvisionnement> implements IApprovisionnementService
{

    @Autowired
    IApprovisionnementDao dao;

    @Autowired
    IFournisseurDao fournisseurDao;

    @Autowired
    IRoleDao roleDao;

    @Override
    protected PagingAndSortingRepository<Approvisionnement, Long> getDao()
    {
        return dao;
    }

    @Override
    public Page<Approvisionnement> filterByDate(Date dateOperation, int page, Integer size)
    {
        return dao.filterByDate(dateOperation, new PageRequest(page, size));
    }

    @Override
    public Page<Approvisionnement> filterByUser(long idUser, int page, Integer size)
    {
        return dao.filterByUser(idUser, new PageRequest(page, size));
    }

    @Override
    public Approvisionnement update(Approvisionnement entity)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final Role userConnected = roleDao.retrieveAUser(auth.getName()); // get the current logged user
        Approvisionnement approvisionnement = dao.findOne(entity.getId());
        approvisionnement.setMontant(entity.getMontant());
        approvisionnement.setFournisseur(fournisseurDao.findOne(entity.getFournisseur().getId()));
        approvisionnement.setUser(userConnected);
        return dao.save(approvisionnement);
    }

    @Override
    public Approvisionnement create(Approvisionnement entity)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final Role userConnected = roleDao.retrieveAUser(auth.getName()); // get the current logged user
        entity.setUser(userConnected);
        return dao.save(entity);

    }

}
