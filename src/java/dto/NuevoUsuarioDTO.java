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
public class NuevoUsuarioDTO 
{
    private String username;
    private String password;
    private String confPassword;
    private String mail;
    private String confMail;
   
    /**
     * 
     */
    public NuevoUsuarioDTO(){}

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
    public String getConfPassword() 
    {
        return confPassword;
    }

    /**
     * 
     * @param confPassword 
     */
    public void setConfPassword(String confPassword)
    {
        this.confPassword = confPassword;
    }

    /**
     * 
     * @return 
     */
    public String getMail() 
    {
        return mail;
    }

    /**
     * 
     * @param mail 
     */
    public void setMail(String mail)
    {
        this.mail = mail;
    }

    /**
     * 
     * @return 
     */
    public String getConfMail()
    {
        return confMail;
    }

    /**
     * 
     * @param confMail 
     */
    public void setConfMail(String confMail) 
    {
        this.confMail = confMail;
    }
    
}
