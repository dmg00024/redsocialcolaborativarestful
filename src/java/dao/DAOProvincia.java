/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Provincia;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class DAOProvincia 
{
    @PersistenceContext
    EntityManager em;
    
    /**
     * 
     */
    public DAOProvincia(){}
    
    /**
     * 
     * @param _provincia 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.GuardarLugarException.class)
    public void guardarProvincia(Provincia _provincia)
    {
        em.persist(_provincia);
    }
   
    /**
     * 
     * @param _provincia 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.ActualizarLugarException.class)
    public void actualizarProvincia(Provincia _provincia)
    {
        em.merge(_provincia);
    }
    
    /**
     * 
     * @param _provincia 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.BorrarLugarException.class)
    public void borrarProvincia(Provincia _provincia)
    {
        em.remove(em.merge(_provincia));
    }
    
    /**
     * 
     * @param _cod_provincia
     * @return 
     */
    public Provincia obtenerProvincia(Integer _cod_provincia)
    {
        return em.find(Provincia.class, _cod_provincia);
    }
}
