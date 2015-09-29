/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.web.form;

import com.clinique.persistence.model.Produit;
import com.clinique.persistence.model.Stock;
import com.clinique.web.util.ShrinkableLazyList;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.collections.FactoryUtils;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
public class ProduitForm
{

    @Valid
    private Produit produit;

    @Valid
    @NotEmpty(message = "Remplissez au moins une ligne")
    //private AutoPopulatingList<LigneAppel> ligneAppels;
    private List<Stock> stocks = ShrinkableLazyList.decorate(
            new ArrayList(), FactoryUtils.instantiateFactory(Stock.class));

    public ProduitForm()
    {
    }

    public Produit getProduit()
    {
        if (produit != null)
        {
            produit.setStocks(stocks);
        }
        return produit;
    }

    public void setProduit(Produit produit)
    {
        this.produit = produit;
    }

    public List<Stock> getStocks()
    {
        return stocks;
    }

    public void setStocks(List<Stock> stocks)
    {
        this.stocks = stocks;
    }

}
