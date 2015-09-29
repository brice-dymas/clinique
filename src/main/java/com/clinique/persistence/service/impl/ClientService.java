/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service.impl;

import com.clinique.persistence.dao.IClientDao;
import com.clinique.persistence.model.Client;
import com.clinique.persistence.service.IClientService;
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
@Service("clientService")
public class ClientService extends AbstractService<Client> implements IClientService
{

    @Autowired
    IClientDao clientDao;

    @Override
    protected PagingAndSortingRepository<Client, Long> getDao()
    {
        return clientDao;
    }

    @Override
    public Page<Client> filterByNom(String nom, int page, Integer size)
    {
        return clientDao.filterByNom("%" + nom + "%", new PageRequest(page, size));
    }

    @Override
    public Client update(Client entity)
    {
        Client toUpdate = clientDao.findOne(entity.getId());
        toUpdate.setNom(entity.getNom());
        return clientDao.save(toUpdate);
    }

}
