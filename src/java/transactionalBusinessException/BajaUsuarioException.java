/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactionalBusinessException;

/**
 *
 * @author Daniel
 */
public class BajaUsuarioException extends RuntimeException
{
    /**
     * 
     * @return 
     */
    @Override
    public String getMessage() 
    {
        return "Fallo transaccional en bajaUsuario";
    }
}
