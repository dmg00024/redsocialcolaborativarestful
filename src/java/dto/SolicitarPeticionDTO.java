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
public class SolicitarPeticionDTO 
{
    private String username;

    public SolicitarPeticionDTO()
    {
        
    }
    
    /**
     * 
     * @param username 
     */
    public SolicitarPeticionDTO(String username) 
    {
        this.username = username;
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
    
}
