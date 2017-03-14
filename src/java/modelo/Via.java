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
    private String idv_via;
    private String nombre;
    @ManyToOne (fetch = FetchType.EAGER)
    private Nivel nivel;
    @ManyToOne (fetch = FetchType.EAGER)
    private Nivel nivelConsensuado;
    private double valoracion_media_nivel;
    private Integer estrellas;
    private double valoracion_media;
    @OneToMany (fetch = FetchType.LAZY)
    private final List<Comentario> comentarios;
    @ManyToOne (fetch = FetchType.EAGER)
    private Sector sector;
    private Integer contador;
    
    /**
     * 
     */
    public Via()
    {
        comentarios=new ArrayList();
        contador=0;
        valoracion_media=0;
        valoracion_media_nivel=0;
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
        contador=0;
        valoracion_media=0;
        valoracion_media_nivel=0;
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
    public String getIdv_via() 
    {
        return idv_via;
    }

    /**
     * 
     * @param idv_via 
     */
    public void setIdv_via(String idv_via)
    {
        this.idv_via = idv_via;
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
    public double getValoracion_media_nivel() 
    {
        return valoracion_media_nivel;
    }

    /**
     * 
     * @param valoracion_media_nivel 
     */
    public void setValoracion_media_nivel(double valoracion_media_nivel)
    {
        this.valoracion_media_nivel = valoracion_media_nivel;
    }

    /**
     * 
     * @return 
     */
    public Integer getEstrellas()
    {
        return estrellas;
    }

    /**
     * 
     * @param estrellas 
     */
    public void setEstrellas(Integer estrellas) 
    {
        this.estrellas = estrellas;
    }

    /**
     * 
     * @return 
     */
    public double getValoracion_media() 
    {
        return valoracion_media;
    }

    /**
     * 
     * @param valoracion_media 
     */
    public void setValoracion_media(double valoracion_media) 
    {
        this.valoracion_media = valoracion_media;
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

    /**
     * 
     * @return 
     */
    public Integer getContador() 
    {
        return contador;
    }

    /**
     * 
     * @param contador 
     */
    public void setContador(Integer contador) 
    {
        this.contador = contador;
    }
    
}
