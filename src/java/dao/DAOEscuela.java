/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Escuela;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class DAOEscuela 
{
    @PersistenceContext
    EntityManager em;
    
    /**
     * 
     */
    public DAOEscuela(){}
    
    /**
     * 
     * @param _escuela 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.GuardarEscuelaException.class)
    public void guardarEscuela(Escuela _escuela)
    {
        em.persist(_escuela);
    }
    
    /**
     * 
     * @param _escuela 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.ActualizarEscuelaException.class)
    public void actualizarEscuela(Escuela _escuela)
    {
        em.merge(_escuela);
    }
    
    /**
     * 
     * @param _escuela 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.BorrarEscuelaException.class)
    public void borrarEscuela(Escuela _escuela)
    {
        em.remove(em.merge(_escuela));
    }
    
    /**
     * 
     * @param _id_escuela
     * @return 
     */
    public Escuela obtenerEscuela(Integer _id_escuela)
    {
        return em.find(Escuela.class, _id_escuela);
    }
    
    /**
     * 
     * @param _cod_provincia
     * @return 
     */
    public List<Escuela> obtenerEscuelasProvincia(Integer _cod_provincia)
    {
        return em.createQuery("SELECT E FROM Escuela E WHERE E.ubicacion.cod_provincia = ?1").setParameter(1, _cod_provincia).getResultList();
    }
}
