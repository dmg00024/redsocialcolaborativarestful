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
public class NuevaViaDTO
{
    private String nombre;
    private String id_mapa;
    private String nivel_oficial;

    /**
     * 
     */
    public NuevaViaDTO() 
    {
        
    }

    /**
     * 
     * @param nombre
     * @param nivel_oficial 
     */
    public NuevaViaDTO(String nombre, String nivel_oficial) 
    {
        this.nombre = nombre;
        this.nivel_oficial = nivel_oficial;
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
    public String getId_mapa()
    {
        return id_mapa;
    }

    /**
     * 
     * @param id_mapa 
     */
    public void setId_mapa(String id_mapa) 
    {
        this.id_mapa = id_mapa;
    }

    /**
     * 
     * @return 
     */
    public String getNivel_oficial() 
    {
        return nivel_oficial;
    }

    /**
     * 
     * @param nivel_oficial 
     */
    public void setNivel_oficial(String nivel_oficial)
    {
        this.nivel_oficial = nivel_oficial;
    } 
    
}
