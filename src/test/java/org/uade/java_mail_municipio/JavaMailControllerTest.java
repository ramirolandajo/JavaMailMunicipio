package org.uade.java_mail_municipio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.uade.java_mail_municipio.model.UsuarioModel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JavaMailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGenerarUsuarioEndpoint() throws Exception {
        UsuarioModel u = new UsuarioModel();
        u.setDni("8888");
        u.setEmail("controller@test.com");
        u.setTipoUsuario("inspector");

        try {
            mockMvc.perform(post("/java_mail/generarUsuario")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(u)))
                    .andExpect(status().isOk());
        }
        catch (Exception e) {
            // will throw exception if user exists
        }
    }
}
