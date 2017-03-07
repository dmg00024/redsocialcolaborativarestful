/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.DAOComentario;
import dao.DAOEscuela;
import dao.DAOProvincia;
import dao.DAONivel;
import dao.DAOOrientacion;
import dao.DAOPeticionAmistad;
import dao.DAOSector;
import dao.DAOUsuario;
import dao.DAOVia;
import exceptionsBusiness.ComentarioNoDisponible;
import exceptionsBusiness.PeticionYaEnviada;
import exceptionsBusiness.UsernameNoDisponible;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import modelo.Comentario;
import modelo.Escuela;
import modelo.Provincia;
import modelo.Nivel;
import modelo.Orientacion;
import modelo.PeticionAmistad;
import modelo.Sector;
import modelo.Usuario;
import modelo.Via;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
public class RedSocial
{
    private DAOComentario daoComentario;
    private DAOEscuela daoEscuela;
    private DAOProvincia daoProvincia;
    private DAONivel daoNivel;
    private DAOOrientacion daoOrientacion;
    private DAOPeticionAmistad daoPeticionAmistad;
    private DAOSector daoSector;
    private DAOUsuario daoUsuario;
    private DAOVia daoVia;
    private Usuario usuarioConectado;
    private String username;
    
    /**
     * 
     */
    public RedSocial() {}
    
    /**
     * 
     * @param _usernameConectado 
     */
    public RedSocial(String _usernameConectado)
    {
        username=_usernameConectado;
        usuarioConectado=daoUsuario.obtenerUsuario(username);
    }
   
    /**
     * 
     * @param user 
     */
    public RedSocial(Usuario user)
    {
        username=user.getUsername();
        usuarioConectado=daoUsuario.obtenerUsuario(user.getUsername());
    }

    /**
     * 
     * @return 
     */
    public DAOComentario getDaoComentario()
    {
        return daoComentario;
    }

    /**
     * 
     * @param daoComentario 
     */
    public void setDaoComentario(DAOComentario daoComentario)
    {
        this.daoComentario = daoComentario;
    }

    /**
     * 
     * @return 
     */
    public DAOEscuela getDaoEscuela() 
    {
        return daoEscuela;
    }

    /**
     * 
     * @param daoEscuela 
     */
    public void setDaoEscuela(DAOEscuela daoEscuela) 
    {
        this.daoEscuela = daoEscuela;
    }

    /**
     * 
     * @return 
     */
    public DAOProvincia getDaoProvincia() 
    {
        return daoProvincia;
    }

    /**
     * 
     * @param daoProvincia 
     */
    public void setDaoProvincia(DAOProvincia daoProvincia)
    {
        this.daoProvincia = daoProvincia;
    }

    /**
     * 
     * @return 
     */
    public DAONivel getDaoNivel() 
    {
        return daoNivel;
    }

    /**
     * 
     * @param daoNivel 
     */
    public void setDaoNivel(DAONivel daoNivel) 
    {
        this.daoNivel = daoNivel;
    }

    /**
     * 
     * @return 
     */
    public DAOOrientacion getDaoOrientacion()
    {
        return daoOrientacion;
    }

    /**
     * 
     * @param daoOrientacion 
     */
    public void setDaoOrientacion(DAOOrientacion daoOrientacion)
    {
        this.daoOrientacion = daoOrientacion;
    }

    /**
     * 
     * @return 
     */
    public DAOPeticionAmistad getDaoPeticionAmistad() 
    {
        return daoPeticionAmistad;
    }

    /**
     * 
     * @param daoPeticionAmistad 
     */
    public void setDaoPeticionAmistad(DAOPeticionAmistad daoPeticionAmistad) 
    {
        this.daoPeticionAmistad = daoPeticionAmistad;
    }

    /**
     * 
     * @return 
     */
    public DAOSector getDaoSector() 
    {
        return daoSector;
    }

    /**
     * 
     * @param daoSector 
     */
    public void setDaoSector(DAOSector daoSector) 
    {
        this.daoSector = daoSector;
    }

    /**
     * 
     * @return 
     */
    public DAOUsuario getDaoUsuario() 
    {
        return daoUsuario;
    }

    /**
     * 
     * @param daoUsuario 
     */
    public void setDaoUsuario(DAOUsuario daoUsuario) 
    {
        this.daoUsuario = daoUsuario;
    }

    /**
     * 
     * @return 
     */
    public DAOVia getDaoVia()
    {
        return daoVia;
    }

    /**
     * 
     * @param daoVia 
     */
    public void setDaoVia(DAOVia daoVia) 
    {
        this.daoVia = daoVia;
    }

