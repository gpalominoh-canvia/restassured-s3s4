package com.canvia.qa.support;

import com.canvia.qa.api.AutenticacionCliente;
import com.canvia.qa.api.ReservaCliente;
import io.cucumber.java.After;

public class Hooks {

    private final Contexto contexto;
    private final ReservaCliente reservas;
    private final AutenticacionCliente autenticacion;

    public Hooks(Contexto contexto, ReservaCliente reservas, AutenticacionCliente autenticacion) {
        this.contexto = contexto;
        this.reservas = reservas;
        this.autenticacion = autenticacion;
    }

    /**
     * Borra lo que el escenario creó, aunque el escenario haya fallado.
     * No valida la respuesta: si la reserva ya se borró en el escenario, da igual.
     */
    @After("@limpieza")
    public void borrarReservasCreadas() {
        if (contexto.reservasCreadas.isEmpty()) {
            return;
        }
        String token = contexto.token != null
                ? contexto.token
                : autenticacion.obtenerToken(Configuracion.usuario(), Configuracion.clave());
        contexto.reservasCreadas.forEach(id -> reservas.eliminar(id, token));
    }
}