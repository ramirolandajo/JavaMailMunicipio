package org.uade.java_mail_municipio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.uade.java_mail_municipio.model.UsuarioModel;
import org.uade.java_mail_municipio.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void testGuardarYEncontrarUsuario() {
        UsuarioModel u = new UsuarioModel();
        u.setDni("12345");
        u.setEmail("test@test.com");
        u.setTipoUsuario("inspector");
        u.setEsperaConfirmacion(true);

        usuarioRepository.save(u);

        UsuarioModel found = usuarioRepository.findById("12345").orElse(null);

        assertNotNull(found);
        assertEquals("test@test.com", found.getEmail());
        assertTrue(found.isEsperaConfirmacion());
    }
}
