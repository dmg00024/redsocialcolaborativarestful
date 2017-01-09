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
public class UsernameNoDisponible extends RuntimeException
{
    /**
     * 
     * @return 
     */
    @Override
    public String getMessage()
    {
        return "El nombre de usuario ya es utilizado por otro usuario";
    }
    
}
