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
public class ComprobarAmigoDTO 
{
    private boolean esAmigo;

    /**
     * 
     * @param esAmigo 
     */
    public ComprobarAmigoDTO(boolean esAmigo) 
    {
        this.esAmigo = esAmigo;
    }

    /**
     * 
     * @return 
     */
    public boolean isEsAmigo() 
    {
        return esAmigo;
    }

    /**
     * 
     * @param esAmigo 
     */
    public void setEsAmigo(boolean esAmigo)
    {
        this.esAmigo = esAmigo;
    }
    
}
