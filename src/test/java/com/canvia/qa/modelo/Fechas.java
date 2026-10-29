package com.canvia.qa.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Fechas de la estadía. Jackson usa el nombre de @JsonProperty en el JSON. */
public record Fechas(
        @JsonProperty("checkin") String llegada,
        @JsonProperty("checkout") String salida) {
}