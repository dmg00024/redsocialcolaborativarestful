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
public class UsuarioDTO 
{
    private String username;
    private String password;
    private String email;
   
    /**
     * 
     */
    public UsuarioDTO(){}

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
    public String getPassword()
    {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * 
     * @return 
     */
    public String getEmail() 
    {
        return email;
    }

    /**
     * 
     * @param email 
     */
    public void setEmail(String email) 
    {
        this.email = email;
    }
    
}
