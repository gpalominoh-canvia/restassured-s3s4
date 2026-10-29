package com.canvia.qa.api;

import com.canvia.qa.support.Configuracion;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


/** Lo que TODA petición comparte, escrito una sola vez. */
public final class Especificaciones {
    private Especificaciones() {
    }
    public static RequestSpecification base() {
        return new RequestSpecBuilder()
                .setBaseUri(Configuracion.baseUrl())   // por ambiente: -DbaseUrl=...
                .setContentType(ContentType.JSON)      // lo que enviamos
                .setAccept("application/json")         // lo que pedimos (evita el 418)
                // log de petición y respuesta, solo si una validación falla
                .setConfig(RestAssuredConfig.config().logConfig(LogConfig.logConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails()))
                .build();
    }
}
