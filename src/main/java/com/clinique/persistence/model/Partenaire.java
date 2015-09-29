/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.model;

import java.util.Objects;
import javax.persistence.MappedSuperclass;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@MappedSuperclass
public abstract class Partenaire extends EntityObjet
{

    @NotBlank(message = "{blank.message}")
    protected String nom;

    public Partenaire()
    {
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nom);
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
        final Partenaire other = (Partenaire) obj;
        if (!Objects.equals(this.nom, other.nom))
        {
            return false;
        }
        return true;
    }

}
