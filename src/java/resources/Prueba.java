/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniel
 */
@RestController
@RequestMapping("/")
public class Prueba
{
    @RequestMapping(value = "/prueba", method = RequestMethod.GET)
    public String prueba()
    {
        return "hola";
    }
}
