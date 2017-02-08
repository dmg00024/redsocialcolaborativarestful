/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import bean.RedSocial;
import dto.ActualizarUsuarioDTO;
import dto.NewPasswordDTO;
import dto.NuevoUsuarioDTO;
import dto.PerfilDTO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @return  
     * @throws java.security.NoSuchAlgorithmException 
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> altaUsuario(@RequestBody NuevoUsuarioDTO _usuario) throws NoSuchAlgorithmException
    {   
        if(!_usuario.getMail().equals(_usuario.getConfMail()))
        {
            return new ResponseEntity<>("Email no confirmado",HttpStatus.BAD_REQUEST);
        }
        else if(!_usuario.getPassword().equals(_usuario.getConfPassword()))
        {
            return new ResponseEntity<>("Password no confirmado",HttpStatus.BAD_REQUEST);
        }
        
        try
        {
            red.altaUsuario(_usuario.getUsername(), _usuario.getPassword(), _usuario.getMail());
        }
        catch(RuntimeException ex)
        {
            throw new exceptionsBusiness.UsernameNoDisponible();
        }
        
        return null;
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
     * @param _usuario
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> actualizarUsuario(@RequestBody ActualizarUsuarioDTO _usuario) throws IOException
    {
        if(!_usuario.getMail().equals(_usuario.getConfMail()))
        {
            return new ResponseEntity<>("Email no confirmado",HttpStatus.BAD_REQUEST);
        }
        
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        red.actualizarPerfilUsuario(_usuario.getNombre(), _usuario.getApellidos(), _usuario.getMail(), _usuario.getDir_foto());
        
        return null;
    }
    
    /**
     * 
     * @param _newPasswordDTO
     * @return
     * @throws NoSuchAlgorithmException 
     */
    @RequestMapping(value = "/perfil/password", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> cambioPasswordUsuario(@RequestBody NewPasswordDTO _newPasswordDTO) throws NoSuchAlgorithmException
    {
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        if(!_newPasswordDTO.getNewPassword().equals(_newPasswordDTO.getConfPassword()))
        {
            return new ResponseEntity<>("Password no confirmado",HttpStatus.BAD_REQUEST);
        }
        
        red.setUsername(usernameConectado);
        
        red.cambiarPassword(_newPasswordDTO.getNewPassword());
        
        return null;
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody PerfilDTO miPerfil()
    {
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        return new PerfilDTO(red.getUsuarioConectado().getUsername(), red.getUsuarioConectado().getNombre(), red.getUsuarioConectado().getApellidos(), red.getUsuarioConectado().getNivel().getNivelAsociado().name(), red.getUsuarioConectado().getFotoperfil());
    }
    
    /**
     * 
     * @param _username
     * @return 
     */
    @RequestMapping(value = "/perfil/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody PerfilDTO verPerfil(@PathVariable("username") String _username)
    {
        red.setUsername(_username);
        
        return new PerfilDTO(red.getUsuarioConectado().getUsername(), red.getUsuarioConectado().getNombre(), red.getUsuarioConectado().getApellidos(), red.getUsuarioConectado().getNivel().getNivelAsociado().name(), red.getUsuarioConectado().getFotoperfil());
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
        prueba.setMail("probando");
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
