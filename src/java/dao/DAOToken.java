/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Token;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class DAOToken 
{
    @PersistenceContext
    EntityManager em;
    
    /**
     * 
     */
    public DAOToken()
    {
        
    }
    
    /**
     * 
     * @param _token
     * @return 
     */
    public Token obtenerToken(String _token)
    {
        return em.find(Token.class, _token);
    }
    
    /**
     * 
     * @param _token 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.GuardarTokenException.class)
    public void guardarToken(Token _token)
    {
        em.persist(_token);
    }
    
    /**
     * 
     * @param _token 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.BorrarTokenException.class)
    public void borrarToken(Token _token)
    {
        em.remove(em.merge(_token));
    }
}
