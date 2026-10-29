package com.canvia.qa.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Respuesta de POST /booking: { "bookingid": 123, "booking": { ... } } */
public record ReservaCreada(
        @JsonProperty("bookingid") int id,
        @JsonProperty("booking") Reserva reserva) {
}