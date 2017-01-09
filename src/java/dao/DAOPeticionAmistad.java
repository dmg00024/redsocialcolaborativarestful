/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.PeticionAmistad;
import modelo.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Repository
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class DAOPeticionAmistad
{
    @PersistenceContext
    EntityManager em;
    
    /**
     * 
     */
    public DAOPeticionAmistad(){}
    
    /**
     * 
     * @param _peticion 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.GuardarPeticionAmistadException.class)
    public void guardarPeticionAmistad(PeticionAmistad _peticion)
    {
        em.persist(_peticion);
    }
    
    /**
     * 
     * @param _peticion 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.ActualizarPeticionAmistadException.class)
    public void actualizarPeticionAmistad(PeticionAmistad _peticion)
    {
        em.merge(_peticion);
    }
    
    /**
     * 
     * @param _peticion 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.BorrarPeticionAmistadException.class)
    public void borrarPeticionAmistad(PeticionAmistad _peticion)
    {
        em.remove(em.merge(_peticion));
    }
    
    /**
     * 
     * @param _id_peticion
     * @return 
     */
    public PeticionAmistad obtenerPeticionAmistad(Integer _id_peticion)
    {
        return em.find(PeticionAmistad.class, _id_peticion);
    }
    
    /**
     * 
     * @param _emisor
     * @param _receptor
     * @return comprueba si existe peticion de amistad en la base de datos
     */
    public boolean comprobarPeticionAmistad(Usuario _emisor, Usuario _receptor)
    {
        return em.createQuery("SELECT P FROM PeticionAmistad P WHERE P.emisor = ?1 AND P.receptor = ?2").setParameter(1, _emisor).setParameter(2, _receptor).getResultList().isEmpty();
    }
}
