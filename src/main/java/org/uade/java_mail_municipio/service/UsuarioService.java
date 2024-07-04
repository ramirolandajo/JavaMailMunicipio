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


    public String aprobarUsuario(UsuarioModel usuario) throws Exception {
        Optional<UsuarioModel> usuarioOp = this.usuarioRepository.findById(usuario.getDni());
        if (usuarioOp.isEmpty()) {
            throw new Exception("El usuario no se encuentra en la base de datos");
        }
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        RandomStringGenerator generator = RandomStringGenerator.builder().selectFrom(chars).build();

        String randomPassword = generator.generate(16);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(randomPassword);
        usuario.setPassword(encodedPassword);
        usuario.setEsperaConfirmacion(false);
        this.usuarioRepository.save(usuario);
        return randomPassword;
    }

    public String guardarUsuario(UsuarioModel usuario) throws Exception {
        Optional<UsuarioModel> usuarioOp = this.usuarioRepository.findById(usuario.getDni());
        if (usuarioOp.isPresent()) {
            throw new Exception("El usuario ya se encuentra en la base de datos");
        }
        if (usuario.getTipoUsuario().equals("inspector")) {
            usuario.setEsperaConfirmacion(false);
        }
        else {
            usuario.setEsperaConfirmacion(true);
        }
        usuario.setCambiosEnReclamos(false);
        usuario.setCambiosEnDenuncias(false);
        this.usuarioRepository.save(usuario);
        return usuario.getDni();
    }
}
