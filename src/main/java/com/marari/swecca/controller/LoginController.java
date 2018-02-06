package com.marari.swecca.controller;


import com.marari.swecca.model.Usuario;
import com.marari.swecca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public Usuario autenticar (@RequestParam("email")String email, @RequestParam("senha")String senha){

            Usuario usuario = usuarioService.buscarPorEmailSenha(email,senha);

            if (usuario != null){
                return usuario;
            }else return null;
}}
