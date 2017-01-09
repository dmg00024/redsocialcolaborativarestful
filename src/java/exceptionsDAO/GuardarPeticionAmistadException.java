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
public class GuardarPeticionAmistadException extends RuntimeException
{
    /**
     * 
     * @return 
     */
    @Override
    public String getMessage()
    {
        return "Error al al guardar peticion de amistad la base de datos";
    }
    
}
