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
public class ViasDTO 
{
    private Integer id_via;
    private String id_mapa;
    private String nombre;
    private String sector;
    private String nivel_oficial;
    private String nivel_consensuado;
    private Integer contador;

    /**
     * 
     */
    public ViasDTO() 
    {
        
    }

    /**
     * 
     * @param id_via
     * @param id_mapa
     * @param nombre
     * @param sector
     * @param nivel_oficial
     * @param nivel_consensuado 
     */
    public ViasDTO(Integer id_via, String id_mapa, String nombre, String sector, String nivel_oficial, String nivel_consensuado)
    {
        this.id_via = id_via;
        this.id_mapa = id_mapa;
        this.nombre = nombre;
        this.sector = sector;
        this.nivel_oficial = nivel_oficial;
        this.nivel_consensuado = nivel_consensuado;
    }

    /**
     * 
     * @return 
     */
    public String getId_mapa() 
    {
        return id_mapa;
    }

    /**
     * 
     * @param id_mapa 
     */
    public void setId_mapa(String id_mapa) 
    {
        this.id_mapa = id_mapa;
    }

    /**
     * 
     * @return 
     */
    public Integer getId_via() 
    {
        return id_via;
    }

    /**
     * 
     * @param id_via 
     */
    public void setId_via(Integer id_via)
    {
        this.id_via = id_via;
    }

    

    /**
     * 
     * @return 
     */
    public String getNombre() 
    {
        return nombre;
    }
    
    /**
     * 
     * @param nombre 
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * 
     * @return 
     */
    public String getSector() 
    {
        return sector;
    }

    /**
     * 
     * @param sector 
     */
    public void setSector(String sector) 
    {
        this.sector = sector;
    }

    /**
     * 
     * @return 
     */
    public String getNivel_oficial()
    {
        return nivel_oficial;
    }

    /**
     * 
     * @param nivel_oficial 
     */
    public void setNivel_oficial(String nivel_oficial) 
    {
        this.nivel_oficial = nivel_oficial;
    }

    /**
     * 
     * @return 
     */
    public String getNivel_consensuado() 
    {
        return nivel_consensuado;
    }

    /**
     * 
     * @param nivel_consensuado 
     */
    public void setNivel_consensuado(String nivel_consensuado) 
    {
        this.nivel_consensuado = nivel_consensuado;
    }

    /**
     * 
     * @return 
     */
    public Integer getContador() 
    {
        return contador;
    }

    /**
     * 
     * @param contador 
     */
    public void setContador(Integer contador)
    {
        this.contador = contador;
    }
    
}
