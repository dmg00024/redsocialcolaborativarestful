/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Daniel
 */
public class ViaDTO 
{
    private Integer id;
    private String nombre;
    private String sector;
    private String escuela;

    /**
     * 
     */
    public ViaDTO()
    {
        
    }

    /**
     * 
     * @param id
     * @param nombre
     * @param sector
     * @param escuela 
     */
    public ViaDTO(Integer id, String nombre, String sector, String escuela) 
    {
        this.id = id;
        this.nombre = nombre;
        this.sector = sector;
        this.escuela = escuela;
    }

    /**
     * 
     * @return 
     */
    public Integer getId() 
    {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id)
    {
        this.id = id;
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
    public String getSector() 
    {
        return sector;
    }
    
    /**
     * 
     * @param sector 
     */
    public void setSector(String sector)
    {
        this.sector = sector;
    }
    
    /**
     * 
     * @return 
     */
    public String getEscuela() 
    {
        return escuela;
    }
    
    /**
     * 
     * @param escuela 
     */
    public void setEscuela(String escuela)
    {
        this.escuela = escuela;
    }
    
}
