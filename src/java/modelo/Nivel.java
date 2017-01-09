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
public class Nivel implements Serializable 
{
    public enum nivelAsociado{_1,_2,_3,_4,_5,_5m,_6a,_6am,_6b,_6bm,_6c,_6cm,_7a,_7am,_7b,_7bm,_7c,_7cm,_8a,_8am,_8b,_8bm,_8c,_8cm,_9a,_9am,_9b,_9bm,_9c,_9cm};
    
    @Id
    private nivelAsociado nivel;

    /**
     * 
     */
    public Nivel() {}

    /**
     * 
     * @param nivel 
     */
    public Nivel(nivelAsociado nivel) 
    {
        this.nivel = nivel;
    }

    /**
     * 
     * @return 
     */
    public nivelAsociado getNivelAsociado() 
    {
        return nivel;
    }
    
    /**
     * 
     * @param nivel 
     */
    public void setNivel(nivelAsociado nivel) 
    {
        this.nivel = nivel;
    }
}
