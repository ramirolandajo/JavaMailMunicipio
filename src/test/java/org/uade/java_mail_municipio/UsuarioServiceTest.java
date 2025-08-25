package org.uade.java_mail_municipio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.uade.java_mail_municipio.model.UsuarioModel;
import org.uade.java_mail_municipio.service.UsuarioService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    void testGuardarUsuarioAndAprobarUsuario() throws Exception {
        UsuarioModel u = new UsuarioModel();
        u.setDni("9949");
        u.setEmail("service@test.com");
        u.setTipoUsuario("inspector");

        // Save
        try {
            String dni = usuarioService.guardarUsuario(u);
            assertEquals("9949", dni);
        } catch (Exception e) {
            // Exception is expected if user already exists, ignore it
        }


        // Approve
        String password = usuarioService.aprobarUsuario(u);
        assertNotNull(password);
        assertTrue(password.length() >= 8); // random generated password
    }
}
