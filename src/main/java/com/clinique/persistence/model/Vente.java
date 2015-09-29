/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Entity
public class Vente extends Operation
{

    @ManyToOne(optional = false)
    private Client client;

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

}
