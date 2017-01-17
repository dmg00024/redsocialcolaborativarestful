/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import bean.RedSocial;
import dto.UsuarioDTO;
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
     */
    @RequestMapping(value = "/registro", method = RequestMethod.POST, consumes = "application/json")
    public void altaUsuario(@RequestBody UsuarioDTO _usuario)
    {
        try
        {
            red.altaUsuario(_usuario.getUsername(), _usuario.getPassword(), _usuario.getEmail());
        }
        catch(Exception e)
        {
            throw new exceptionsBusiness.UsernameNoDisponible();
        }
    }
    
    /**
     * 
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.DELETE, produces = "application/json")
    public void bajaUsuario()
    {     
        red.bajaUsuario();
    }
    
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/prueba", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody UsuarioDTO prueba()
    {
        UsuarioDTO prueba=new UsuarioDTO();
        
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
