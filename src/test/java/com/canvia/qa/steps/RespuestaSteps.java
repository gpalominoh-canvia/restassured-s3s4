package com.canvia.qa.steps;

import static org.hamcrest.Matchers.notNullValue;
import com.canvia.qa.support.Contexto;
import io.cucumber.java.es.Entonces;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

/** Verificaciones que sirven para cualquier respuesta, venga del recurso que venga. */
public class RespuestaSteps {

    private final Contexto contexto;

    public RespuestaSteps(Contexto contexto) {
        this.contexto = contexto;
    }

    
    @Entonces("el código de respuesta es {int}")
    public void codigoDeRespuesta(int codigo) {
        contexto.respuesta.then().statusCode(codigo);
    }
    
    @Entonces("la respuesta contiene el campo {string}")
    public void contieneCampo(String campo) {
        contexto.respuesta.then().body(campo, notNullValue());
    }

    /** Valida la FORMA de la respuesta: campos obligatorios, tipos y formatos. */
    @Entonces("la respuesta cumple el contrato {string}")
    public void cumpleContrato(String contrato) {
        contexto.respuesta.then()
                .body(matchesJsonSchemaInClasspath("schemas/" + contrato + ".json"));
    }

    @Entonces("la respuesta llega en menos de {int} segundos")
    public void llegaEnMenosDe(int segundos) {
        contexto.respuesta.then().time(lessThan(segundos * 1000L));
    }

}