/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import bean.RedSocial;
import dto.UsuarioDTO;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST, consumes = "application/json")
    public void altaUsuario(@RequestBody UsuarioDTO _usuario)
    {
        try
        {
            red.altaUsuario(_usuario.getUsername(), _usuario.getPassword(), _usuario.getEmail());
        }
        catch(exceptionsBusiness.UsernameNoDisponible e)
        {
            //enviar codigo de error
        }
    }
}
