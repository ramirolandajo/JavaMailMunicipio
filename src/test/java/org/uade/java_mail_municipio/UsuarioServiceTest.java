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
        u.setDni("9999");
        u.setEmail("service@test.com");
        u.setTipoUsuario("inspector");

        // Save
        String dni = usuarioService.guardarUsuario(u);
        assertEquals("9999", dni);

        // Approve
        String password = usuarioService.aprobarUsuario(u);
        assertNotNull(password);
        assertTrue(password.length() >= 8); // random generated password
    }
}
