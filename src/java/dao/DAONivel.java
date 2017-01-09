/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Nivel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class DAONivel 
{
    @PersistenceContext
    EntityManager em;
    
    /**
     * 
     */
    public DAONivel(){}
    
    /**
     * 
     * @param _nivel 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.GuardarNivelException.class)
    public void guardarNivel(Nivel _nivel)
    { 
        em.persist(_nivel);
    }
    
    /**
     * 
     * @param _nivel 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.BorrarNivelException.class)
    public void borrarNivel(Nivel _nivel)
    {
        em.remove(em.merge(_nivel));
    }
    
    /**
     * 
     * @param _nivelAsociado
     * @return 
     */
    public Nivel obtenerNivel(Nivel.nivelAsociado _nivelAsociado)
    {
        return em.find(Nivel.class, _nivelAsociado);
    }
}
