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
    private Integer id_peticion;
    private boolean conf;

    /**
     * 
     */
    public GestionarPeticionDTO()
    {
        
    }

    /**
     * 
     * @param id_peticion
     * @param conf 
     */
    public GestionarPeticionDTO(Integer id_peticion, boolean conf) 
    {
        this.id_peticion = id_peticion;
        this.conf = conf;
    }

    /**
     * 
     * @return 
     */
    public Integer getId_peticion()
    {
        return id_peticion;
    }

    /**
     * 
     * @param id_peticion 
     */
    public void setId_peticion(Integer id_peticion)
    {
        this.id_peticion = id_peticion;
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
