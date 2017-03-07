/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
    private String email;

    public Token() 
    {
        fecha=Calendar.getInstance();
        hora=Calendar.getInstance().getTime();
    }

    /**
     * 
     * @param _email
     */
    public Token(String _email) 
    {
        fecha=Calendar.getInstance();
        hora=Calendar.getInstance().getTime();
        email=_email;
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
