/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Via;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class DAOVia 
{
    @PersistenceContext
    EntityManager em;
    
    /**
     * 
     */
    public DAOVia(){}
    
    /**
     * 
     * @param _via 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.GuardarViaException.class)
    public void guardarVia(Via _via)
    {
        em.persist(_via);
    }
    
    /**
     * 
     * @param _via 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.ActualizarViaException.class)
    public void actualizarVia(Via _via)
    {
        em.merge(_via);
    }
    
    /**
     * 
     * @param _via 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.BorrarViaException.class)
    public void borrarVia(Via _via)
    {
        em.remove(em.merge(_via));
    }
    
    /**
     * 
     * @param _id_via
     * @return 
     */
    public Via obtenerVia(Integer _id_via)
    {
        return em.find(Via.class, _id_via);
    }
    
}
