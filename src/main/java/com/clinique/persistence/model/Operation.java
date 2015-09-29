/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@MappedSuperclass
public abstract class Operation extends EntityObjet
{

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected Date dateOperation;

    @Digits(fraction = 0, integer = Integer.MAX_VALUE)
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    protected int montant;

    @ManyToOne(optional = false, targetEntity = Role.class)
    protected Role user;

    public Operation()
    {
    }

    public Date getDateOperation()
    {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation)
    {
        this.dateOperation = dateOperation;
    }

    public Role getUser()
    {
        return user;
    }

    public void setUser(Role user)
    {
        this.user = user;
    }

    public int getMontant()
    {
        return montant;
    }

    public void setMontant(int montant)
    {
        this.montant = montant;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.dateOperation);
        hash = 97 * hash + this.montant;
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
        final Operation other = (Operation) obj;
        if (!Objects.equals(this.dateOperation, other.dateOperation))
        {
            return false;
        }
        if (this.montant != other.montant)
        {
            return false;
        }
        return true;
    }

}
