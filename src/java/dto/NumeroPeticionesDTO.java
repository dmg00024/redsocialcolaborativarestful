/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Daniel
 */
public class NumeroPeticionesDTO 
{
    private Integer numeroPendientes;

    /**
     * 
     */
    public NumeroPeticionesDTO() 
    {
    }

    /**
     * 
     * @param numeroPendientes 
     */
    public NumeroPeticionesDTO(Integer numeroPendientes)
    {
        this.numeroPendientes = numeroPendientes;
    }

    /**
     * 
     * @return 
     */
    public Integer getNumeroPendientes() 
    {
        return numeroPendientes;
    }

    /**
     * 
     * @param numeroPendientes 
     */
    public void setNumeroPendientes(Integer numeroPendientes)
    {
        this.numeroPendientes = numeroPendientes;
    }
    
}