    /**
     * 
     * @return 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.GetUsuarioConectadoException.class)
    public Usuario getUsuarioConectado()
    {
        usuarioConectado=daoUsuario.obtenerUsuario(username);
        
        usuarioConectado.getAmigos().size();
        usuarioConectado.getPeticionesAmistad().size();
        usuarioConectado.getViasRealizadas().size();
        usuarioConectado.getComentarios().size();
        
        return usuarioConectado;
    }

    /**
     * 
     * @param usuarioConectado 
     */
    public void setUsuarioConectado(Usuario usuarioConectado) 
    {
        this.usuarioConectado = usuarioConectado;
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
     * @param _username
     * @param _password
     * @param _email 
     * @throws java.security.NoSuchAlgorithmException 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.AltaUsuarioException.class)
    public void altaUsuario(String _username, String _password, String _email) throws NoSuchAlgorithmException
    {
        String hash = BCrypt.hashpw(_password, BCrypt.gensalt());
        
        try
        {
            daoUsuario.guardarUsuario(new Usuario(_username, hash, _email));
        }
        catch(RuntimeException e)
        {
            throw new UsernameNoDisponible();
        }
    }
    
    /**
     * 
     * @param _newPassword 
     * @throws java.security.NoSuchAlgorithmException 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.CambiarPasswordException.class)
    public void cambiarPassword(String _newPassword) throws NoSuchAlgorithmException
    {
        String hash = BCrypt.hashpw(_newPassword, BCrypt.gensalt());
        
        usuarioConectado=daoUsuario.obtenerUsuario(usuarioConectado.getUsername());
        
        usuarioConectado.setPassword(hash);
        
        daoUsuario.actualizarUsuario(usuarioConectado);
    }
    
    /**
     * 
     * @param _nombre
     * @param _apellidos
     * @param _email
     * @param dir_foto
     * @throws IOException 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.CambiarPasswordException.class)
    public void actualizarPerfilUsuario(String _nombre, String _apellidos, String _email, String dir_foto) throws IOException
    {
        usuarioConectado=daoUsuario.obtenerUsuario(username);
        
        usuarioConectado.setNombre(_nombre);
        usuarioConectado.setApellidos(_apellidos);
        usuarioConectado.setEmail(_email);
        
        if(!"".equals(dir_foto))
        {
            File foto=new File(dir_foto);
            byte[] _fotoperfil=Files.readAllBytes(foto.toPath());
            usuarioConectado.setFotoperfil(_fotoperfil);
        }
        else
        {
            usuarioConectado.setFotoperfil(null);
        }
        
        daoUsuario.actualizarUsuario(usuarioConectado);
    }
    
    /**
     * 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.BajaUsuarioException.class)
    public void bajaUsuario()
    {
        usuarioConectado=daoUsuario.obtenerUsuario(username);
        
        usuarioConectado.setNivel(null);
        
        for (Usuario amigo : usuarioConectado.getAmigos()) 
        {
            amigo.getAmigos().remove(usuarioConectado);
            daoUsuario.actualizarUsuario(amigo);
        }
        
        List<Comentario> comentarios=daoComentario.comentariosUsuario(usuarioConectado);
        
        for (Comentario comentario : comentarios)
        {
            comentario.getUsuario().getComentarios().remove(comentario);
            comentario.getVia().getComentarios().remove(comentario);
            daoUsuario.actualizarUsuario(usuarioConectado);
            daoVia.actualizarVia(comentario.getVia());
            comentario.setUsuario(null);
            comentario.setVia(null);
            daoComentario.actualizarComentario(comentario);
            daoComentario.borrarComentario(comentario);
        }
        
        usuarioConectado.getAmigos().clear();
        usuarioConectado.getViasRealizadas().clear();
        usuarioConectado.getPeticionesAmistad().clear();
              
        daoUsuario.actualizarUsuario(usuarioConectado);
        daoUsuario.borrarUsuario(usuarioConectado);
    } 
    
    /**
     * 
     * @param _username 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.EnviarPeticionAmistadException.class)
    public void enviarPeticionAmistad(String _username)
    {
        usuarioConectado=daoUsuario.obtenerUsuario(username);
       
        Usuario _usuario=daoUsuario.obtenerUsuario(_username);
        
        if(!daoPeticionAmistad.comprobarPeticionAmistad(usuarioConectado, _usuario))
        {
            throw new PeticionYaEnviada();
        } 
        
        PeticionAmistad peticion=new PeticionAmistad(usuarioConectado, _usuario);
        daoPeticionAmistad.guardarPeticionAmistad(peticion);
        usuarioConectado.getPeticionesAmistad().add(peticion);
        _usuario.getPeticionesAmistad().add(peticion);
            
        daoUsuario.actualizarUsuario(usuarioConectado);
        daoUsuario.actualizarUsuario(_usuario);
    }
    
    /**
     * 
     * @return peticiones de amistad recibidas
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.PeticionesAmistadRecibidasException.class)
    public List<PeticionAmistad> peticionesAmistadRecibidas()
    {
        usuarioConectado=daoUsuario.obtenerUsuario(username);
        
        List<PeticionAmistad> peticionesRecibidas=new ArrayList();
        
        if(!usuarioConectado.getPeticionesAmistad().isEmpty())
        {
            for (PeticionAmistad peticion : usuarioConectado.getPeticionesAmistad()) 
            {
                if(peticion.getReceptor().getUsername().equals(usuarioConectado.getUsername()))
                {
                    peticionesRecibidas.add(peticion);
                }
            }
        }
        
        return peticionesRecibidas;
    }
    
    /**
     * 
     * @param _id_peticion
     * @param _conf 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.ConfirmarPeticionException.class)
    public void confirmarPeticion(Integer _id_peticion, boolean _conf)
    {
        usuarioConectado=daoUsuario.obtenerUsuario(username);
       
        PeticionAmistad peticion=daoPeticionAmistad.obtenerPeticionAmistad(_id_peticion);
        
        Usuario emisor=daoUsuario.obtenerUsuario(peticion.getEmisor().getUsername());
        
        if(_conf)
        {
            usuarioConectado.getAmigos().add(emisor);
            emisor.getAmigos().add(usuarioConectado);
        }
        
        usuarioConectado.getPeticionesAmistad().remove(peticion);
        emisor.getPeticionesAmistad().remove(peticion);
        
        daoPeticionAmistad.borrarPeticionAmistad(peticion);
        
        daoUsuario.actualizarUsuario(emisor);
        daoUsuario.actualizarUsuario(usuarioConectado);
    }
    
    /**
     * 
     * @param _nombre
     * @param _descripcion
     * @param _horario
     * @param _dir_foto
     * @param _cod_provincia
     * @throws IOException 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.NuevaEscuelaException.class)
    public void nuevaEscuela(String _nombre, String _descripcion, String _horario, String _dir_foto, Integer _cod_provincia) throws IOException
    {
        Provincia p=daoProvincia.obtenerProvincia(_cod_provincia);
        Escuela e=new Escuela(_nombre, p);
        
        e.setDescripcion(_descripcion);
        e.setHorario(_horario);
        
        if(_dir_foto != null)
        {
            File foto=new File(_dir_foto);
            byte[] _fotoescuela=Files.readAllBytes(foto.toPath());
            e.setFotoEscuela(_fotoescuela);
        }
        
        daoEscuela.guardarEscuela(e);
        
    }
    
    /**
     * 
     * @param _id_escuela
     * @param _orientacion
     * @param _nombre
     * @param _dir_foto
     * @throws IOException 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.NuevoSectorException.class)
    public void nuevoSector(Integer _id_escuela, String _orientacion, String _nombre, String _dir_foto) throws IOException
    {   
        Orientacion o=daoOrientacion.obtenerOrientacion(Orientacion.orientacion.valueOf(_orientacion));
        
        Sector s=new Sector(_nombre, o);
       
        Escuela e=daoEscuela.obtenerEscuela(_id_escuela);
        
        
        s.setEscuela(e);
        s.setNombreSector(_nombre);
        
        if(_dir_foto != null)
        {
            File foto=new File(_dir_foto);
            byte[] _fotosector=Files.readAllBytes(foto.toPath());
            s.setFotoSector(_fotosector);
        }
        
        daoSector.guardarSector(s);
        
        e.getSectores().add(s);
        daoEscuela.actualizarEscuela(e);
    }
    
    /**
     * 
     * @param _id_sector
     * @param _nombre
     * @param _nivel_oficial
     * @param id_mapa 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.NuevaViaException.class)
    public void nuevaVia(Integer _id_sector, String _nombre, String _nivel_oficial, String id_mapa)
    {
        Nivel n=daoNivel.obtenerNivel(Nivel.nivelAsociado.valueOf(_nivel_oficial));
        
        Via v=new Via(_nombre, n);
        
        Sector s=daoSector.obtenerSector(_id_sector);
        
        v.setSector(s);
        v.setNivelConsensuado(n);
        v.setIdv_via(id_mapa);
        
        daoVia.guardarVia(v);
        
        s.getVias().add(v);
        daoSector.actualizarSector(s);
    }
    
    /**
     * 
     * @param _id_via
     * @param _valor_comentario 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.ComentarViaException.class)
    public void comentarVia(Integer _id_via, String _valor_comentario)
    {
        usuarioConectado=daoUsuario.obtenerUsuario(username);
        
        Via v=daoVia.obtenerVia(_id_via);
        
        Comentario c=new Comentario(v, _valor_comentario, usuarioConectado);
        
        usuarioConectado.getComentarios().add(c);
        v.getComentarios().add(c);
        
        daoComentario.guardarComentario(c);
        daoVia.actualizarVia(v);
        daoUsuario.actualizarUsuario(usuarioConectado);
        
    }
    
    /**
     * 
     * @param _id_comentario
     * @param _valor_comentario 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.EditarComentarioException.class)
    public void editarComentario(Integer _id_comentario, String _valor_comentario)
    {   
        usuarioConectado=daoUsuario.obtenerUsuario(username);
        
        Comentario c=daoComentario.obtenerComentario(_id_comentario);
        
        if(!c.getUsuario().getUsername().equals(usuarioConectado.getUsername()))
        {
            throw new ComentarioNoDisponible();
        }
        
        c.setComentario(_valor_comentario);
        
        daoComentario.actualizarComentario(c);
    }
    
    /**
     * 
     * @param _id_comentario 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.EliminarComentarioException.class)
    public void eliminarComentario(Integer _id_comentario)
    {
        usuarioConectado=daoUsuario.obtenerUsuario(username);
        
        Comentario c=daoComentario.obtenerComentario(_id_comentario);
        
        if(!c.getUsuario().getUsername().equals(usuarioConectado.getUsername()))
        {
            throw new ComentarioNoDisponible();
        }
        
        Via v=c.getVia();
        v.getComentarios().remove(c);
        usuarioConectado.getComentarios().remove(c);
        
        c.setUsuario(null);
        c.setVia(null);
        
        daoVia.actualizarVia(v);
        daoComentario.actualizarComentario(c);
        daoComentario.borrarComentario(c);
        daoUsuario.actualizarUsuario(usuarioConectado);
        
    }
    
    /**
     * 
     * @param id_via
     * @param _nivel_valoracion 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.RealizarViaException.class)
    public void realizarVia(Integer id_via, String _nivel_valoracion)
    {
        usuarioConectado=daoUsuario.obtenerUsuario(username);
        
        Nivel n=daoNivel.obtenerNivel(Nivel.nivelAsociado.valueOf(_nivel_valoracion));
        
        Via v=daoVia.obtenerVia(id_via);
        
        if(usuarioConectado.getViasRealizadas().contains(v))
        {
            usuarioConectado.getViasRealizadas().remove(v);
            usuarioConectado.getViasRealizadas().add(v);
            usuarioConectado.setNivel(v.getNivel());
            
            //aquí se debería aplicar el algoritmo de reestimacion colaborativa
            v.setNivelConsensuado(n);
            daoVia.actualizarVia(v);
        }
        else
        {
            usuarioConectado.getViasRealizadas().add(v);
            usuarioConectado.setNivel(v.getNivel());
            
            //aquí se debería aplicar el algoritmo de reestimacion colaborativa
            v.setNivelConsensuado(n);
            daoVia.actualizarVia(v);
        }
        
        daoUsuario.actualizarUsuario(usuarioConectado);
    }
 
    /**
     * 
     * @param _cod_provincia
     * @return 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.EscuelasProvinciaException.class)
    public List<Escuela> escuelasProvincia(Integer _cod_provincia)
    {
        return daoEscuela.obtenerEscuelasProvincia(_cod_provincia);
    }
    
    /**
     * 
     * @param _cod_escuela
     * @return 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.SectoresEscuelaException.class)
    public List<Sector> sectoresEscuela(Integer _cod_escuela)
    {
        daoEscuela.obtenerEscuela(_cod_escuela).getSectores().size();
        List<Sector> sectores=daoEscuela.obtenerEscuela(_cod_escuela).getSectores();
        
        return sectores;
    }
    
    /**
     * 
     * @param _cod_sector
     * @return 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.ViasSectorException.class)
    public List<Via> viasSector(Integer _cod_sector)
    {
        daoSector.obtenerSector(_cod_sector).getVias().size();
        List<Via> vias=daoSector.obtenerSector(_cod_sector).getVias();
        
        return vias;
    }
}
