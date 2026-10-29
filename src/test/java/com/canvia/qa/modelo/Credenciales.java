package com.canvia.qa.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Cuerpo de POST /auth. */
public record Credenciales(
        @JsonProperty("username") String usuario,
        @JsonProperty("password") String clave) {
}