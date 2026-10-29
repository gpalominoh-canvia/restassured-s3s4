package com.canvia.qa.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Una reserva de Restful Booker como objeto Java (record: inmutable, con equals y toString). */
@JsonInclude(JsonInclude.Include.NON_NULL)       // no envía los campos nulos
@JsonIgnoreProperties(ignoreUnknown = true)      // si la API agrega campos, no rompemos
public record Reserva(
        @JsonProperty("firstname") String nombre,
        @JsonProperty("lastname") String apellido,
        @JsonProperty("totalprice") Integer precio,
        @JsonProperty("depositpaid") Boolean depositoPagado,
        @JsonProperty("bookingdates") Fechas fechas,
        @JsonProperty("additionalneeds") String necesidades) {

    /** Una reserva válida de ejemplo para el huésped indicado. */
    public static Reserva de(String nombre, String apellido, int precio) {
        return new Reserva(nombre, apellido, precio, true,
                new Fechas("2026-11-01", "2026-11-05"), "Desayuno");
    }

    public Reserva conPrecio(Integer nuevoPrecio) {
        return new Reserva(nombre, apellido, nuevoPrecio, depositoPagado, fechas, necesidades);
    }

    public Reserva conNecesidades(String nuevas) {
        return new Reserva(nombre, apellido, precio, depositoPagado, fechas, nuevas);
    }
}