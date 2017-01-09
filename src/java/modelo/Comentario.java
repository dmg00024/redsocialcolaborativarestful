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

    /**
     * 
     */
    public Comentario(){}

    /**
     * 
     * @param via 
     */
    public Comentario(Via via) 
    {
        this.via = via;
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
    
}
