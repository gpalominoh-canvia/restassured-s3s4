package com.canvia.qa.api;

import com.canvia.qa.modelo.Reserva;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Cliente del recurso /booking: los steps hablan de negocio y este cliente sabe de HTTP.
 * Si mañana cambia una ruta o una cabecera, se corrige aquí y en ningún otro lugar.
 */
public class ReservaCliente {

    public Response ping() {
        return given().spec(Especificaciones.base())
                .when().get("/ping");
    }

    /** Acepta un record Reserva o un Map (para enviar cuerpos inválidos). */
    public Response crear(Object cuerpo) {
        return given().spec(Especificaciones.base())
                .body(cuerpo)
                .when().post("/booking");
    }

    public Response consultar(int id) {
        return given().spec(Especificaciones.base())
                .pathParam("id", id)
                .when().get("/booking/{id}");
    }

    public Response buscarPorApellido(String apellido) {
        return given().spec(Especificaciones.base())
                .queryParam("lastname", apellido)
                .when().get("/booking");
    }

    public Response actualizar(int id, Reserva reserva, String token) {
        return conToken(token)
                .pathParam("id", id)
                .body(reserva)
                .when().put("/booking/{id}");
    }

    public Response actualizarParcial(int id, Map<String, Object> cambios, String token) {
        return conToken(token)
                .pathParam("id", id)
                .body(cambios)
                .when().patch("/booking/{id}");
    }

    public Response eliminar(int id, String token) {
        return conToken(token)
                .pathParam("id", id)
                .when().delete("/booking/{id}");
    }

    /** Restful Booker recibe el token en la cookie "token". Sin token, no hay cookie. */
    private RequestSpecification conToken(String token) {
        RequestSpecification peticion = given().spec(Especificaciones.base());
        if (token != null && !token.isBlank()) {
            peticion.cookie("token", token);
        }
        return peticion;
    }
}