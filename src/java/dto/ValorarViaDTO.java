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
public class ValorarViaDTO 
{
    private String nivel;
    private Integer valoracion;

    /**
     * 
     */
    public ValorarViaDTO() 
    {
        
    }

    /**
     * 
     * @return 
     */
    public String getNivel() 
    {
        return nivel;
    }

    /**
     * 
     * @param nivel 
     */
    public void setNivel(String nivel) 
    {
        this.nivel = nivel;
    }
    
    /**
     * 
     * @return 
     */
    public Integer getValoracion() 
    {
        return valoracion;
    }

    /**
     * 
     * @param valoracion 
     */
    public void setValoracion(Integer valoracion) 
    {
        this.valoracion = valoracion;
    }
    
}
