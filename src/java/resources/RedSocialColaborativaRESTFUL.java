/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import bean.RedSocial;
import dto.ActualizarUsuarioDTO;
import dto.AmigosDTO;
import dto.ComentarioDTO;
import dto.ComentariosDTO;
import dto.EscuelaDTO;
import dto.EscuelasDTO;
import dto.GestionarPeticionDTO;
import dto.NewPasswordDTO;
import dto.NuevaViaDTO;
import dto.NuevoUsuarioDTO;
import dto.PerfilDTO;
import dto.PeticionDTO;
import dto.SectorDTO;
import dto.SectoresDTO;
import dto.UsernameDTO;
import dto.ViaDTO;
import dto.ViasDTO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import modelo.Comentario;
import modelo.Escuela;
import modelo.Nivel;
import modelo.PeticionAmistad;
import modelo.Sector;
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
        
        String nivel=null;
        
        if(red.getUsuarioConectado().getNivel().getNivelAsociado()==Nivel.nivelAsociado._1)
        {
            nivel="1";
        }
        
        return new PerfilDTO(red.getUsuarioConectado().getUsername(), red.getUsuarioConectado().getNombre(), red.getUsuarioConectado().getApellidos(), nivel, red.getUsuarioConectado().getFotoperfil());
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
    public void solicitarAmistad(@RequestBody UsernameDTO _usuario)
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
        
        red.confirmarPeticion(_peticion.getIdPeticion(), _peticion.isConf());
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value = "/username", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<?> prueba2()
    {
        UsernameDTO aux=new UsernameDTO();
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        if(usernameConectado==null)
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
        aux.setUsername(usernameConectado);
        
        return new ResponseEntity<>(aux, HttpStatus.OK);
    }
    
    /**
     * 
     * @param _escuela 
     * @throws java.io.IOException 
     */
    @RequestMapping(value = "/escuelas", method = RequestMethod.POST, consumes = "application/json")
    public void nuevaEscuela(@RequestBody EscuelaDTO _escuela) throws IOException
    {
        red.nuevaEscuela(_escuela.getNombre(), _escuela.getDescripcion(), _escuela.getHorario(), _escuela.getDir_foto(), _escuela.getCod_provincia());
    }
    
    /**
     * 
     * @param _id_escuela
     * @param _sector 
     * @throws java.io.IOException 
     */
    @RequestMapping(value = "/escuelas/{id_escuela}/sectores", method = RequestMethod.POST, consumes = "application/json")
    public void nuevoSector(@PathVariable ("id_escuela") Integer _id_escuela, @RequestBody SectorDTO _sector) throws IOException
    {
        red.nuevoSector(_id_escuela, _sector.getOrientacion(), _sector.getNombre(), _sector.getDir_foto());
    }
    
    /**
     * 
     * @param _id_sector
     * @param _via 
     */
    @RequestMapping(value = "/sectores/{id_sector}/vias", method = RequestMethod.POST, consumes = "application/json")
    public void nuevaVia(@PathVariable ("id_sector") Integer _id_sector, @RequestBody NuevaViaDTO _via)
    {
        red.nuevaVia(_id_sector, _via.getNombre(), _via.getNivel_oficial(), _via.getId_mapa());
    }
    
    /**
     * 
     * @param _cod_provincia
     * @return 
     */
    @RequestMapping(value = "/escuelas/{cod_prov}", method = RequestMethod.GET, produces = "application/json")
    public List<EscuelasDTO> escuelasProv(@PathVariable("cod_prov") Integer _cod_provincia)
    {
        List<EscuelasDTO> aux_list=new ArrayList();
        
        for (Escuela escuela : red.escuelasProvincia(_cod_provincia))
        {
            EscuelasDTO aux=new EscuelasDTO();
            aux.setId_escuela(escuela.getId_escuela());
            aux.setNombre(escuela.getNombreEscuela());
            aux.setDescripcion(escuela.getDescripcion());
            aux.setFoto(escuela.getFotoEscuela());
            aux.setHorario(escuela.getHorario());
            
            aux_list.add(aux);
        }
        
        return aux_list;
    }
    
    /**
     * 
     * @param _cod_escuela
     * @return 
     */
    @RequestMapping(value = "/sectores/{cod_escuela}", method = RequestMethod.GET, produces = "application/json")
    public List<SectoresDTO> sectoresEsc(@PathVariable("cod_escuela") Integer _cod_escuela)
    {
        List<SectoresDTO> aux_list=new ArrayList();
        
        for (Sector sector : red.sectoresEscuela(_cod_escuela)) 
        {
            SectoresDTO aux=new SectoresDTO();
            
            aux.setId_sector(sector.getId_sector());
            aux.setNombre(sector.getNombreSector());
            aux.setOrientacion(sector.getOrientacion().getOrientacion().name());
            aux.setFoto(sector.getFotoSector());
            
            aux_list.add(aux);
        }
        
        return aux_list;
    }
    
    /**
     * 
     * @param _cod_sector
     * @return 
     */
    @RequestMapping(value = "/vias/{cod_sector}", method = RequestMethod.GET, produces = "application/json")
    public List<ViasDTO> viasSector(@PathVariable("cod_sector") Integer _cod_sector)
    {
        List<ViasDTO> vias=new ArrayList();
        
        for (Via via : red.viasSector(_cod_sector)) 
        {
            ViasDTO aux=new ViasDTO();
            
            aux.setId_via(via.getId_via());
            aux.setId_mapa(via.getIdv_via());
            aux.setNombre(via.getNombre());
            aux.setNivel_oficial(via.getNivel().getNivelAsociado().name());
            aux.setNivel_consensuado(via.getNivelConsensuado().getNivelAsociado().name());
            
            vias.add(aux);
        }
        
        return vias;
    }
    
    /**
     * 
     * @param _cod_via
     * @param _comentario 
     */
    @RequestMapping(value = "/comentarios/{cod_via}", method = RequestMethod.POST, produces = "application/json")
    public void add_comentario_via(@PathVariable("cod_via") Integer _cod_via, @RequestBody ComentarioDTO _comentario)
    {
        String usernameConectado=null;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(principal instanceof UserDetails)
        {
            usernameConectado=((UserDetails) principal).getUsername();
        }
        
        red.setUsername(usernameConectado);
        
        red.comentarVia(_cod_via, _comentario.getValor_comentario());
    }
    
    /**
     * 
     * @param _cod_via
     * @return 
     */
    @RequestMapping(value = "/comentarios/{cod_via}", method = RequestMethod.GET, produces = "application/json")
    public List<ComentariosDTO> comentarios_via(@PathVariable("cod_via") Integer _cod_via)
    {
        List<ComentariosDTO> comentarios=new ArrayList();
        
        for (Comentario comentario : red.comentariosVia(_cod_via)) 
        {
            ComentariosDTO aux=new ComentariosDTO();
            
            aux.setUsername(comentario.getUsuario().getUsername());
            aux.setValor_comentario(comentario.getComentario());
            
            comentarios.add(aux);
        }
        
        return comentarios;
    }
    
}
