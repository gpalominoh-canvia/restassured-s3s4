package com.canvia.qa.api;

import com.canvia.qa.modelo.Credenciales;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

/** Todo lo que se puede hacer con el recurso /auth. */
public class AutenticacionCliente {

    public Response autenticar(String usuario, String clave) {
        return given()
                .spec(Especificaciones.base())
                .body(new Credenciales(usuario, clave))   // record → JSON
            .when()
                .post("/auth");
    }

    /** Devuelve un token válido o falla con un mensaje claro. */
    public String obtenerToken(String usuario, String clave) {
        return autenticar(usuario, clave)
            .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract().path("token");
    }
}