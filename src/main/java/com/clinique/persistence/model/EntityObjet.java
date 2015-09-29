/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.persistence.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@MappedSuperclass
public abstract class EntityObjet implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Version
    protected long version;

    public EntityObjet()
    {
    }

    public EntityObjet(long id, long verion)
    {
        this.id = id;
        this.version = verion;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getVersion()
    {
        return version;
    }

    public void setVersion(long version)
    {
        this.version = version;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + (int) (this.version ^ (this.version >>> 32));
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
        final EntityObjet other = (EntityObjet) obj;
        if (this.id != other.id)
        {
            return false;
        }
        if (this.version != other.version)
        {
            return false;
        }
        return true;
    }

}
