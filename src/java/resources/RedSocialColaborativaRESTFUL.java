/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import bean.RedSocial;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UsuarioDTO;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
     * @param _usuarioJSON 
     * @throws java.io.IOException 
     */
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public void altaUsuario(@RequestBody String _usuarioJSON) throws IOException
    {
        UsuarioDTO _usuario;
        
        ObjectMapper mapper=new ObjectMapper();
        _usuario=mapper.readValue(_usuarioJSON, UsuarioDTO.class);
        
        try
        {
            red.altaUsuario(_usuario.getUsername(), _usuario.getPassword(), _usuario.getEmail());
        }
        catch(Exception e)
        {
            throw new exceptionsBusiness.UsernameNoDisponible();
        }
        
    }
    
}
