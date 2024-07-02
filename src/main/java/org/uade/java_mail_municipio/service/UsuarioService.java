package org.uade.java_mail_municipio.service;

import org.uade.java_mail_municipio.model.UsuarioModel;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.uade.java_mail_municipio.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public String guardarUsuario(UsuarioModel newUsuario) throws Exception {
        Optional<UsuarioModel> usuarioOp = this.usuarioRepository.findById(newUsuario.getDni());
        if (usuarioOp.isPresent()) {
            throw new Exception("El usuario que esta intentando crear ya se encuentra en la base de datos");
        }
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        RandomStringGenerator generator = RandomStringGenerator.builder().selectFrom(chars).build();

        String randomPassword = generator.generate(16); //genera un string random de 10 caracteres

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(randomPassword);
        newUsuario.setPassword(encodedPassword);
        newUsuario.setCambiosEnReclamos(false);
        this.usuarioRepository.save(newUsuario);
        return randomPassword;
    }
}
