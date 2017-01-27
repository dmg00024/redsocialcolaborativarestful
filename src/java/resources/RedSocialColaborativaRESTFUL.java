/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import bean.RedSocial;
import dto.ActualizarUsuarioDTO;
import dto.NuevoUsuarioDTO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.websocket.server.PathParam;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniel
 */
@RestController
@RequestMapping("/")
public class RedSocialColaborativaRESTFUL
{
    @Autowired
    private RedSocial red;
    
    /**
     * 
     * @param _usuario 
     * @throws java.security.NoSuchAlgorithmException 
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.POST, consumes = "application/json")
    public void altaUsuario(@RequestBody NuevoUsuarioDTO _usuario) throws NoSuchAlgorithmException
    {
        try
        {
            red.altaUsuario(_usuario.getUsername(), _usuario.getPassword(), _usuario.getEmail());
        }
        catch(exceptionsBusiness.UsernameNoDisponible ex)
        {
            throw new exceptionsBusiness.UsernameNoDisponible();
        }
    }
    
    /**
     * 
     * @param _usuario
     * @throws IOException 
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.PUT, consumes = "application/json")
    public void actualizarUsuario(@RequestBody ActualizarUsuarioDTO _usuario) throws IOException
    {
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        red.actualizarPerfilUsuario(_usuario.getNombre(), _usuario.getApellidos(), _usuario.getEmail(), _usuario.getDir_foto());
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Usuario miPerfil()
    {
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        return red.getUsuarioConectado();
    }
    
    /**
     * 
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.DELETE)
    public void bajaUsuario()
    {    
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        red.bajaUsuario();
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/prueba", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody NuevoUsuarioDTO prueba()
    {
        NuevoUsuarioDTO prueba=new NuevoUsuarioDTO();
        
        prueba.setUsername("probando");
        prueba.setEmail("probando");
        prueba.setPassword("probando");
        
        return prueba;
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/prueba2", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String prueba2()
    {
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        return usernameConectado;
    }
    
}
