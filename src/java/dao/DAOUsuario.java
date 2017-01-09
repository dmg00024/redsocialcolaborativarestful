/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class DAOUsuario 
{
    @PersistenceContext
    EntityManager em;
    
    /**
     * 
     */
    public DAOUsuario(){}
    
    /**
     * 
     * @param _usuario 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.GuardarUsuarioException.class)
    public void guardarUsuario(Usuario _usuario)
    {
        em.persist(_usuario);
    }
    
    /**
     * 
     * @param _usuario 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.ActualizarUsuarioException.class)
    public void actualizarUsuario(Usuario _usuario)
    {
        em.merge(_usuario);
    }
    
    /**
     * 
     * @param _usuario 
     * @brief elimina un usuario de la base de datos
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.BorrarUsuarioException.class)
    public void borrarUsuario(Usuario _usuario)
    {
        em.remove(em.merge(_usuario));
    }
    
    /**
     * 
     * @param _username
     * @return devuelve el usuario de la base de datos a partir de su _username
     */
    public Usuario obtenerUsuario(String _username)
    {
        return em.find(Usuario.class, _username);
    }
    
}
