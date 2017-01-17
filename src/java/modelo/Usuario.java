/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Daniel
 */
@Entity
public class Usuario implements Serializable
{
    @Id
    private String username;
    @Lob
    private byte[] fotoperfil;
    private String nombre;
    private String apellidos;
    @ManyToOne (fetch = FetchType.EAGER)
    private Nivel nivel;
    private String email;
    private String password;
    private final String role;
    @ManyToMany (fetch = FetchType.LAZY)
    private final List<Usuario> amigos;
    @ManyToMany (fetch = FetchType.LAZY)
    private final List<Via> viasRealizadas;
    @ManyToMany (fetch = FetchType.LAZY)
    private final List<PeticionAmistad> peticionesAmistad;
    @OneToMany (fetch = FetchType.LAZY)
    private final List<Comentario> comentarios;

    /**
     * 
     */
    public Usuario()
    {
        amigos=new ArrayList();
        viasRealizadas=new ArrayList();
        peticionesAmistad=new ArrayList();
        comentarios=new ArrayList();
        role="ROLE_USER";
    }

    /**
     * 
     * @param username
     * @param password
     * @param email 
     */
    public Usuario(String username, String password, String email)
    {
        amigos=new ArrayList();
        viasRealizadas=new ArrayList();
        peticionesAmistad=new ArrayList();
        this.username = username;
        this.password = password;
        this.email=email;
        Nivel n=new Nivel(Nivel.nivelAsociado._1);
        this.nivel=n;
        comentarios=new ArrayList();
        role="ROLE_USER";
    }

    /**
     * 
     * @param nombre
     * @param apellidos
     * @param username
     * @param password 
     */
    public Usuario(String nombre, String apellidos, String username, String password)
    {
        amigos=new ArrayList();
        viasRealizadas=new ArrayList();
        peticionesAmistad=new ArrayList();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        Nivel n=new Nivel(Nivel.nivelAsociado._1);
        this.nivel=n;
        comentarios=new ArrayList();
        role="ROLE_USER";
    }

    /**
     * 
     * @param nombre
     * @param apellidos
     * @param nivel
     * @param username
     * @param password 
     */
    public Usuario(String nombre, String apellidos, Nivel nivel, String username, String password)
    {
        amigos=new ArrayList();
        viasRealizadas=new ArrayList();
        peticionesAmistad=new ArrayList();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nivel = nivel;
        this.username = username;
        this.password = password;
        comentarios=new ArrayList();
        role="ROLE_USER";
    }
    
    /**
     * 
     * @return 
     */
    public byte[] getFotoperfil() 
    {
        return fotoperfil;
    }

    /**
     * 
     * @param fotoperfil 
     */
    public void setFotoperfil(byte[] fotoperfil) 
    {
        this.fotoperfil = fotoperfil;
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
    public Nivel getNivel()
    {
        return nivel;
    }

    /**
     * 
     * @param nivel 
     */
    public void setNivel(Nivel nivel) 
    {
        this.nivel = nivel;
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
    public String getPassword()
    {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * 
     * @return 
     */
    public String getRole()
    {
        return role;
    }
    
    

    /**
     * 
     * @return 
     */
    public List<Usuario> getAmigos() 
    {
        return amigos;
    }

    /**
     * 
     * @return 
     */
    public List<Via> getViasRealizadas() 
    {
        return viasRealizadas;
    }

    /**
     * 
     * @return 
     */
    public List<PeticionAmistad> getPeticionesAmistad() 
    {
        return peticionesAmistad;
    }

    /**
     * 
     * @return 
     */
    public List<Comentario> getComentarios() 
    {
        return comentarios;
    }
    
}
