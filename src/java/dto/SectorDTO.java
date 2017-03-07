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
public class SectorDTO 
{
    private String orientacion;
    private String nombre;
    private String dir_foto;

    /**
     * 
     */
    public SectorDTO() 
    {
        
    }

    /**
     * 
     * @param orientacion
     * @param nombre
     * @param dir_foto 
     */
    public SectorDTO(String orientacion, String nombre, String dir_foto) 
    {
        this.orientacion = orientacion;
        this.nombre = nombre;
        this.dir_foto = dir_foto;
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
    public String getDir_foto()
    {
        return dir_foto;
    }

    /**
     * 
     * @param dir_foto 
     */
    public void setDir_foto(String dir_foto)
    {
        this.dir_foto = dir_foto;
    }
    
}
