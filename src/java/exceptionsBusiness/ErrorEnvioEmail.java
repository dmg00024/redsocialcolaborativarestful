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
public class ErrorEnvioEmail extends RuntimeException
{
    /**
     * 
     * @return 
     */
    @Override
    public String getMessage() 
    {
        return "Error de envío de email";
    }
    
}
