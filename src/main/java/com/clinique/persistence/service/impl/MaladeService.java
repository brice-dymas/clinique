/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.service.impl;

import com.clinique.persistence.dao.IMaladeDao;
import com.clinique.persistence.model.Malade;
import com.clinique.persistence.service.IMaladeService;
import com.clinique.persistence.service.common.AbstractService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Service("maladeService")
public class MaladeService extends AbstractService<Malade> implements IMaladeService
{

    @Autowired
    IMaladeDao maladeDao;

    @Override
    protected PagingAndSortingRepository<Malade, Long> getDao()
    {
        return maladeDao;
    }

    @Override
    public Page<Malade> search(String nom, String prenom, Date dateNaissance, String lieuNaissance, int page, Integer size)
    {
        return maladeDao.search("%" + nom + "%", "%" + prenom + "%", dateNaissance, "%" + lieuNaissance + "%", new PageRequest(page, size));
    }

    @Override
    public Malade create(Malade entity)
    {
        String code = "";
        // Génération automatique du numéro du malade
        code += (entity.getDateDeNaissance().getYear() + 1900);
        code += entity.getDateDeNaissance().getMonth();
        code += entity.getDateDeNaissance().getDate();
        code += entity.getNom().substring(0, 3).toUpperCase();
        code += entity.getPrenom().substring(0, 2).toUpperCase();
        entity.setNumero(code);

        return maladeDao.save(entity);
    }

    @Override
    public Malade update(Malade entity)
    {
        String code = "";
        final Malade toUpdate = maladeDao.findOne(entity.getId());
        toUpdate.setDateDeNaissance(entity.getDateDeNaissance());
        toUpdate.setEthnie(entity.getEthnie());
        toUpdate.setLieuDeNaissance(entity.getLieuDeNaissance());
        toUpdate.setLieuDeResidence(entity.getLieuDeResidence());
        toUpdate.setNom(entity.getNom());
        toUpdate.setPrenom(entity.getPrenom());
        toUpdate.setProfession(entity.getProfession());
        toUpdate.setTelephone(entity.getTelephone());

        // Génération automatique du numéro du malade
        code += (entity.getDateDeNaissance().getYear() + 1900);
        code += entity.getDateDeNaissance().getMonth();
        code += entity.getDateDeNaissance().getDate();
        code += entity.getNom().substring(0, 3).toUpperCase();
        code += entity.getPrenom().substring(0, 2).toUpperCase();
        toUpdate.setNumero(code);

        return maladeDao.save(toUpdate);
    }

}
