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
    private final String token;
    @Temporal(javax.persistence.TemporalType.DATE)
    private final Calendar fecha;
    @Temporal(javax.persistence.TemporalType.TIME)
    private final Date hora;
    
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
     * @return 
     */
    public String getToken() 
    {
        return token;
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
    
}
