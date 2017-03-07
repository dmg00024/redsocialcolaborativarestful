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
public class ComentarioDTO 
{
    private String valor_comentario;

    /**
     * 
     */
    public ComentarioDTO() 
    {
        
    }

    /**
     * 
     * @param valor_comentario 
     */
    public ComentarioDTO(String valor_comentario)
    {
        this.valor_comentario = valor_comentario;
    }

    /**
     * 
     * @return 
     */
    public String getValor_comentario()
    {
        return valor_comentario;
    }
    
    /**
     * 
     * @param valor_comentario 
     */
    public void setValor_comentario(String valor_comentario)
    {
        this.valor_comentario = valor_comentario;
    }
    
}
