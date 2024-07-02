package org.uade.java_mail_municipio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    @Id
    private String dni; //usuario

    @JsonIgnore
    private String password;

    private String email;

    @Column(name = "tipo_usuario")
    private String tipoUsuario;

    @Lob
    @JsonIgnore
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagenPerfil;

    @JsonIgnore
    @Column(name = "cambios_en_reclamos")
    private boolean cambiosEnReclamos;
}
