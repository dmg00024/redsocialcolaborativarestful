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
import dto.NumeroPeticionesDTO;
import dto.PerfilDTO;
import dto.PeticionDTO;
import dto.ProvinciaDTO;
import dto.SectorDTO;
import dto.SectoresDTO;
import dto.UsernameDTO;
import dto.ValorarViaDTO;
import dto.ViasDTO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import modelo.Comentario;
import modelo.Escuela;
import modelo.Nivel;
import modelo.PeticionAmistad;
import modelo.Provincia;
import modelo.Sector;
import modelo.Usuario;
import modelo.Via;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class RedSocialColaborativaRESTFUL {

    @Autowired
    private RedSocial red;

    /**
     *
     * @param _usuario
     * @return
     */
    @RequestMapping(value = "/perfil/acceso", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> solicitudAcceso(@RequestBody NuevoUsuarioDTO _usuario) {
        if (!_usuario.getMail().equals(_usuario.getConfMail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!_usuario.getPassword().equals(_usuario.getConfPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            red.solicitarAcceso(_usuario.getUsername(), _usuario.getMail(), _usuario.getPassword());
        } catch (RuntimeException e) {
            //codigo 409
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param _token
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/confirmacion/{token}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> altaUsuario(@PathVariable("token") String _token) throws NoSuchAlgorithmException {
        try {
            red.altaUsuario(_token);
        } catch (RuntimeException ex) {
            throw new exceptionsBusiness.TokenCaducado();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.DELETE)
    public void bajaUsuario() {
        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
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
    public ResponseEntity<String> actualizarUsuario(@RequestBody ActualizarUsuarioDTO _usuario) throws IOException {
        if (!_usuario.getMail().equals(_usuario.getConfMail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
        }

        red.setUsername(usernameConectado);

        red.actualizarPerfilUsuario(_usuario.getNombre(), _usuario.getApellidos(), _usuario.getMail(), _usuario.getDir_foto());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param _newPasswordDTO
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/perfil/password", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> cambioPasswordUsuario(@RequestBody NewPasswordDTO _newPasswordDTO) throws NoSuchAlgorithmException {
        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (_newPasswordDTO.getPasswordActual() == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            if (!encoder.matches(_newPasswordDTO.getPasswordActual(), ((UserDetails) principal).getPassword())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }

        if (!_newPasswordDTO.getNewPassword().equals(_newPasswordDTO.getConfPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        red.setUsername(usernameConectado);

        red.cambiarPassword(_newPasswordDTO.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/perfil", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    PerfilDTO miPerfil() {
        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
        }

        red.setUsername(usernameConectado);

        String nivel = null;

        if (null != red.getUsuarioConectado().getNivel().getNivelAsociado()) {
            switch (red.getUsuarioConectado().getNivel().getNivelAsociado()) {
                case _1:
                    nivel = "1";
                    break;
                case _2:
                    nivel = "2";
                    break;
                case _3:
                    nivel = "3";
                    break;
                case _4:
                    nivel = "4";
                    break;
                case _5:
                    nivel = "5";
                    break;
                case _5m:
                    nivel = "5+";
                    break;
                case _6a:
                    nivel = "6a";
                    break;
                case _6am:
                    nivel = "6a+";
                    break;
                case _6b:
                    nivel = "6b";
                    break;
                case _6bm:
                    nivel = "6b+";
                    break;
                case _6c:
                    nivel = "6c";
                    break;
                case _6cm:
                    nivel = "6c+";
                    break;
                case _7a:
                    nivel = "7a";
                    break;
                case _7am:
                    nivel = "7a+";
                    break;
                case _7b:
                    nivel = "7b";
                    break;
                case _7bm:
                    nivel = "7b+";
                    break;
                case _7c:
                    nivel = "7c";
                    break;
                case _7cm:
                    nivel = "7c+";
                    break;
                case _8a:
                    nivel = "8a";
                    break;
                case _8am:
                    nivel = "8a+";
                    break;
                case _8b:
                    nivel = "8b";
                    break;
                case _8bm:
                    nivel = "8b+";
                    break;
                case _8c:
                    nivel = "8c";
                    break;
                case _8cm:
                    nivel = "8c+";
                    break;
                case _9a:
                    nivel = "9a";
                    break;
                case _9am:
                    nivel = "9a+";
                    break;
                case _9b:
                    nivel = "9b";
                    break;
                case _9bm:
                    nivel = "9b+";
                    break;
                case _9c:
                    nivel = "9c";
                    break;
                case _9cm:
                    nivel = "9c+";
                    break;
                default:
                    break;
            }
        }

        return new PerfilDTO(red.getUsuarioConectado().getUsername(), red.getUsuarioConectado().getNombre(), red.getUsuarioConectado().getApellidos(), nivel, red.getUsuarioConectado().getEmail(), red.getUsuarioConectado().getFotoperfil());
    }

    /**
     *
     * @param _username
     * @return
     */
    @RequestMapping(value = "/perfil/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    PerfilDTO verPerfil(@PathVariable("username") String _username) {
        red.setUsername(_username);
        
        Usuario u=red.getDaoUsuario().obtenerUsuario(_username);

        String nivel = null;

        if (null != u.getNivel().getNivelAsociado()) {
            switch (u.getNivel().getNivelAsociado()) {
                case _1:
                    nivel = "1";
                    break;
                case _2:
                    nivel = "2";
                    break;
                case _3:
                    nivel = "3";
                    break;
                case _4:
                    nivel = "4";
                    break;
                case _5:
                    nivel = "5";
                    break;
                case _5m:
                    nivel = "5+";
                    break;
                case _6a:
                    nivel = "6a";
                    break;
                case _6am:
                    nivel = "6a+";
                    break;
                case _6b:
                    nivel = "6b";
                    break;
                case _6bm:
                    nivel = "6b+";
                    break;
                case _6c:
                    nivel = "6c";
                    break;
                case _6cm:
                    nivel = "6c+";
                    break;
                case _7a:
                    nivel = "7a";
                    break;
                case _7am:
                    nivel = "7a+";
                    break;
                case _7b:
                    nivel = "7b";
                    break;
                case _7bm:
                    nivel = "7b+";
                    break;
                case _7c:
                    nivel = "7c";
                    break;
                case _7cm:
                    nivel = "7c+";
                    break;
                case _8a:
                    nivel = "8a";
                    break;
                case _8am:
                    nivel = "8a+";
                    break;
                case _8b:
                    nivel = "8b";
                    break;
                case _8bm:
                    nivel = "8b+";
                    break;
                case _8c:
                    nivel = "8c";
                    break;
                case _8cm:
                    nivel = "8c+";
                    break;
                case _9a:
                    nivel = "9a";
                    break;
                case _9am:
                    nivel = "9a+";
                    break;
                case _9b:
                    nivel = "9b";
                    break;
                case _9bm:
                    nivel = "9b+";
                    break;
                case _9c:
                    nivel = "9c";
                    break;
                case _9cm:
                    nivel = "9c+";
                    break;
                default:
                    break;
            }
        }

        return new PerfilDTO(u.getUsername(), u.getNombre(), u.getApellidos(), nivel, u.getEmail(), u.getFotoperfil());
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/perfil/amigos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<AmigosDTO> misAmigos() {
        List<AmigosDTO> amigos = new ArrayList();

        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
        }

        red.setUsername(usernameConectado);

        for (Usuario amigo : red.getUsuarioConectado().getAmigos()) {
            AmigosDTO aux = new AmigosDTO();
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
    public @ResponseBody
    List<AmigosDTO> amigosPerfil(@PathVariable("username") String _username) {
        List<AmigosDTO> amigos = new ArrayList();

        red.setUsername(_username);
        
        Usuario u=red.getDaoUsuario().obtenerUsuario(_username);

        for (Usuario amigo : u.getAmigos()) {
            AmigosDTO aux = new AmigosDTO();
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
    public @ResponseBody
    List<ViasDTO> misVias() {
        List<ViasDTO> vias = new ArrayList();

        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
        }

        red.setUsername(usernameConectado);

        for (Via via : red.getUsuarioConectado().getViasRealizadas()) {
            ViasDTO aux = new ViasDTO();

            aux.setId_via(via.getId_via());
            aux.setId_mapa(via.getIdv_via());
            aux.setNombre(via.getNombre());
            aux.setSector(via.getSector().getNombreSector());
            aux.setContador(via.getContador());
            aux.setEstrellas(via.getEstrellas());

            switch (via.getNivel().getNivelAsociado()) {
                case _1:
                    aux.setNivel_oficial("1");
                    break;
                case _2:
                    aux.setNivel_oficial("2");
                    break;
                case _3:
                    aux.setNivel_oficial("3");
                    break;
                case _4:
                    aux.setNivel_oficial("4");
                    break;
                case _5:
                    aux.setNivel_oficial("5");
                    break;
                case _5m:
                    aux.setNivel_oficial("5+");
                    break;
                case _6a:
                    aux.setNivel_oficial("6a");
                    break;
                case _6am:
                    aux.setNivel_oficial("6a+");
                    break;
                case _6b:
                    aux.setNivel_oficial("6b");
                    break;
                case _6bm:
                    aux.setNivel_oficial("6b+");
                    break;
                case _6c:
                    aux.setNivel_oficial("6c");
                    break;
                case _6cm:
                    aux.setNivel_oficial("6c+");
                    break;
                case _7a:
                    aux.setNivel_oficial("7a");
                    break;
                case _7am:
                    aux.setNivel_oficial("7a+");
                    break;
                case _7b:
                    aux.setNivel_oficial("7b");
                    break;
                case _7bm:
                    aux.setNivel_oficial("7b+");
                    break;
                case _7c:
                    aux.setNivel_oficial("7c");
                    break;
                case _7cm:
                    aux.setNivel_oficial("7c+");
                    break;
                case _8a:
                    aux.setNivel_oficial("8a");
                    break;
                case _8am:
                    aux.setNivel_oficial("8a+");
                    break;
                case _8b:
                    aux.setNivel_oficial("8b");
                    break;
                case _8bm:
                    aux.setNivel_oficial("8b+");
                    break;
                case _8c:
                    aux.setNivel_oficial("8c");
                    break;
                case _8cm:
                    aux.setNivel_oficial("8c+");
                    break;
                case _9a:
                    aux.setNivel_oficial("9a");
                    break;
                case _9am:
                    aux.setNivel_oficial("9a+");
                    break;
                case _9b:
                    aux.setNivel_oficial("9b");
                    break;
                case _9bm:
                    aux.setNivel_oficial("9b+");
                    break;
                case _9c:
                    aux.setNivel_oficial("9c");
                    break;
                case _9cm:
                    aux.setNivel_oficial("9c+");
                    break;
                default:
                    break;
            }

            switch (via.getNivel().getNivelAsociado()) {
                case _1:
                    aux.setNivel_consensuado("1");
                    break;
                case _2:
                    aux.setNivel_consensuado("2");
                    break;
                case _3:
                    aux.setNivel_consensuado("3");
                    break;
                case _4:
                    aux.setNivel_consensuado("4");
                    break;
                case _5:
                    aux.setNivel_consensuado("5");
                    break;
                case _5m:
                    aux.setNivel_consensuado("5+");
                    break;
                case _6a:
                    aux.setNivel_consensuado("6a");
                    break;
                case _6am:
                    aux.setNivel_consensuado("6a+");
                    break;
                case _6b:
                    aux.setNivel_consensuado("6b");
                    break;
                case _6bm:
                    aux.setNivel_consensuado("6b+");
                    break;
                case _6c:
                    aux.setNivel_consensuado("6c");
                    break;
                case _6cm:
                    aux.setNivel_consensuado("6c+");
                    break;
                case _7a:
                    aux.setNivel_consensuado("7a");
                    break;
                case _7am:
                    aux.setNivel_consensuado("7a+");
                    break;
                case _7b:
                    aux.setNivel_consensuado("7b");
                    break;
                case _7bm:
                    aux.setNivel_consensuado("7b+");
                    break;
                case _7c:
                    aux.setNivel_consensuado("7c");
                    break;
                case _7cm:
                    aux.setNivel_consensuado("7c+");
                    break;
                case _8a:
                    aux.setNivel_consensuado("8a");
                    break;
                case _8am:
                    aux.setNivel_consensuado("8a+");
                    break;
                case _8b:
                    aux.setNivel_consensuado("8b");
                    break;
                case _8bm:
                    aux.setNivel_consensuado("8b+");
                    break;
                case _8c:
                    aux.setNivel_consensuado("8c");
                    break;
                case _8cm:
                    aux.setNivel_consensuado("8c+");
                    break;
                case _9a:
                    aux.setNivel_consensuado("9a");
                    break;
                case _9am:
                    aux.setNivel_consensuado("9a+");
                    break;
                case _9b:
                    aux.setNivel_consensuado("9b");
                    break;
                case _9bm:
                    aux.setNivel_consensuado("9b+");
                    break;
                case _9c:
                    aux.setNivel_consensuado("9c");
                    break;
                case _9cm:
                    aux.setNivel_consensuado("9c+");
                    break;
                default:
                    break;
            }

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
    public @ResponseBody
    List<ViasDTO> viasPerfil(@PathVariable("username") String _usernameConectado) {
        List<ViasDTO> vias = new ArrayList();

        red.setUsername(_usernameConectado);
        
        Usuario u=red.getDaoUsuario().obtenerUsuario(_usernameConectado);

        for (Via via : u.getViasRealizadas()) {
            ViasDTO aux = new ViasDTO();

            aux.setId_via(via.getId_via());
            aux.setId_mapa(via.getIdv_via());
            aux.setNombre(via.getNombre());
            aux.setSector(via.getSector().getNombreSector());
            aux.setContador(via.getContador());
            aux.setEstrellas(via.getEstrellas());

            switch (via.getNivel().getNivelAsociado()) {
                case _1:
                    aux.setNivel_oficial("1");
                    break;
                case _2:
                    aux.setNivel_oficial("2");
                    break;
                case _3:
                    aux.setNivel_oficial("3");
                    break;
                case _4:
                    aux.setNivel_oficial("4");
                    break;
                case _5:
                    aux.setNivel_oficial("5");
                    break;
                case _5m:
                    aux.setNivel_oficial("5+");
                    break;
                case _6a:
                    aux.setNivel_oficial("6a");
                    break;
                case _6am:
                    aux.setNivel_oficial("6a+");
                    break;
                case _6b:
                    aux.setNivel_oficial("6b");
                    break;
                case _6bm:
                    aux.setNivel_oficial("6b+");
                    break;
                case _6c:
                    aux.setNivel_oficial("6c");
                    break;
                case _6cm:
                    aux.setNivel_oficial("6c+");
                    break;
                case _7a:
                    aux.setNivel_oficial("7a");
                    break;
                case _7am:
                    aux.setNivel_oficial("7a+");
                    break;
                case _7b:
                    aux.setNivel_oficial("7b");
                    break;
                case _7bm:
                    aux.setNivel_oficial("7b+");
                    break;
                case _7c:
                    aux.setNivel_oficial("7c");
                    break;
                case _7cm:
                    aux.setNivel_oficial("7c+");
                    break;
                case _8a:
                    aux.setNivel_oficial("8a");
                    break;
                case _8am:
                    aux.setNivel_oficial("8a+");
                    break;
                case _8b:
                    aux.setNivel_oficial("8b");
                    break;
                case _8bm:
                    aux.setNivel_oficial("8b+");
                    break;
                case _8c:
                    aux.setNivel_oficial("8c");
                    break;
                case _8cm:
                    aux.setNivel_oficial("8c+");
                    break;
                case _9a:
                    aux.setNivel_oficial("9a");
                    break;
                case _9am:
                    aux.setNivel_oficial("9a+");
                    break;
                case _9b:
                    aux.setNivel_oficial("9b");
                    break;
                case _9bm:
                    aux.setNivel_oficial("9b+");
                    break;
                case _9c:
                    aux.setNivel_oficial("9c");
                    break;
                case _9cm:
                    aux.setNivel_oficial("9c+");
                    break;
                default:
                    break;
            }

            switch (via.getNivel().getNivelAsociado()) {
                case _1:
                    aux.setNivel_consensuado("1");
                    break;
                case _2:
                    aux.setNivel_consensuado("2");
                    break;
                case _3:
                    aux.setNivel_consensuado("3");
                    break;
                case _4:
                    aux.setNivel_consensuado("4");
                    break;
                case _5:
                    aux.setNivel_consensuado("5");
                    break;
                case _5m:
                    aux.setNivel_consensuado("5+");
                    break;
                case _6a:
                    aux.setNivel_consensuado("6a");
                    break;
                case _6am:
                    aux.setNivel_consensuado("6a+");
                    break;
                case _6b:
                    aux.setNivel_consensuado("6b");
                    break;
                case _6bm:
                    aux.setNivel_consensuado("6b+");
                    break;
                case _6c:
                    aux.setNivel_consensuado("6c");
                    break;
                case _6cm:
                    aux.setNivel_consensuado("6c+");
                    break;
                case _7a:
                    aux.setNivel_consensuado("7a");
                    break;
                case _7am:
                    aux.setNivel_consensuado("7a+");
                    break;
                case _7b:
                    aux.setNivel_consensuado("7b");
                    break;
                case _7bm:
                    aux.setNivel_consensuado("7b+");
                    break;
                case _7c:
                    aux.setNivel_consensuado("7c");
                    break;
                case _7cm:
                    aux.setNivel_consensuado("7c+");
                    break;
                case _8a:
                    aux.setNivel_consensuado("8a");
                    break;
                case _8am:
                    aux.setNivel_consensuado("8a+");
                    break;
                case _8b:
                    aux.setNivel_consensuado("8b");
                    break;
                case _8bm:
                    aux.setNivel_consensuado("8b+");
                    break;
                case _8c:
                    aux.setNivel_consensuado("8c");
                    break;
                case _8cm:
                    aux.setNivel_consensuado("8c+");
                    break;
                case _9a:
                    aux.setNivel_consensuado("9a");
                    break;
                case _9am:
                    aux.setNivel_consensuado("9a+");
                    break;
                case _9b:
                    aux.setNivel_consensuado("9b");
                    break;
                case _9bm:
                    aux.setNivel_consensuado("9b+");
                    break;
                case _9c:
                    aux.setNivel_consensuado("9c");
                    break;
                case _9cm:
                    aux.setNivel_consensuado("9c+");
                    break;
                default:
                    break;
            }

            vias.add(aux);
        }

        return vias;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "perfil/numeropeticiones", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    NumeroPeticionesDTO numeroPeticionesAmistadPendientes() {
        NumeroPeticionesDTO numero = new NumeroPeticionesDTO();

        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
        }

        red.setUsername(usernameConectado);

        numero.setNumeroPendientes(red.peticionesAmistadRecibidas().size());

        return numero;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "perfil/peticiones", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<PeticionDTO> peticionesAmistadPendientes() {
        List<PeticionDTO> peticiones = new ArrayList();

        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
        }

        red.setUsername(usernameConectado);

        for (PeticionAmistad peticion : red.peticionesAmistadRecibidas()) {
            PeticionDTO aux = new PeticionDTO(peticion.getId_peticion(), peticion.getEmisor().getUsername());

            peticiones.add(aux);
        }

        return peticiones;
    }

    /**
     *
     * @param _usuario
     */
    @RequestMapping(value = "/perfil/peticiones", method = RequestMethod.POST, consumes = "application/json")
    public void solicitarAmistad(@RequestBody UsernameDTO _usuario) {
        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
        }

        red.setUsername(usernameConectado);

        try {
            red.enviarPeticionAmistad(_usuario.getUsername());
        } catch (exceptionsBusiness.PeticionYaEnviada ex) {
            throw new exceptionsBusiness.PeticionYaEnviada();
        }

    }

    /**
     *
     * @param _peticion
     */
    @RequestMapping(value = "/perfil/peticiones", method = RequestMethod.PUT, consumes = "application/json")
    public void confirmarAmistad(@RequestBody GestionarPeticionDTO _peticion) {
        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
        }

        red.setUsername(usernameConectado);

        red.confirmarPeticion(_peticion.getIdPeticion(), _peticion.isConf());
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/username", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> prueba2() {
        UsernameDTO aux = new UsernameDTO();
        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
        }

        if (usernameConectado == null) {
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
    public void nuevaEscuela(@RequestBody EscuelaDTO _escuela) throws IOException {
        red.nuevaEscuela(_escuela.getNombre(), _escuela.getDescripcion(), _escuela.getHorario(), _escuela.getDir_foto(), _escuela.getCod_provincia());
    }

    /**
     *
     * @param _id_escuela
     * @param _sector
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/escuelas/{id_escuela}/sectores", method = RequestMethod.POST, consumes = "application/json")
    public void nuevoSector(@PathVariable("id_escuela") Integer _id_escuela, @RequestBody SectorDTO _sector) throws IOException {
        red.nuevoSector(_id_escuela, _sector.getOrientacion(), _sector.getNombre(), _sector.getDir_foto());
    }

    /**
     *
     * @param _id_sector
     * @param _via
     */
    @RequestMapping(value = "/sectores/{id_sector}/vias", method = RequestMethod.POST, consumes = "application/json")
    public void nuevaVia(@PathVariable("id_sector") Integer _id_sector, @RequestBody NuevaViaDTO _via) {
        Nivel nivel = new Nivel();

        switch (_via.getNivel_oficial()) {
            case "1":
                nivel.setNivel(Nivel.nivelAsociado._1);
                break;
            case "2":
                nivel.setNivel(Nivel.nivelAsociado._2);
                break;
            case "3":
                nivel.setNivel(Nivel.nivelAsociado._3);
                break;
            case "4":
                nivel.setNivel(Nivel.nivelAsociado._4);
                break;
            case "5":
                nivel.setNivel(Nivel.nivelAsociado._5);
                break;
            case "5+":
                nivel.setNivel(Nivel.nivelAsociado._5m);
                break;
            case "6a":
                nivel.setNivel(Nivel.nivelAsociado._6a);
                break;
            case "6a+":
                nivel.setNivel(Nivel.nivelAsociado._6am);
                break;
            case "6b":
                nivel.setNivel(Nivel.nivelAsociado._6b);
                break;
            case "6b+":
                nivel.setNivel(Nivel.nivelAsociado._6bm);
                break;
            case "6c":
                nivel.setNivel(Nivel.nivelAsociado._6c);
                break;
            case "6c+":
                nivel.setNivel(Nivel.nivelAsociado._6cm);
                break;
            case "7a":
                nivel.setNivel(Nivel.nivelAsociado._7a);
                break;
            case "7a+":
                nivel.setNivel(Nivel.nivelAsociado._7am);
                break;
            case "7b":
                nivel.setNivel(Nivel.nivelAsociado._7b);
                break;
            case "7b+":
                nivel.setNivel(Nivel.nivelAsociado._7bm);
                break;
            case "7c":
                nivel.setNivel(Nivel.nivelAsociado._7c);
                break;
            case "7c+":
                nivel.setNivel(Nivel.nivelAsociado._7cm);
                break;
            case "8a":
                nivel.setNivel(Nivel.nivelAsociado._8a);
                break;
            case "8a+":
                nivel.setNivel(Nivel.nivelAsociado._8am);
                break;
            case "8b":
                nivel.setNivel(Nivel.nivelAsociado._8b);
                break;
            case "8b+":
                nivel.setNivel(Nivel.nivelAsociado._8bm);
                break;
            case "8c":
                nivel.setNivel(Nivel.nivelAsociado._8c);
                break;
            case "8c+":
                nivel.setNivel(Nivel.nivelAsociado._8cm);
                break;
            case "9a":
                nivel.setNivel(Nivel.nivelAsociado._9a);
                break;
            case "9a+":
                nivel.setNivel(Nivel.nivelAsociado._9am);
                break;
            case "9b":
                nivel.setNivel(Nivel.nivelAsociado._9b);
                break;
            case "9b+":
                nivel.setNivel(Nivel.nivelAsociado._9bm);
                break;
            case "9c":
                nivel.setNivel(Nivel.nivelAsociado._9c);
                break;
            case "9c+":
                nivel.setNivel(Nivel.nivelAsociado._9cm);
                break;

            default:
                break;
        }

        red.nuevaVia(_id_sector, _via.getNombre(), nivel, _via.getId_mapa());
    }

    /**
     *
     * @param _cod_provincia
     * @return
     */
    @RequestMapping(value = "/escuelas/{cod_prov}", method = RequestMethod.GET, produces = "application/json")
    public List<EscuelasDTO> escuelasProv(@PathVariable("cod_prov") Integer _cod_provincia) {
        List<EscuelasDTO> aux_list = new ArrayList();

        for (Escuela escuela : red.escuelasProvincia(_cod_provincia))
        {
            EscuelasDTO aux = new EscuelasDTO();
            
            aux.setId_escuela(escuela.getId_escuela());
            aux.setNombre(escuela.getNombreEscuela());
            
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
    public List<SectoresDTO> sectoresEsc(@PathVariable("cod_escuela") Integer _cod_escuela) {
        List<SectoresDTO> aux_list = new ArrayList();

        for (Sector sector : red.sectoresEscuela(_cod_escuela))
        {
            SectoresDTO aux = new SectoresDTO();
            
            aux.setId_sector(sector.getId_sector());
            aux.setNombre(sector.getNombreSector());

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
    public List<ViasDTO> viasSector(@PathVariable("cod_sector") Integer _cod_sector) {
        List<ViasDTO> vias = new ArrayList();

        for (Via via : red.viasSector(_cod_sector)) {
            ViasDTO aux = new ViasDTO();

            aux.setId_via(via.getId_via());
            aux.setId_mapa(via.getIdv_via());
            aux.setNombre(via.getNombre());
            aux.setSector(via.getSector().getNombreSector());
            aux.setContador(via.getContador());
            aux.setEstrellas(via.getEstrellas());

            switch (via.getNivel().getNivelAsociado()) {
                case _1:
                    aux.setNivel_oficial("1");
                    break;
                case _2:
                    aux.setNivel_oficial("2");
                    break;
                case _3:
                    aux.setNivel_oficial("3");
                    break;
                case _4:
                    aux.setNivel_oficial("4");
                    break;
                case _5:
                    aux.setNivel_oficial("5");
                    break;
                case _5m:
                    aux.setNivel_oficial("5+");
                    break;
                case _6a:
                    aux.setNivel_oficial("6a");
                    break;
                case _6am:
                    aux.setNivel_oficial("6a+");
                    break;
                case _6b:
                    aux.setNivel_oficial("6b");
                    break;
                case _6bm:
                    aux.setNivel_oficial("6b+");
                    break;
                case _6c:
                    aux.setNivel_oficial("6c");
                    break;
                case _6cm:
                    aux.setNivel_oficial("6c+");
                    break;
                case _7a:
                    aux.setNivel_oficial("7a");
                    break;
                case _7am:
                    aux.setNivel_oficial("7a+");
                    break;
                case _7b:
                    aux.setNivel_oficial("7b");
                    break;
                case _7bm:
                    aux.setNivel_oficial("7b+");
                    break;
                case _7c:
                    aux.setNivel_oficial("7c");
                    break;
                case _7cm:
                    aux.setNivel_oficial("7c+");
                    break;
                case _8a:
                    aux.setNivel_oficial("8a");
                    break;
                case _8am:
                    aux.setNivel_oficial("8a+");
                    break;
                case _8b:
                    aux.setNivel_oficial("8b");
                    break;
                case _8bm:
                    aux.setNivel_oficial("8b+");
                    break;
                case _8c:
                    aux.setNivel_oficial("8c");
                    break;
                case _8cm:
                    aux.setNivel_oficial("8c+");
                    break;
                case _9a:
                    aux.setNivel_oficial("9a");
                    break;
                case _9am:
                    aux.setNivel_oficial("9a+");
                    break;
                case _9b:
                    aux.setNivel_oficial("9b");
                    break;
                case _9bm:
                    aux.setNivel_oficial("9b+");
                    break;
                case _9c:
                    aux.setNivel_oficial("9c");
                    break;
                case _9cm:
                    aux.setNivel_oficial("9c+");
                    break;
                default:
                    break;
            }

            switch (via.getNivel().getNivelAsociado()) {
                case _1:
                    aux.setNivel_consensuado("1");
                    break;
                case _2:
                    aux.setNivel_consensuado("2");
                    break;
                case _3:
                    aux.setNivel_consensuado("3");
                    break;
                case _4:
                    aux.setNivel_consensuado("4");
                    break;
                case _5:
                    aux.setNivel_consensuado("5");
                    break;
                case _5m:
                    aux.setNivel_consensuado("5+");
                    break;
                case _6a:
                    aux.setNivel_consensuado("6a");
                    break;
                case _6am:
                    aux.setNivel_consensuado("6a+");
                    break;
                case _6b:
                    aux.setNivel_consensuado("6b");
                    break;
                case _6bm:
                    aux.setNivel_consensuado("6b+");
                    break;
                case _6c:
                    aux.setNivel_consensuado("6c");
                    break;
                case _6cm:
                    aux.setNivel_consensuado("6c+");
                    break;
                case _7a:
                    aux.setNivel_consensuado("7a");
                    break;
                case _7am:
                    aux.setNivel_consensuado("7a+");
                    break;
                case _7b:
                    aux.setNivel_consensuado("7b");
                    break;
                case _7bm:
                    aux.setNivel_consensuado("7b+");
                    break;
                case _7c:
                    aux.setNivel_consensuado("7c");
                    break;
                case _7cm:
                    aux.setNivel_consensuado("7c+");
                    break;
                case _8a:
                    aux.setNivel_consensuado("8a");
                    break;
                case _8am:
                    aux.setNivel_consensuado("8a+");
                    break;
                case _8b:
                    aux.setNivel_consensuado("8b");
                    break;
                case _8bm:
                    aux.setNivel_consensuado("8b+");
                    break;
                case _8c:
                    aux.setNivel_consensuado("8c");
                    break;
                case _8cm:
                    aux.setNivel_consensuado("8c+");
                    break;
                case _9a:
                    aux.setNivel_consensuado("9a");
                    break;
                case _9am:
                    aux.setNivel_consensuado("9a+");
                    break;
                case _9b:
                    aux.setNivel_consensuado("9b");
                    break;
                case _9bm:
                    aux.setNivel_consensuado("9b+");
                    break;
                case _9c:
                    aux.setNivel_consensuado("9c");
                    break;
                case _9cm:
                    aux.setNivel_consensuado("9c+");
                    break;
                default:
                    break;
            }

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
    public void add_comentario_via(@PathVariable("cod_via") Integer _cod_via, @RequestBody ComentarioDTO _comentario) {
        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
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
    public List<ComentariosDTO> comentarios_via(@PathVariable("cod_via") Integer _cod_via) {
        List<ComentariosDTO> comentarios = new ArrayList();

        for (Comentario comentario : red.comentariosVia(_cod_via)) {
            ComentariosDTO aux = new ComentariosDTO();

            aux.setUsername(comentario.getUsuario().getUsername());
            aux.setValor_comentario(comentario.getComentario());

            comentarios.add(aux);
        }

        return comentarios;
    }

    @RequestMapping(value = "/perfil/vias/{id_via}", method = RequestMethod.POST, consumes = "application/json")
    public void realizarVia(@PathVariable("id_via") Integer _id_via, @RequestBody ValorarViaDTO _valoracion) {
        String usernameConectado = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            usernameConectado = ((UserDetails) principal).getUsername();
        }

        red.setUsername(usernameConectado);

        Nivel nivel = new Nivel();

        switch (_valoracion.getNivel()) {
            case "1":
                nivel.setNivel(Nivel.nivelAsociado._1);
                break;
            case "2":
                nivel.setNivel(Nivel.nivelAsociado._2);
                break;
            case "3":
                nivel.setNivel(Nivel.nivelAsociado._3);
                break;
            case "4":
                nivel.setNivel(Nivel.nivelAsociado._4);
                break;
            case "5":
                nivel.setNivel(Nivel.nivelAsociado._5);
                break;
            case "5+":
                nivel.setNivel(Nivel.nivelAsociado._5m);
                break;
            case "6a":
                nivel.setNivel(Nivel.nivelAsociado._6a);
                break;
            case "6a+":
                nivel.setNivel(Nivel.nivelAsociado._6am);
                break;
            case "6b":
                nivel.setNivel(Nivel.nivelAsociado._6b);
                break;
            case "6b+":
                nivel.setNivel(Nivel.nivelAsociado._6bm);
                break;
            case "6c":
                nivel.setNivel(Nivel.nivelAsociado._6c);
                break;
            case "6c+":
                nivel.setNivel(Nivel.nivelAsociado._6cm);
                break;
            case "7a":
                nivel.setNivel(Nivel.nivelAsociado._7a);
                break;
            case "7a+":
                nivel.setNivel(Nivel.nivelAsociado._7am);
                break;
            case "7b":
                nivel.setNivel(Nivel.nivelAsociado._7b);
                break;
            case "7b+":
                nivel.setNivel(Nivel.nivelAsociado._7bm);
                break;
            case "7c":
                nivel.setNivel(Nivel.nivelAsociado._7c);
                break;
            case "7c+":
                nivel.setNivel(Nivel.nivelAsociado._7cm);
                break;
            case "8a":
                nivel.setNivel(Nivel.nivelAsociado._8a);
                break;
            case "8a+":
                nivel.setNivel(Nivel.nivelAsociado._8am);
                break;
            case "8b":
                nivel.setNivel(Nivel.nivelAsociado._8b);
                break;
            case "8b+":
                nivel.setNivel(Nivel.nivelAsociado._8bm);
                break;
            case "8c":
                nivel.setNivel(Nivel.nivelAsociado._8c);
                break;
            case "8c+":
                nivel.setNivel(Nivel.nivelAsociado._8cm);
                break;
            case "9a":
                nivel.setNivel(Nivel.nivelAsociado._9a);
                break;
            case "9a+":
                nivel.setNivel(Nivel.nivelAsociado._9am);
                break;
            case "9b":
                nivel.setNivel(Nivel.nivelAsociado._9b);
                break;
            case "9b+":
                nivel.setNivel(Nivel.nivelAsociado._9bm);
                break;
            case "9c":
                nivel.setNivel(Nivel.nivelAsociado._9c);
                break;
            case "9c+":
                nivel.setNivel(Nivel.nivelAsociado._9cm);
                break;

            default:
                break;
        }

        red.realizarVia(_id_via, nivel, _valoracion.getValoracion());

    }
    
    /**
     * 
     * @param cod_provicia
     * @return 
     */
    @RequestMapping(value = "/provincia/{cod_provincia}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ProvinciaDTO provincia(@PathVariable ("cod_provincia") Integer cod_provicia)
    {
        Provincia provincia=red.getDaoProvincia().obtenerProvincia(cod_provicia);
        
        ProvinciaDTO provinciaDTO=new ProvinciaDTO(provincia.getProvincia());
        
        return provinciaDTO;
    }
    
    /**
     * 
     * @param cod_escuela
     * @return 
     */
    @RequestMapping(value = "/escuela/{cod_escuela}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody EscuelasDTO datosEscuela(@PathVariable ("cod_escuela") Integer cod_escuela)
    {
        Escuela e=red.getDaoEscuela().obtenerEscuela(cod_escuela);
        
        EscuelasDTO escuelasDTO=new EscuelasDTO(e.getId_escuela(), e.getNombreEscuela(), e.getDescripcion(), e.getFotoEscuela(), e.getHorario());
        escuelasDTO.setProvincia(e.getUbicacion().getProvincia());
        
        return escuelasDTO;
    }
    
    /**
     * 
     * @param cod_sector
     * @return 
     */
    @RequestMapping(value = "/sector/{cod_sector}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody SectoresDTO datosSector(@PathVariable ("cod_sector") Integer cod_sector)
    {
        Sector s=red.getDaoSector().obtenerSector(cod_sector);
        
        SectoresDTO dto=new SectoresDTO(s.getId_sector(), s.getOrientacion().getOrientacion().toString(), s.getNombreSector(), s.getFotoSector());
        
        dto.setEscuela(s.getEscuela().getNombreEscuela());
        
        return dto;
    }
    
    /**
     * 
     * @param cod_via
     * @return 
     */
    @RequestMapping(value = "/via/{cod_via}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ViasDTO datosVia(@PathVariable ("cod_via") Integer cod_via)
    {
        Via via=red.getDaoVia().obtenerVia(cod_via);
        
        ViasDTO aux=new ViasDTO();
        
        aux.setId_via(via.getId_via());
        aux.setId_mapa(via.getIdv_via());
        aux.setNombre(via.getNombre());
        aux.setSector(via.getSector().getNombreSector());
        aux.setContador(via.getContador());
        aux.setEstrellas(via.getEstrellas());
        aux.setSector(via.getSector().getNombreSector());
        
        switch (via.getNivel().getNivelAsociado()) {
                case _1:
                    aux.setNivel_oficial("1");
                    break;
                case _2:
                    aux.setNivel_oficial("2");
                    break;
                case _3:
                    aux.setNivel_oficial("3");
                    break;
                case _4:
                    aux.setNivel_oficial("4");
                    break;
                case _5:
                    aux.setNivel_oficial("5");
                    break;
                case _5m:
                    aux.setNivel_oficial("5+");
                    break;
                case _6a:
                    aux.setNivel_oficial("6a");
                    break;
                case _6am:
                    aux.setNivel_oficial("6a+");
                    break;
                case _6b:
                    aux.setNivel_oficial("6b");
                    break;
                case _6bm:
                    aux.setNivel_oficial("6b+");
                    break;
                case _6c:
                    aux.setNivel_oficial("6c");
                    break;
                case _6cm:
                    aux.setNivel_oficial("6c+");
                    break;
                case _7a:
                    aux.setNivel_oficial("7a");
                    break;
                case _7am:
                    aux.setNivel_oficial("7a+");
                    break;
                case _7b:
                    aux.setNivel_oficial("7b");
                    break;
                case _7bm:
                    aux.setNivel_oficial("7b+");
                    break;
                case _7c:
                    aux.setNivel_oficial("7c");
                    break;
                case _7cm:
                    aux.setNivel_oficial("7c+");
                    break;
                case _8a:
                    aux.setNivel_oficial("8a");
                    break;
                case _8am:
                    aux.setNivel_oficial("8a+");
                    break;
                case _8b:
                    aux.setNivel_oficial("8b");
                    break;
                case _8bm:
                    aux.setNivel_oficial("8b+");
                    break;
                case _8c:
                    aux.setNivel_oficial("8c");
                    break;
                case _8cm:
                    aux.setNivel_oficial("8c+");
                    break;
                case _9a:
                    aux.setNivel_oficial("9a");
                    break;
                case _9am:
                    aux.setNivel_oficial("9a+");
                    break;
                case _9b:
                    aux.setNivel_oficial("9b");
                    break;
                case _9bm:
                    aux.setNivel_oficial("9b+");
                    break;
                case _9c:
                    aux.setNivel_oficial("9c");
                    break;
                case _9cm:
                    aux.setNivel_oficial("9c+");
                    break;
                default:
                    break;
            }

            switch (via.getNivel().getNivelAsociado()) {
                case _1:
                    aux.setNivel_consensuado("1");
                    break;
                case _2:
                    aux.setNivel_consensuado("2");
                    break;
                case _3:
                    aux.setNivel_consensuado("3");
                    break;
                case _4:
                    aux.setNivel_consensuado("4");
                    break;
                case _5:
                    aux.setNivel_consensuado("5");
                    break;
                case _5m:
                    aux.setNivel_consensuado("5+");
                    break;
                case _6a:
                    aux.setNivel_consensuado("6a");
                    break;
                case _6am:
                    aux.setNivel_consensuado("6a+");
                    break;
                case _6b:
                    aux.setNivel_consensuado("6b");
                    break;
                case _6bm:
                    aux.setNivel_consensuado("6b+");
                    break;
                case _6c:
                    aux.setNivel_consensuado("6c");
                    break;
                case _6cm:
                    aux.setNivel_consensuado("6c+");
                    break;
                case _7a:
                    aux.setNivel_consensuado("7a");
                    break;
                case _7am:
                    aux.setNivel_consensuado("7a+");
                    break;
                case _7b:
                    aux.setNivel_consensuado("7b");
                    break;
                case _7bm:
                    aux.setNivel_consensuado("7b+");
                    break;
                case _7c:
                    aux.setNivel_consensuado("7c");
                    break;
                case _7cm:
                    aux.setNivel_consensuado("7c+");
                    break;
                case _8a:
                    aux.setNivel_consensuado("8a");
                    break;
                case _8am:
                    aux.setNivel_consensuado("8a+");
                    break;
                case _8b:
                    aux.setNivel_consensuado("8b");
                    break;
                case _8bm:
                    aux.setNivel_consensuado("8b+");
                    break;
                case _8c:
                    aux.setNivel_consensuado("8c");
                    break;
                case _8cm:
                    aux.setNivel_consensuado("8c+");
                    break;
                case _9a:
                    aux.setNivel_consensuado("9a");
                    break;
                case _9am:
                    aux.setNivel_consensuado("9a+");
                    break;
                case _9b:
                    aux.setNivel_consensuado("9b");
                    break;
                case _9bm:
                    aux.setNivel_consensuado("9b+");
                    break;
                case _9c:
                    aux.setNivel_consensuado("9c");
                    break;
                case _9cm:
                    aux.setNivel_consensuado("9c+");
                    break;
                default:
                    break;
            }
                
        return aux;
    }
}

