/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Daniel
 */
@Entity
public class Provincia implements Serializable
{
    @Id
    private Integer cod_provincia;
    private String provincia;

    /**
     * 
     */
    public Provincia() {}

    /**
     * 
     * @param cod_provincia
     * @param provincia 
     */
    public Provincia(Integer cod_provincia, String provincia)
    {
        this.cod_provincia = cod_provincia;
        this.provincia = provincia;
    }

    /**
     * 
     * @param cod_provincia 
     */
    public Provincia(Integer cod_provincia) 
    {
        this.cod_provincia = cod_provincia;
    }

    /**
     * 
     * @return 
     */
    public Integer getCod_provincia() 
    {
        return cod_provincia;
    }

    /**
     * 
     * @param cod_provincia 
     */
    public void setCod_provincia(Integer cod_provincia) 
    {
        this.cod_provincia = cod_provincia;
    }

    /**
     * 
     * @return 
     */
    public String getProvincia() 
    {
        return provincia;
    }

    /**
     * 
     * @param provincia 
     */
    public void setProvincia(String provincia)
    {
        this.provincia = provincia;
    }
    
}
