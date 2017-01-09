/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Daniel
 */
@Entity
public class PeticionAmistad implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_peticion;
    @ManyToOne (fetch = FetchType.EAGER)
    private Usuario emisor;
    @ManyToOne (fetch = FetchType.EAGER)
    private Usuario receptor;
    @Temporal(javax.persistence.TemporalType.DATE)
    private final Calendar fecha;
    @Temporal(javax.persistence.TemporalType.TIME)
    private final Date hora;

    /**
     * 
     */
    public PeticionAmistad() 
    {
        fecha=Calendar.getInstance();
        hora=Calendar.getInstance().getTime();
    }
    
    /**
     * 
     * @param _emisor
     * @param _receptor 
     */
    public PeticionAmistad(Usuario _emisor, Usuario _receptor)
    {
        fecha=Calendar.getInstance();
        hora=Calendar.getInstance().getTime();
        emisor=_emisor;
        receptor=_receptor;
    }

    /**
     * 
     * @return 
     */
    public Integer getId_peticion() 
    {
        return id_peticion;
    }
    
    /**
     * 
     * @param id_peticion 
     */
    public void setId_peticion(Integer id_peticion) 
    {
        this.id_peticion = id_peticion;
    }

    /**
     * 
     * @return 
     */
    public Usuario getEmisor() 
    {
        return emisor;
    }

    /**
     * 
     * @param emisor 
     */
    public void setEmisor(Usuario emisor) 
    {
        this.emisor = emisor;
    }

    /**
     * 
     * @return 
     */
    public Usuario getReceptor() 
    {
        return receptor;
    }
    
    /**
     * 
     * @param receptor 
     */
    public void setReceptor(Usuario receptor)
    {
        this.receptor = receptor;
    }

    /**
     * 
     * @return 
     */
    public Calendar getFecha() 
    {
        return fecha;
    }

    /**
     * 
     * @return 
     */
    public Date getHora()
    {
        return hora;
    }
    
}
