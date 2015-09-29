/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Entity
public class LigneOperation extends EntityObjet
{

    @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "{digits.message}")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 1, message = "{min.message}")
    private int quantite;

    @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "{digits.message}")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 500, message = "{min.message}")
    private int prixUnitaire;

    @ManyToOne(optional = false)
    private Vente vente;

    @ManyToOne(optional = false, targetEntity = Stock.class)
    private Stock stock;

    public LigneOperation()
    {
    }

    public int getQuantite()
    {
        return quantite;
    }

    public void setQuantite(int quantite)
    {
        this.quantite = quantite;
    }

    public int getPrixUnitaire()
    {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire)
    {
        this.prixUnitaire = prixUnitaire;
    }

    public Vente getOperation()
    {
        return vente;
    }

    public void setOperation(Vente operation)
    {
        this.vente = operation;
    }

    public Stock getStock()
    {
        return stock;
    }

    public void setStock(Stock stock)
    {
        this.stock = stock;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 79 * hash + this.quantite;
        hash = 79 * hash + this.prixUnitaire;
        hash = 79 * hash + Objects.hashCode(this.vente);
        hash = 79 * hash + Objects.hashCode(this.stock);
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
        final LigneOperation other = (LigneOperation) obj;
        if (this.quantite != other.quantite)
        {
            return false;
        }
        if (this.prixUnitaire != other.prixUnitaire)
        {
            return false;
        }
        if (!Objects.equals(this.vente, other.vente))
        {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock))
        {
            return false;
        }
        return true;
    }

}
