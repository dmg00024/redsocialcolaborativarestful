/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import bean.RedSocial;
import dto.NuevoUsuarioDTO;
import java.security.NoSuchAlgorithmException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.PUT, consumes = "application/json")
    public void actualizarUsuario(@RequestBody NuevoUsuarioDTO _usuario)
    {
        
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Usuario obtenerUsuario()
    {
        return red.getUsuarioConectado();
    }
    
    /**
     * 
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.DELETE)
    public void bajaUsuario()
    {     
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
