/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Orientacion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class DAOOrientacion 
{
    @PersistenceContext 
    EntityManager em;
    
    /**
     * 
     */
    public DAOOrientacion(){}
    
    /**
     * 
     * @param _o 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.GuardarOrientacionException.class)
    public void guardarOrientacion(Orientacion _o)
    {
        em.persist(_o);
    }
    
    /**
     * 
     * @param _orientacion
     * @return 
     */
    public Orientacion obtenerOrientacion(Orientacion.orientacion _orientacion)
    {
        return em.find(Orientacion.class, _orientacion);
    }
}
