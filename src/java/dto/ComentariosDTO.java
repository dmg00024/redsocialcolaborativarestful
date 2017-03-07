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
public class ComentariosDTO 
{
    private String valor_comentario;
    private String username;

    /**
     * 
     */
    public ComentariosDTO() 
    {
        
    }

    /**
     * 
     * @param valor_comentario
     * @param username 
     */
    public ComentariosDTO(String valor_comentario, String username) 
    {
        this.valor_comentario = valor_comentario;
        this.username = username;
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

    /**
     * 
     * @return 
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * 
     * @param username 
     */
    public void setUsername(String username) 
    {
        this.username = username;
    }
    
}
