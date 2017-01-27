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
public class ActualizarUsuarioDTO 
{
    private String nombre;
    private String apellidos;
    private String email;
    private String dir_foto;

    /**
     * 
     */
    public ActualizarUsuarioDTO() {}

    /**
     * 
     * @param nombre
     * @param apellidos
     * @param email
     * @param dir_foto 
     */
    public ActualizarUsuarioDTO(String nombre, String apellidos, String email, String dir_foto) 
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.dir_foto = dir_foto;
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
    public String getEmail()
    {
        return email;
    }

    /**
     * 
     * @param email 
     */
    public void setEmail(String email)
    {
        this.email = email;
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
    
}
