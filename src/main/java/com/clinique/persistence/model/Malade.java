/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Entity
public class Malade extends EntityObjet
{

    private String numero;

    @NotBlank(message = "{blank.message}")
    private String nom;

    @NotBlank(message = "{blank.message}")
    private String prenom;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateDeNaissance;

    @NotBlank(message = "{blank.message}")
    private String lieuDeNaissance;

    @NotBlank(message = "{blank.message}")
    private String lieuDeResidence;

    @NotBlank(message = "{blank.message}")
    private String telephone;

    @NotBlank(message = "{blank.message}")
    private String ethnie;

    @NotBlank(message = "{blank.message}")
    private String profession;

    public Malade()
    {
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public Date getDateDeNaissance()
    {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance)
    {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance()
    {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance)
    {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public String getLieuDeResidence()
    {
        return lieuDeResidence;
    }

    public void setLieuDeResidence(String lieuDeResidence)
    {
        this.lieuDeResidence = lieuDeResidence;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getEthnie()
    {
        return ethnie;
    }

    public void setEthnie(String ethnie)
    {
        this.ethnie = ethnie;
    }

    public String getProfession()
    {
        return profession;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.numero);
        hash = 37 * hash + Objects.hashCode(this.nom);
        hash = 37 * hash + Objects.hashCode(this.prenom);
        hash = 37 * hash + Objects.hashCode(this.dateDeNaissance);
        hash = 37 * hash + Objects.hashCode(this.lieuDeNaissance);
        hash = 37 * hash + Objects.hashCode(this.lieuDeResidence);
        hash = 37 * hash + Objects.hashCode(this.telephone);
        hash = 37 * hash + Objects.hashCode(this.ethnie);
        hash = 37 * hash + Objects.hashCode(this.profession);
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
        final Malade other = (Malade) obj;
        if (!Objects.equals(this.numero, other.numero))
        {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom))
        {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom))
        {
            return false;
        }
        if (!Objects.equals(this.dateDeNaissance, other.dateDeNaissance))
        {
            return false;
        }
        if (!Objects.equals(this.lieuDeNaissance, other.lieuDeNaissance))
        {
            return false;
        }
        if (!Objects.equals(this.lieuDeResidence, other.lieuDeResidence))
        {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone))
        {
            return false;
        }
        if (!Objects.equals(this.ethnie, other.ethnie))
        {
            return false;
        }
        if (!Objects.equals(this.profession, other.profession))
        {
            return false;
        }
        return true;
    }

}
