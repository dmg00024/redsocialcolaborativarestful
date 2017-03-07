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
public class SectoresDTO 
{
    private Integer id_sector;
    private String orientacion;
    private String nombre;
    private byte[] foto;

    /**
     * 
     */
    public SectoresDTO() 
    {
        
    }

    /**
     * 
     * @param id_sector
     * @param orientacion
     * @param nombre
     * @param foto 
     */
    public SectoresDTO(Integer id_sector, String orientacion, String nombre, byte[] foto) 
    {
        this.id_sector = id_sector;
        this.orientacion = orientacion;
        this.nombre = nombre;
        this.foto = foto;
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
    public String getOrientacion()
    {
        return orientacion;
    }

    /**
     * 
     * @param orientacion 
     */
    public void setOrientacion(String orientacion) 
    {
        this.orientacion = orientacion;
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
    public byte[] getFoto()
    {
        return foto;
    }

    /**
     * 
     * @param foto 
     */
    public void setFoto(byte[] foto)
    {
        this.foto = foto;
    }
    
}
