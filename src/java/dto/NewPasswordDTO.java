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
public class NewPasswordDTO 
{
    private String passwordActual;
    private String newPassword;
    private String confPassword;

    /**
     * 
     */
    public NewPasswordDTO() 
    {
        
    }

    /**
     * 
     * @return 
     */
    public String getPasswordActual()
    {
        return passwordActual;
    }

    /**
     * 
     * @param passwordActual 
     */
    public void setPasswordActual(String passwordActual)
    {
        this.passwordActual = passwordActual;
    }
    
    /**
     * 
     * @return 
     */
    public String getNewPassword() 
    {
        return newPassword;
    }

    /**
     * 
     * @param newPassword 
     */
    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
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
    
}
