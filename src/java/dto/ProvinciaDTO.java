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
public class ProvinciaDTO 
{
    private String provincia;

    /**
     * 
     */
    public ProvinciaDTO()
    {
        
    }
    
    /**
     * 
     * @param provincia 
     */
    public ProvinciaDTO(String provincia) 
    {
        this.provincia = provincia;
    }
    
    /**
     * 
     * @return 
     */
    public String getProvincia() 
    {
        return provincia;
    }

    /**
     * 
     * @param provincia 
     */
    public void setProvincia(String provincia) 
    {
        this.provincia = provincia;
    } 
}
