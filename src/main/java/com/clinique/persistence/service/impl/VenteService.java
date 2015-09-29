/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service.impl;

import com.clinique.persistence.dao.IClientDao;
import com.clinique.persistence.dao.IRoleDao;
import com.clinique.persistence.dao.IVenteDao;
import com.clinique.persistence.model.Role;
import com.clinique.persistence.model.Vente;
import com.clinique.persistence.service.IVenteService;
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
@Service("venteService")
public class VenteService extends AbstractService<Vente> implements IVenteService
{

    @Autowired
    IClientDao clientDao;

    @Autowired
    IRoleDao roleDao;

    @Autowired
    IVenteDao venteDao;

    @Override
    protected PagingAndSortingRepository<Vente, Long> getDao()
    {
        return venteDao;
    }

    @Override
    public Page<Vente> filterByDate(Date dateOperation, int page, Integer size)
    {
        return venteDao.filterByDate(dateOperation, new PageRequest(page, size));
    }

    @Override
    public Page<Vente> filterByUser(long idUser, int page, Integer size)
    {
        return venteDao.filterByUser(idUser, new PageRequest(page, size));
    }

    @Override
    public Vente update(Vente entity)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final Role userConnected = roleDao.retrieveAUser(auth.getName()); // get the current logged user
        Vente vente = venteDao.findOne(entity.getId());
        vente.setMontant(entity.getMontant());
        vente.setClient(clientDao.findOne(entity.getClient().getId()));
        vente.setUser(userConnected);
        return venteDao.save(vente);
    }

    @Override
    public Vente create(Vente entity)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final Role userConnected = roleDao.retrieveAUser(auth.getName()); // get the current logged user
        entity.setUser(userConnected);
        return venteDao.save(entity);
    }

}
