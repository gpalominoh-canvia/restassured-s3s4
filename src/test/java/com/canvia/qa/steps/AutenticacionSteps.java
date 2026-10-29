package com.canvia.qa.steps;

import com.canvia.qa.api.AutenticacionCliente;
import com.canvia.qa.support.Configuracion;
import com.canvia.qa.support.Contexto;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static org.hamcrest.Matchers.matchesPattern;

public class AutenticacionSteps {

    private final Contexto contexto;
    private final AutenticacionCliente autenticacion;

    public AutenticacionSteps(Contexto contexto, AutenticacionCliente autenticacion) {
        this.contexto = contexto;
        this.autenticacion = autenticacion;
    }

    /** Usuario y clave NO están en el código: vienen de Configuracion. */
    @Dado("que estoy autenticado como administrador")
    public void autenticadoComoAdministrador() {
        contexto.token = autenticacion.obtenerToken(Configuracion.usuario(), Configuracion.clave());
    }

    @Cuando("me autentico como administrador")
    public void meAutentico() {
        contexto.respuesta = autenticacion.autenticar(Configuracion.usuario(), Configuracion.clave());
    }

    @Entonces("recibo un token de acceso")
    public void reciboToken() {
        contexto.respuesta.then().body("token", matchesPattern("^[a-z0-9]{10,}$"));
    }
}