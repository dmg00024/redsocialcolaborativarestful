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
public class Puntuacion implements Serializable 
{
    @Id
    private Integer valor_puntuacion;

    /**
     * 
     */
    public Puntuacion() 
    {
        
    }

    /**
     * 
     * @param valor_puntuacion 
     */
    public Puntuacion(Integer valor_puntuacion) 
    {
        this.valor_puntuacion = valor_puntuacion;
    }

    /**
     * 
     * @return 
     */
    public Integer getValor_puntuacion()
    {
        return valor_puntuacion;
    }
    
    /**
     * 
     * @param valor_puntuacion 
     */
    public void setValor_puntuacion(Integer valor_puntuacion) 
    {
        this.valor_puntuacion = valor_puntuacion;
    }
    
}
