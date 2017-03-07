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
public class EscuelaDTO 
{
    private String nombre;
    private String descripcion;
    private String dir_foto;
    private String horario;
    private Integer cod_provincia;

    /**
     * 
     */
    public EscuelaDTO() 
    {
        
    }

    /**
     * 
     * @param nombre
     * @param descripcion
     * @param dir_foto
     * @param horario
     * @param cod_provincia 
     */
    public EscuelaDTO(String nombre, String descripcion, String dir_foto, String horario, Integer cod_provincia) 
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dir_foto = dir_foto;
        this.horario = horario;
        this.cod_provincia = cod_provincia;
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
    public String getDescripcion() 
    {
        return descripcion;
    }

    /**
     * 
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) 
    {
        this.descripcion = descripcion;
    }

    /**
     * 
     * @return 
     */
    public String getDir_foto() 
    {
        return dir_foto;
    }

    /**
     * 
     * @param dir_foto 
     */
    public void setDir_foto(String dir_foto) 
    {
        this.dir_foto = dir_foto;
    }

    /**
     * 
     * @return 
     */
    public String getHorario() 
    {
        return horario;
    }

    /**
     * 
     * @param horario 
     */
    public void setHorario(String horario)
    {
        this.horario = horario;
    }

    /**
     * 
     * @return 
     */
    public Integer getCod_provincia() 
    {
        return cod_provincia;
    }

    /**
     * 
     * @param cod_provincia 
     */
    public void setCod_provincia(Integer cod_provincia)
    {
        this.cod_provincia = cod_provincia;
    }
    
}
