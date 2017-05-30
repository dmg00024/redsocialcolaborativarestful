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
public class AmigosDTO 
{
    private String username;
    private String nombre;

    /**
     * 
     */
    public AmigosDTO() 
    {
        
    }

    /**
     * 
     * @return 
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * 
     * @param username 
     */
    public void setUsername(String username) 
    {
        this.username = username;
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
    
}
