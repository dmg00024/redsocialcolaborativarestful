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
public class EscuelasDTO 
{
    private Integer id_escuela;
    private String nombre;
    private String descripcion;
    private byte[] foto;
    private String horario;
    private String provincia;

    /**
     * 
     */
    public EscuelasDTO() 
    {
        
    }

    /**
     * 
     * @param id_escuela
     * @param nombre
     * @param descripcion
     * @param foto
     * @param horario 
     */
    public EscuelasDTO(Integer id_escuela, String nombre, String descripcion, byte[] foto, String horario) 
    {
        this.id_escuela = id_escuela;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.horario = horario;
    }

    /**
     * 
     * @return 
     */
    public Integer getId_escuela() 
    {
        return id_escuela;
    }

    /**
     * 
     * @param id_escuela 
     */
    public void setId_escuela(Integer id_escuela)
    {
        this.id_escuela = id_escuela;
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
    public byte[] getFoto() 
    {
        return foto;
    }

    /**
     * 
     * @param foto 
     */
    public void setFoto(byte[] foto) 
    {
        this.foto = foto;
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
    public String getProvincia() 
    {
        return provincia;
    }

    /**
     * 
     * @param provincia 
     */
    public void setProvincia(String provincia) 
    {
        this.provincia = provincia;
    }
        
}
