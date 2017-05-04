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
import dao.DAOToken;
import dao.DAOUsuario;
import dao.DAOVia;
import exceptionsBusiness.ComentarioNoDisponible;
import exceptionsBusiness.ErrorEnvioEmail;
import exceptionsBusiness.PeticionYaEnviada;
import exceptionsBusiness.TokenCaducado;
import exceptionsBusiness.UsernameNoDisponible;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import modelo.Comentario;
import modelo.Escuela;
import modelo.Provincia;
import modelo.Nivel;
import modelo.Orientacion;
import modelo.PeticionAmistad;
import modelo.Sector;
import modelo.Token;
import modelo.Usuario;
import modelo.Via;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;
import java.util.Base64;

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
    private DAOToken daoToken;
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
    public DAOToken getDaoToken() 
    {
        return daoToken;
    }

    /**
     * 
     * @param daoToken 
     */
    public void setDaoToken(DAOToken daoToken)
    {
        this.daoToken = daoToken;
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
     * @param _email
     * @param _password 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.ComentariosViaException.class)
    public void solicitarAcceso(String _username, String _email, String _password)
    {
        if(daoUsuario.obtenerUsuario(_username) != null)
        {
            throw new exceptionsBusiness.UsernameNoDisponible();
        }
        
        String hash = BCrypt.hashpw(_password, BCrypt.gensalt());
        
        Token token = new Token(_username, _email, hash);
        daoToken.guardarToken(token);

        //enviar token de acceso a la direccion email
        
        String correoEnvia = "skala2climbing@gmail.com";
        String claveCorreo = "vNspLa5H";

        // La configuración para enviar correo
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);

        // Obtener la sesion
        Session session = Session.getInstance(properties, null);

        try {
            // Crear el cuerpo del mensaje
            MimeMessage mimeMessage = new MimeMessage(session);

            // Agregar quien envía el correo
            mimeMessage.setFrom(new InternetAddress(correoEnvia, "Skala2Climbing"));

            // Los destinatarios
            InternetAddress[] internetAddresses = 
            {
                new InternetAddress(token.getEmail())
            };

            // Agregar los destinatarios al mensaje
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    internetAddresses);

            // Agregar el asunto al correo
            mimeMessage.setSubject("Confirmación de registro");

            // Creo la parte del mensaje
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText("Confirme su registro pulsando en el siguiente enlace: http://localhost:8383/redsocialcolaborativaclientangularjs/confirmacion.html?"+"token="+token.getToken());
            //mimeBodyPart.setText("Confirme su registro pulsando en el siguiente enlace: "+"Enlace aún no disponible");

            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            // Agregar el multipart al cuerpo del mensaje
            mimeMessage.setContent(multipart);

            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

        } 
        catch (UnsupportedEncodingException | MessagingException ex) 
        {
            throw new ErrorEnvioEmail();
        }
        
    }
    
    /**
     * 
     * @param _token
     * @throws NoSuchAlgorithmException 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.AltaUsuarioException.class)
    public void altaUsuario(String _token) throws NoSuchAlgorithmException
    {
        Token token=daoToken.obtenerToken(_token);

        //comprobar caducidad del token y lanzar exception
        Calendar fecha_actual=Calendar.getInstance();
        Calendar fecha_token=token.getFecha();
        
        long diferencia=fecha_token.getTimeInMillis()-fecha_actual.getTimeInMillis();
        
        if(diferencia > 1800000)
        {
            throw new TokenCaducado();
        }
        
        try
        {
            daoUsuario.guardarUsuario(new Usuario(token.getUsername(), token.getPassword(), token.getEmail()));
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
        
        if(dir_foto != null)
        {
            dir_foto=dir_foto.substring(23);
            byte[] _fotoperfil = Base64.getDecoder().decode(dir_foto);
            usuarioConectado.setFotoperfil(_fotoperfil);   
            
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
    public void nuevaVia(Integer _id_sector, String _nombre, Nivel _nivel_oficial, String id_mapa)
    {
        Nivel n=daoNivel.obtenerNivel(_nivel_oficial.getNivelAsociado());
        
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
     * @param _valoracion 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.RealizarViaException.class)
    public void realizarVia(Integer id_via, Nivel _nivel_valoracion, Integer _valoracion)
    {   
        Via v=daoVia.obtenerVia(id_via);
        usuarioConectado=daoUsuario.obtenerUsuario(username);
        
        if(usuarioConectado.getViasRealizadas().contains(v))
        {
            usuarioConectado.getViasRealizadas().remove(v);
        }
        
        usuarioConectado.getViasRealizadas().add(v);           
            
        //media puntuaciones
        double media_valoracion=(v.getValoracion_media()*v.getContador()+_valoracion)/(v.getContador()+1);
        v.setValoracion_media(media_valoracion);
        v.setContador(v.getContador()+1);
        
        if((media_valoracion - ((int)media_valoracion)) >= 0.5)
        {
            v.setEstrellas((int)media_valoracion + 1);
        }
        else
        {
            v.setEstrellas((int)media_valoracion);
        }
        
        //aquí se debería aplicar el algoritmo de reestimacion colaborativa
        //ponderacion media segun su nivel medio de las últimas 10 vias realizadas
        int suma_nivel_usuario=0;
        double media_nivel_usuario;
        
        if(usuarioConectado.getViasRealizadas().size() < 10)
        {       
            if(usuarioConectado.getViasRealizadas().isEmpty())
            {
                Nivel n=daoNivel.obtenerNivel(v.getNivel().getNivelAsociado());
                usuarioConectado.setNivel(n);
                media_nivel_usuario=v.getNivel().getNivelAsociado().ordinal();
            }
            else
            {
                for (Via via : usuarioConectado.getViasRealizadas()) 
                {
                    suma_nivel_usuario = suma_nivel_usuario + via.getNivel().getNivelAsociado().ordinal();
                }
                media_nivel_usuario = (double) ((double) (suma_nivel_usuario) / (double) (usuarioConectado.getViasRealizadas().size()));
                
                if((media_nivel_usuario - (int) media_nivel_usuario) >= 0.5)
                {
                    Nivel n=new Nivel(Nivel.nivelAsociado.values()[(int) media_nivel_usuario+1]);
                    usuarioConectado.setNivel(n);
                }
                else
                {
                    Nivel n=new Nivel(Nivel.nivelAsociado.values()[(int) media_nivel_usuario]);
                    usuarioConectado.setNivel(n);
                }
            } 
        }
        else
        {
            int contador=0;
            int pos=usuarioConectado.getViasRealizadas().size()-1;
            
            while(contador != 10)
            {
                suma_nivel_usuario=suma_nivel_usuario+usuarioConectado.getViasRealizadas().get(pos).getNivel().getNivelAsociado().ordinal();
                pos--;
                contador++;
            }
            media_nivel_usuario = (double) ((double) (suma_nivel_usuario) / (double) (usuarioConectado.getViasRealizadas().size()));
            
            if((media_nivel_usuario - (int) media_nivel_usuario) >= 0.5)
            {
                Nivel n=new Nivel(Nivel.nivelAsociado.values()[(int) media_nivel_usuario+1]);
                usuarioConectado.setNivel(n);
            }
            else
            {
                Nivel n=new Nivel(Nivel.nivelAsociado.values()[(int) media_nivel_usuario]);
                usuarioConectado.setNivel(n);
            }
        }
        
        //calculo del porcentaje de fiabilidad
        double porcentaje_fiabilidad=((100 * media_nivel_usuario)/29.0);
        
        //ese porcentaje aumenta hasta un 20% según sea más o menos conocido
        if(usuarioConectado.getAmigos().size() >= 0 && usuarioConectado.getAmigos().size() < 10)
        {
            porcentaje_fiabilidad=porcentaje_fiabilidad+0;
        }
        if(usuarioConectado.getAmigos().size() >= 10 && usuarioConectado.getAmigos().size() < 20)
        {
            porcentaje_fiabilidad=porcentaje_fiabilidad+5;
        }
        if(usuarioConectado.getAmigos().size() >= 20 && usuarioConectado.getAmigos().size() < 50)
        {
            porcentaje_fiabilidad=porcentaje_fiabilidad+10;
        }
        if(usuarioConectado.getAmigos().size() >= 50 && usuarioConectado.getAmigos().size() < 100)
        {
            porcentaje_fiabilidad=porcentaje_fiabilidad+15;
        }
        if(usuarioConectado.getAmigos().size() >= 100)
        {
            porcentaje_fiabilidad=porcentaje_fiabilidad+20;
        }
        
        //puede aumentar conforme más vías vaya publicando hasta en un 20%
        if(usuarioConectado.getViasRealizadas().size() >= 0 && usuarioConectado.getViasRealizadas().size() < 10)
        {
            porcentaje_fiabilidad=porcentaje_fiabilidad+0;
        }
        if(usuarioConectado.getViasRealizadas().size() >= 10 && usuarioConectado.getViasRealizadas().size() < 20)
        {
            porcentaje_fiabilidad=porcentaje_fiabilidad+5;
        }
        if(usuarioConectado.getViasRealizadas().size() >= 20 && usuarioConectado.getViasRealizadas().size() < 50)
        {
            porcentaje_fiabilidad=porcentaje_fiabilidad+10;
        }
        if(usuarioConectado.getViasRealizadas().size() >= 50 && usuarioConectado.getViasRealizadas().size() < 100)
        {
            porcentaje_fiabilidad=porcentaje_fiabilidad+15;
        }
        if(usuarioConectado.getViasRealizadas().size() >= 100)
        {
            porcentaje_fiabilidad=porcentaje_fiabilidad+20;
        }
        
        //calculo del la media colaborativa
        double tanto_fiabilidad=porcentaje_fiabilidad/100;
        double puntuacion_total_nivel;
        double ponderacion_actual_puntuacion=(1/((double)v.getContador()));
        double ponderacion_usuario=ponderacion_actual_puntuacion*tanto_fiabilidad;
        double ponderacion_general=1.0-ponderacion_usuario;
        
        puntuacion_total_nivel=v.getValoracion_media_nivel()*ponderacion_general+_nivel_valoracion.getNivelAsociado().ordinal()*ponderacion_usuario;
        v.setValoracion_media_nivel(puntuacion_total_nivel);
        
        if(puntuacion_total_nivel - (int)puntuacion_total_nivel >= 0.5)
        {
            Nivel n=new Nivel(Nivel.nivelAsociado.values()[(int) puntuacion_total_nivel + 1]);
            v.setNivelConsensuado(n);
        }
        else
        {
            Nivel n=new Nivel(Nivel.nivelAsociado.values()[(int) puntuacion_total_nivel]);
            v.setNivelConsensuado(n);
        }
        
        daoVia.actualizarVia(v);
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
    
    /**
     * 
     * @param _cod_via
     * @return 
     */
    @Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = transactionalBusinessException.ComentariosViaException.class)
    public List<Comentario> comentariosVia(Integer _cod_via)
    {
        daoVia.obtenerVia(_cod_via).getComentarios().size();
        List<Comentario> comentarios=daoVia.obtenerVia(_cod_via).getComentarios();
        
        return comentarios;
    }
   
}
