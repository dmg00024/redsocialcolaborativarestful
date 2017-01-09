/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Daniel
 */
@Entity
public class Orientacion implements Serializable
{
    public enum orientacion{N,S,E,O,NE,SE,SO,NO};
    
    @Id
    private orientacion orientacion;
    
    /**
     * 
     */
    public Orientacion(){}

    /**
     * 
     * @param orientacion 
     */
    public Orientacion(orientacion orientacion) 
    {
        this.orientacion = orientacion;
    }

    /**
     * 
     * @return 
     */
    public orientacion getOrientacion()
    {
        return orientacion;
    }

    /**
     * 
     * @param orientacion 
     */
    public void setOrientacion(orientacion orientacion) 
    {
        this.orientacion = orientacion;
    }
}
