/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Comentario;
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
public class DAOComentario
{
    @PersistenceContext
    EntityManager em;
    
    /**
     * 
     */
    public DAOComentario(){}
    
    /**
     * 
     * @param _comentario 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.GuardarComentarioException.class)
    public void guardarComentario(Comentario _comentario)
    {
        em.persist(_comentario);
    }
    
    /**
     * 
     * @param _comentario 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.ActualizarComentarioException.class)
    public void actualizarComentario(Comentario _comentario)
    {
        em.merge(_comentario);
    }
    
    /**
     * 
     * @param _comentario 
     */
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = exceptionsDAO.BorrarComentarioException.class)
    public void borrarComentario(Comentario _comentario)
    {
        em.remove(em.merge(_comentario));
    }
    
    /**
     * 
     * @param _id_comentario
     * @return 
     */
    public Comentario obtenerComentario(Integer _id_comentario)
    {
        return em.find(Comentario.class, _id_comentario);
    }
    
    /**
     * 
     * @param _u
     * @return 
     */
    public List<Comentario> comentariosUsuario(Usuario _u)
    {
        return em.createQuery("SELECT C FROM Comentario C WHERE C.usuario = ?1").setParameter(1, _u).getResultList();
    }
    
}
