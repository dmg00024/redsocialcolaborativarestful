/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Sector;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class DAOSector
{
    @PersistenceContext
    EntityManager em;
    
    /**
     * 
     */
    public DAOSector(){}
    
    /**
     * 
     * @param _sector 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.GuardarSectorException.class)
    public void guardarSector(Sector _sector)
    {
        em.persist(_sector);
    }
    
    /**
     * 
     * @param _sector 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.ActualizarSectorException.class)
    public void actualizarSector(Sector _sector)
    {
        em.merge(_sector);
    }
    
    /**
     * 
     * @param _sector 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.BorrarSectorException.class)
    public void borrarSector(Sector _sector)
    {
        em.remove(em.merge(_sector));
    }
    
    /**
     * 
     * @param _id_sector
     * @return 
     */
    public Sector obtenerSector(Integer _id_sector)
    {
        return em.find(Sector.class, _id_sector);
    }
    
}
