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
public class Comentario implements Serializable 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_comentario;
    private String comentario;
    @ManyToOne (fetch = FetchType.EAGER)
    private Usuario usuario;
    @ManyToOne (fetch = FetchType.EAGER)
    private Via via;
    private Integer puntuación;
    private String valoracion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private final Calendar fecha;
    @Temporal(javax.persistence.TemporalType.TIME)
    private final Date hora;

    /**
     * 
     */
    public Comentario()
    {
        fecha=Calendar.getInstance();
        hora=Calendar.getInstance().getTime();
    }

    /**
     * 
     * @param via 
     */
    public Comentario(Via via) 
    {
        this.via = via;
        fecha=Calendar.getInstance();
        hora=Calendar.getInstance().getTime();
    }

    /**
     * 
     * @param via
     * @param comentario
     * @param usuario 
     */
    public Comentario(Via via, String comentario, Usuario usuario) 
    {
        this.via = via;
        this.comentario = comentario;
        this.usuario = usuario;
        fecha=Calendar.getInstance();
        hora=Calendar.getInstance().getTime();
    }

    /**
     * 
     * @param id_comentario
     * @param comentario
     * @param usuario
     * @param via
     * @param puntuación
     * @param valoracion
     * @param fecha
     * @param hora 
     */
    public Comentario(Integer id_comentario, String comentario, Usuario usuario, Via via, Integer puntuación, String valoracion, Calendar fecha, Date hora) 
    {
        this.id_comentario = id_comentario;
        this.comentario = comentario;
        this.usuario = usuario;
        this.via = via;
        this.puntuación = puntuación;
        this.valoracion = valoracion;
        this.fecha = fecha;
        this.hora = hora;
    }

    /**
     * 
     * @param comentario
     * @param usuario
     * @param via
     * @param puntuación
     * @param valoracion
     * @param fecha
     * @param hora 
     */
    public Comentario(String comentario, Usuario usuario, Via via, Integer puntuación, String valoracion, Calendar fecha, Date hora)
    {
        this.comentario = comentario;
        this.usuario = usuario;
        this.via = via;
        this.puntuación = puntuación;
        this.valoracion = valoracion;
        this.fecha = fecha;
        this.hora = hora;
    }
    
    /**
     * 
     * @return 
     */
    public Integer getId_comentario() 
    {
        return id_comentario;
    }

    /**
     * 
     * @param id_comentario 
     */
    public void setId_comentario(Integer id_comentario) 
    {
        this.id_comentario = id_comentario;
    }

    /**
     * 
     * @return 
     */
    public String getComentario()
    {
        return comentario;
    }

    /**
     * 
     * @param comentario 
     */
    public void setComentario(String comentario)
    {
        this.comentario = comentario;
    }

    /**
     * 
     * @return 
     */
    public Usuario getUsuario()
    {
        return usuario;
    }

    /**
     * 
     * @param usuario 
     */
    public void setUsuario(Usuario usuario) 
    {
        this.usuario = usuario;
    }

    /**
     * 
     * @return 
     */
    public Via getVia()
    {
        return via;
    }

    /**
     * 
     * @param via 
     */
    public void setVia(Via via) 
    {
        this.via = via;
    }
    
    /**
     * 
     * @return 
     */
    public Integer getPuntuación() 
    {
        return puntuación;
    }

    /**
     * 
     * @param puntuación 
     */
    public void setPuntuación(Integer puntuación)
    {
        this.puntuación = puntuación;
    }

    /**
     * 
     * @return 
     */
    public String getValoracion() 
    {
        return valoracion;
    }

    /**
     * 
     * @param valoracion 
     */
    public void setValoracion(String valoracion) 
    {
        this.valoracion = valoracion;
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
