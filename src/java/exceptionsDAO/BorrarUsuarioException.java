/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionsDAO;

/**
 *
 * @author Daniel
 */
public class BorrarUsuarioException extends RuntimeException 
{
    /**
     * 
     * @return 
     */
    @Override
    public String getMessage() 
    {
        return "Error al eliminar usuario de la base de datos";
    }
    
}
