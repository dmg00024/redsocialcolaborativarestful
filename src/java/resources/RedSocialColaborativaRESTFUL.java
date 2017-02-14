/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import bean.RedSocial;
import dto.ActualizarUsuarioDTO;
import dto.AmigosDTO;
import dto.GestionarPeticionDTO;
import dto.NewPasswordDTO;
import dto.NuevoUsuarioDTO;
import dto.PerfilDTO;
import dto.PeticionDTO;
import dto.SolicitarPeticionDTO;
import dto.ViaDTO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import modelo.PeticionAmistad;
import modelo.Usuario;
import modelo.Via;
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
        
        if(_newPasswordDTO.getNewPassword().length() < 6)
        {
            return new ResponseEntity<>("Password con menos de 6 caracteres", HttpStatus.BAD_REQUEST);
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
    @RequestMapping(value = "/perfil/amigos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<AmigosDTO> misAmigos()
    {
        List<AmigosDTO> amigos=new ArrayList();
        
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        for (Usuario amigo : red.getUsuarioConectado().getAmigos()) 
        {
            AmigosDTO aux=new AmigosDTO();
            aux.setUsername(amigo.getUsername());
            amigos.add(aux);
        }
        
        return amigos;
    }
    
    /**
     * 
     * @param _username
     * @return 
     */
    @RequestMapping(value = "/perfil/{username}/amigos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<AmigosDTO> amigosPerfil(@PathVariable ("username") String _username)
    {
        List<AmigosDTO> amigos=new ArrayList();
        
        red.setUsername(_username);
        
        for (Usuario amigo : red.getUsuarioConectado().getAmigos()) 
        {
            AmigosDTO aux=new AmigosDTO();
            aux.setUsername(amigo.getUsername());
            amigos.add(aux);
        }
        
        return amigos;
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/perfil/vias", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ViaDTO> misVias()
    {
        List<ViaDTO> vias=new ArrayList();
        
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        for (Via via : red.getUsuarioConectado().getViasRealizadas()) 
        {
            ViaDTO aux=new ViaDTO(via.getId_via(), via.getNombre(), via.getSector().getNombreSector(), via.getSector().getEscuela().getNombreEscuela());
            vias.add(aux);
        }
        
        return vias;
    }
    
    /**
     * 
     * @param _usernameConectado
     * @return 
     */
    @RequestMapping(value = "/perfil/{username}/vias", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ViaDTO> viasPerfil(@PathVariable("username") String _usernameConectado)
    {
        List<ViaDTO> vias=new ArrayList();
        
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        for (Via via : red.getUsuarioConectado().getViasRealizadas()) 
        {
            ViaDTO aux=new ViaDTO(via.getId_via(), via.getNombre(), via.getSector().getNombreSector(), via.getSector().getEscuela().getNombreEscuela());
            vias.add(aux);
        }
        
        return vias;
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value = "perfil/peticiones", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PeticionDTO> peticionesAmistadPendientes()
    {
        List<PeticionDTO> peticiones=new ArrayList();
        
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        for (PeticionAmistad peticion : red.peticionesAmistadRecibidas())
        {
            PeticionDTO aux=new PeticionDTO(peticion.getId_peticion(), peticion.getEmisor().getUsername());
            
            peticiones.add(aux);
        }
        
        return peticiones;
    }
    
    /**
     * 
     * @param _usuario 
     */
    @RequestMapping(value = "/perfil/peticiones", method = RequestMethod.POST, consumes = "application/json")
    public void solicitarAmistad(@RequestBody SolicitarPeticionDTO _usuario)
    {
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        try
        {
            red.enviarPeticionAmistad(_usuario.getUsername());
        }
        catch(exceptionsBusiness.PeticionYaEnviada ex)
        {
            throw new exceptionsBusiness.PeticionYaEnviada();
        }
        
    }
    
    /**
     * 
     * @param _peticion 
     */
    @RequestMapping(value = "/perfil/peticiones", method = RequestMethod.PUT, consumes = "application/json")
    public void confirmarAmistad(@RequestBody GestionarPeticionDTO _peticion)
    {
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        red.confirmarPeticion(_peticion.getId_peticion(), _peticion.isConf());
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
