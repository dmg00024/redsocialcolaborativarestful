/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionsBusiness;

/**
 *
 * @author Daniel
 */
public class ComentarioNoDisponible extends RuntimeException
{
    /**
     * 
     * @return 
     */
    @Override
    public String getMessage() 
    {
        return "No puedes editar o eliminar un comentario no publicado por ti";
    }
    
}
