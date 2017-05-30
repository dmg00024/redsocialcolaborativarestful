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
    private Integer puntuacion;
    private String valoracion;

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
     * @param valor_comentario
     * @param puntuacion
     * @param valoracion 
     */
    public ComentarioDTO(String valor_comentario, Integer puntuacion, String valoracion) 
    {
        this.valor_comentario = valor_comentario;
        this.puntuacion = puntuacion;
        this.valoracion = valoracion;
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
    public Integer getPuntuacion()
    {
        return puntuacion;
    }

    /**
     * 
     * @param puntuacion 
     */
    public void setPuntuacion(Integer puntuacion) 
    {
        this.puntuacion = puntuacion;
    }
    
    /**
     * 
     * @return 
     */
    public String getValoracion() 
    {
        return valoracion;
    }

    /**
     * 
     * @param valoracion 
     */
    public void setValoracion(String valoracion) 
    {
        this.valoracion = valoracion;
    }
    
}
