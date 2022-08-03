package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//La classe controle l'url
/**
 *Un controller:
 * Reçoit des requetes
 * manao traitement
 * mamerina reponse
 */

@RestController
public class HelloWorldController {
    @GetMapping({"/hello/{name}", "/hello/"})
    //@GetMapping("/")
    public String helloWorld(@PathVariable(required = false) String name){
        //return "Hello World";
        if (name == null){
          return "Hello world";
        }else{
            return "Hello "+name;
        }
    }
}
