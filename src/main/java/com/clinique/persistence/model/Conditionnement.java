/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.model;

import java.util.Objects;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Entity
public class Conditionnement extends EntityObjet
{

    @NotBlank(message = "{blank.message}")
    private String nom;

    public Conditionnement()
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
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.nom);
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
        final Conditionnement other = (Conditionnement) obj;
        if (!Objects.equals(this.nom, other.nom))
        {
            return false;
        }
        return true;
    }

}
