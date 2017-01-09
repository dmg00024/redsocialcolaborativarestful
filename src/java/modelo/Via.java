/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Daniel
 */
@Entity
public class Via implements Serializable 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_via;
    private String nombre;
    @ManyToOne (fetch = FetchType.EAGER)
    private Nivel nivel;
    @ManyToOne (fetch = FetchType.EAGER)
    private Nivel nivelConsensuado;
    @OneToMany (fetch = FetchType.LAZY)
    private final List<Comentario> comentarios;
    @ManyToOne (fetch = FetchType.EAGER)
    private Sector sector;
    
    /**
     * 
     */
    public Via()
    {
        comentarios=new ArrayList();
    }

    /**
     * 
     * @param nombre
     * @param nivel 
     */
    public Via(String nombre, Nivel nivel) 
    {
        comentarios=new ArrayList();
        this.nombre = nombre;
        this.nivel = nivel;
    }

    /**
     * 
     * @return 
     */
    public Integer getId_via()
    {
        return id_via;
    }

    /**
     * 
     * @param id_via 
     */
    public void setId_via(Integer id_via)
    {
        this.id_via = id_via;
    }

    /**
     * 
     * @return 
     */
    public String getNombre() 
    {
        return nombre;
    }

    /**
     * 
     * @param nombre 
     */
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
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

    /**
     * 
     * @return 
     */
    public Nivel getNivelConsensuado()
    {
        return nivelConsensuado;
    }

    /**
     * 
     * @param nivelConsensuado 
     */
    public void setNivelConsensuado(Nivel nivelConsensuado) 
    {
        this.nivelConsensuado = nivelConsensuado;
    }

    /**
     * 
     * @return 
     */
    public Sector getSector() 
    {
        return sector;
    }

    /**
     * 
     * @param sector 
     */
    public void setSector(Sector sector) 
    {
        this.sector = sector;
    }

    /**
     * 
     * @return 
     */
    public List<Comentario> getComentarios() 
    {
        return comentarios;
    }
    
}
