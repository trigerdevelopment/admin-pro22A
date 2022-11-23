package com.adminpro22.triger.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

    private Long id;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String nombreUsuario;
    @NotNull
    private String email;
    private String photoName;
    @NotNull
    @JsonProperty( value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
