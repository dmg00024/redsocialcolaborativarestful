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
public class PerfilDTO 
{
    private String username;
    private String nombre;
    private String apellidos;
    private String nivel;
    private byte[] foto;

    /**
     * 
     */
    public PerfilDTO() 
    {
        
    }

    /**
     * 
     * @param username
     * @param nombre
     * @param apellidos
     * @param nivel
     * @param foto 
     */
    public PerfilDTO(String username, String nombre, String apellidos, String nivel, byte[] foto)
    {
        this.username = username;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nivel = nivel;
        this.foto = foto;
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
    public String getApellidos() 
    {
        return apellidos;
    }

    /**
     * 
     * @param apellidos 
     */
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
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
    
}
