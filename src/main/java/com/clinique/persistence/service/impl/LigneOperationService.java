/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service.impl;

import com.clinique.persistence.dao.ILigneOperationDao;
import com.clinique.persistence.dao.IStockDao;
import com.clinique.persistence.model.LigneOperation;
import com.clinique.persistence.service.ILigneOperationService;
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
@Service("ligneOperationService")
public class LigneOperationService extends AbstractService<LigneOperation> implements ILigneOperationService
{

    @Autowired
    IStockDao stockDao;

    @Autowired
    ILigneOperationDao dao;

    @Override
    protected PagingAndSortingRepository<LigneOperation, Long> getDao()
    {
        return dao;
    }

    @Override
    public Page<LigneOperation> filterByOperationsId(long idOperation, int page, Integer size)
    {
        return dao.filterByOperationsId(idOperation, new PageRequest(page, size));
    }

}
