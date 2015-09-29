/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Entity
public class Stock extends EntityObjet
{

    @NotNull(message = "{blank.message}")
    @Temporal(TemporalType.DATE)
    @Future(message = "{future.message}")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date datePeremption;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "{blank.message}")
    @Future(message = "{future.message}")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateAlerte;

    @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "{digits.message}")
    @Min(value = 5, message = "{min.message}")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int quantiteEnStock;

    @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "{digits.message}")
    @Min(value = 3, message = "{min.message}")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int quantiteSeuille;

    @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "{digits.message}")
    @Min(value = 100, message = "{min.message}")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int prixUnitaire;

    @ManyToOne(optional = false, targetEntity = Produit.class)
    Produit produit;

    @ManyToOne(optional = false, targetEntity = Conditionnement.class)
    Conditionnement conditionnement;

    public Stock()
    {
    }

    public Date getDatePeremption()
    {
        return datePeremption;
    }

    public void setDatePeremption(Date datePeremption)
    {
        this.datePeremption = datePeremption;
    }

    public int getQuantiteEnStock()
    {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock)
    {
        this.quantiteEnStock = quantiteEnStock;
    }

    public int getQuantiteSeuille()
    {
        return quantiteSeuille;
    }

    public void setQuantiteSeuille(int quantiteSeuille)
    {
        this.quantiteSeuille = quantiteSeuille;
    }

    public Date getDateAlerte()
    {
        return dateAlerte;
    }

    public void setDateAlerte(Date dateAlerte)
    {
        this.dateAlerte = dateAlerte;
    }

    public int getPrixUnitaire()
    {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire)
    {
        this.prixUnitaire = prixUnitaire;
    }

    public Produit getProduit()
    {
        return produit;
    }

    public void setProduit(Produit produit)
    {
        this.produit = produit;
    }

    public Conditionnement getConditionnement()
    {
        return conditionnement;
    }

    public void setConditionnement(Conditionnement conditionnement)
    {
        this.conditionnement = conditionnement;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.datePeremption);
        hash = 41 * hash + this.quantiteEnStock;
        hash = 41 * hash + this.quantiteSeuille;
        hash = 41 * hash + this.prixUnitaire;
        hash = 41 * hash + Objects.hashCode(this.produit);
        hash = 41 * hash + Objects.hashCode(this.conditionnement);
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
        final Stock other = (Stock) obj;
        if (!Objects.equals(this.datePeremption, other.datePeremption))
        {
            return false;
        }
        if (this.quantiteEnStock != other.quantiteEnStock)
        {
            return false;
        }
        if (this.quantiteSeuille != other.quantiteSeuille)
        {
            return false;
        }
        if (this.prixUnitaire != other.prixUnitaire)
        {
            return false;
        }
        if (!Objects.equals(this.produit, other.produit))
        {
            return false;
        }
        if (!Objects.equals(this.conditionnement, other.conditionnement))
        {
            return false;
        }
        return true;
    }

}
