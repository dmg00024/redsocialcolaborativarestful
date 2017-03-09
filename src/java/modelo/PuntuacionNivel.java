/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Daniel
 */

@Entity
public class PuntuacionNivel implements Serializable 
{
    @Id
    @ManyToOne
    private Nivel nivel;

    /**
     * 
     */
    public PuntuacionNivel() 
    {
        
    }

    /**
     * 
     * @param nivel 
     */
    public PuntuacionNivel(Nivel nivel)
    {
        this.nivel = nivel;
    }

    /**
     * 
     * @return 
     */
    public Nivel getNivel()
    {
        return nivel;
    }

    /**
     * 
     * @param nivel 
     */
    public void setNivel(Nivel nivel) 
    {
        this.nivel = nivel;
    }
    
}
