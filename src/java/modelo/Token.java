/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Daniel
 */
@Entity
public class Token implements Serializable
{
    @Id
    private String token;
    @Temporal(javax.persistence.TemporalType.DATE)
    private final Calendar fecha;
    @Temporal(javax.persistence.TemporalType.TIME)
    private final Date hora;
    private String username;
    private String email;
    private String password;
    

    /**
     * 
     */
    public Token() 
    {
        token=UUID.randomUUID().toString().toUpperCase();
        fecha=Calendar.getInstance();
        hora=Calendar.getInstance().getTime();
    }

    /**
     * 
     * @param _username
     * @param _email
     * @param _password 
     */
    public Token(String _username, String _email, String _password) 
    {
        token=UUID.randomUUID().toString().toUpperCase();
        fecha=Calendar.getInstance();
        hora=Calendar.getInstance().getTime();
        username=_username;
        email=_email;
        password=_password;
    }

    /**
     * 
     * @return 
     */
    public String getToken() 
    {
        return token;
    }

    /**
     * 
     * @param token 
     */
    public void setToken(String token) 
    {
        this.token = token;
    }

    /**
     * 
     * @return 
     */
    public Calendar getFecha()
    {
        return fecha;
    }

    /**
     * 
     * @return 
     */
    public Date getHora()
    {
        return hora;
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
    
}
