package org.uade.java_mail_municipio.controller;

import lombok.extern.slf4j.Slf4j;
import org.uade.java_mail_municipio.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uade.java_mail_municipio.service.UsuarioService;

@Slf4j
@RestController
@RequestMapping(path = "/java_mail")
public class JavaMailController {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UsuarioService usuarioService;

    private String generatedPassword;

    @PostMapping(path = "/send_new_user")
    public ResponseEntity<String> sendMail(@RequestBody UsuarioModel newUser) {
        try {
            generatedPassword = this.usuarioService.guardarUsuario(newUser);
            sendEmail(newUser.getEmail());
            return new ResponseEntity<>("Usuario generado correctamente! Mail enviado con password: " + generatedPassword, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void sendEmail(String mailDestino) {
        SimpleMailMessage msj = new SimpleMailMessage();
        msj.setFrom("lacunish@gmail.com");
        msj.setTo(mailDestino);
        msj.setSubject("Activacion de cuenta de municipio.");
        msj.setText("Tu cuenta ha sido activada! Tu contrasenia para iniciar sesion a la aplicacion por unica vez es: "
                + generatedPassword);

        mailSender.send(msj);
        log.info("Mail enviado exitosamente");
    }

}
