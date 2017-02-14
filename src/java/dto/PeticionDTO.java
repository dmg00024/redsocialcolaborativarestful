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
public class PeticionDTO 
{
    private Integer idPeticion;
    private String emisor;

    /**
     * 
     */
    public PeticionDTO() 
    {
        
    }
    
    /**
     * 
     * @param idPeticion
     * @param emisor 
     */
    public PeticionDTO(Integer idPeticion, String emisor) 
    {
        this.idPeticion = idPeticion;
        this.emisor = emisor;
    }

    /**
     * 
     * @return 
     */
    public Integer getIdPeticion() 
    {
        return idPeticion;
    }
    
    /**
     * 
     * @param idPeticion 
     */
    public void setIdPeticion(Integer idPeticion) 
    {
        this.idPeticion = idPeticion;
    }

    /**
     * 
     * @return 
     */
    public String getEmisor() 
    {
        return emisor;
    }

    /**
     * 
     * @param emisor 
     */
    public void setEmisor(String emisor) 
    {
        this.emisor = emisor;
    }
    
}
