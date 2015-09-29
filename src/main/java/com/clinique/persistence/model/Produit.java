/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Entity
public class Produit extends EntityObjet
{

    @NotBlank(message = "{blank.message}")
    private String reference;

    @NotBlank(message = "{blank.message}")
    private String nom;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Stock.class)
    List<Stock> stocks;

    public Produit()
    {
    }

    public String getReference()
    {
        return reference;
    }

    public void setReference(String reference)
    {
        this.reference = reference;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public List<Stock> getStocks()
    {
        return stocks;
    }

    public void setStocks(List<Stock> stocks)
    {
        this.stocks = stocks;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.reference);
        hash = 67 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Produit other = (Produit) obj;
        if (!Objects.equals(this.reference, other.reference))
        {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom))
        {
            return false;
        }
        return true;
    }

}
