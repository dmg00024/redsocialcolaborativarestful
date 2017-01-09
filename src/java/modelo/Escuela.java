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
public class Escuela implements Serializable 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_escuela;
    private String nombreEscuela;
    private String descripcion;
    private String horario;
    @Lob
    private byte[] fotoEscuela;
    @ManyToOne (fetch = FetchType.EAGER)
    private final Provincia ubicacion;
    @OneToMany (fetch = FetchType.LAZY)
    private final List<Sector> sectores;
    
    /**
     * 
     */
    public Escuela()
    {
        sectores=new ArrayList();
        ubicacion = null;
    }

    /**
     * 
     * @param nombreEscuela
     * @param ubicacion 
     */
    public Escuela(String nombreEscuela, Provincia ubicacion)
    {
        sectores=new ArrayList();
        this.nombreEscuela = nombreEscuela;
        this.ubicacion = ubicacion;
    }    

    /**
     * 
     * @return 
     */
    public Integer getId_escuela() 
    {
        return id_escuela;
    }

    /**
     * 
     * @param id_escuela 
     */
    public void setId_escuela(Integer id_escuela) 
    {
        this.id_escuela = id_escuela;
    }

    /**
     * 
     * @return 
     */
    public String getNombreEscuela()
    {
        return nombreEscuela;
    }

    /**
     * 
     * @param nombreEscuela 
     */
    public void setNombreEscuela(String nombreEscuela) 
    {
        this.nombreEscuela = nombreEscuela;
    }

    /**
     * 
     * @return 
     */
    public String getDescripcion() 
    {
        return descripcion;
    }

    /**
     * 
     * @param descripcion 
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * 
     * @return 
     */
    public String getHorario() 
    {
        return horario;
    }

    /**
     * 
     * @param horario 
     */
    public void setHorario(String horario) 
    {
        this.horario = horario;
    }
    
    /**
     * 
     * @return 
     */
    public byte[] getFotoEscuela() 
    {
        return fotoEscuela;
    }

    /**
     * 
     * @param fotoEscuela 
     */
    public void setFotoEscuela(byte[] fotoEscuela)
    {
        this.fotoEscuela = fotoEscuela;
    }

    /**
     * 
     * @return 
     */
    public Provincia getUbicacion() 
    {
        return ubicacion;
    }

    /**
     * 
     * @return 
     */
    public List<Sector> getSectores()
    {
        return sectores;
    }
}
