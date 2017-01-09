/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

/**
 *
 * @author Daniel
 */
@Entity
public class Sector implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_sector;
    private String nombreSector;
    @Lob
    private byte[] fotoSector;
    @ManyToOne (fetch = FetchType.EAGER)
    private Escuela escuela;
    @ManyToOne (fetch = FetchType.EAGER)
    private final Orientacion orientacion;
    @OneToMany (fetch = FetchType.LAZY)
    private final List<Via> vias;

    /**
     * 
     */
    public Sector() 
    {
        vias=new ArrayList();
        orientacion=null;
    }

    /**
     * 
     * @param orientacion 
     */
    public Sector(Orientacion orientacion) 
    {
        vias=new ArrayList();
        this.orientacion = orientacion;
    }
    
    /**
     * 
     * @param nombreSector
     * @param orientacion 
     */
    public Sector(String nombreSector, Orientacion orientacion) 
    {
        vias=new ArrayList();
        this.nombreSector = nombreSector;
        this.orientacion = orientacion;
    }

    /**
     * 
     * @return 
     */
    public Integer getId_sector() 
    {
        return id_sector;
    }

    /**
     * 
     * @param id_sector 
     */
    public void setId_sector(Integer id_sector) 
    {
        this.id_sector = id_sector;
    }

    /**
     * 
     * @return 
     */
    public String getNombreSector()
    {
        return nombreSector;
    }

    /**
     * 
     * @param nombreSector 
     */
    public void setNombreSector(String nombreSector)
    {
        this.nombreSector = nombreSector;
    }

    /**
     * 
     * @return 
     */
    public byte[] getFotoSector() 
    {
        return fotoSector;
    }

    /**
     * 
     * @param fotoSector 
     */
    public void setFotoSector(byte[] fotoSector) 
    {
        this.fotoSector = fotoSector;
    }

    /**
     * 
     * @return 
     */
    public Escuela getEscuela() 
    {
        return escuela;
    }

    /**
     * 
     * @param escuela 
     */
    public void setEscuela(Escuela escuela) 
    {
        this.escuela = escuela;
    }

    /**
     * 
     * @return 
     */
    public Orientacion getOrientacion() 
    {
        return orientacion;
    }

    /**
     * 
     * @return 
     */
    public List<Via> getVias() 
    {
        return vias;
    }
    
}
