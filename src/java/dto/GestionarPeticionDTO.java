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
public class GestionarPeticionDTO 
{
    private Integer idPeticion;
    private boolean conf;

    /**
     * 
     */
    public GestionarPeticionDTO()
    {
        
    }

    /**
     * 
     * @param idPeticion
     * @param conf 
     */
    public GestionarPeticionDTO(Integer idPeticion, boolean conf) 
    {
        this.idPeticion = idPeticion;
        this.conf = conf;
    }

    /**
     * 
     * @return 
     */
    public Integer getIdPeticion() 
    {
        return idPeticion;
    }

    /**
     * 
     * @param idPeticion 
     */
    public void setIdPeticion(Integer idPeticion) 
    {
        this.idPeticion = idPeticion;
    }

    /**
     * 
     * @return 
     */
    public boolean isConf() 
    {
        return conf;
    }

    /**
     * 
     * @param conf 
     */
    public void setConf(boolean conf) 
    {
        this.conf = conf;
    }
    
}
