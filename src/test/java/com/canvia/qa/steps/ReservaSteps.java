package com.canvia.qa.steps;

import com.canvia.qa.api.ReservaCliente;
import com.canvia.qa.modelo.Reserva;
import com.canvia.qa.modelo.ReservaCreada;
import com.canvia.qa.support.Contexto;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

public class ReservaSteps {

    private final Contexto contexto;
    private final ReservaCliente reservas;

    // PicoContainer crea el Contexto y el cliente, y los inyecta aquí.
    public ReservaSteps(Contexto contexto, ReservaCliente reservas) {
        this.contexto = contexto;
        this.reservas = reservas;
    }

    // ---------- Preparar ----------

    @Dado("que la API de reservas está disponible")
    public void apiDisponible() {
        reservas.ping().then().statusCode(201);
    }

    @Dado("que existe una reserva a nombre de {string}")
    public void existeReserva(String nombre) {
        crearValida(Reserva.de(nombre, "Prueba", 100));
    }

    // ---------- Crear ----------

    @Cuando("creo una reserva para {string} {string} por {int} soles")
    public void creoReserva(String nombre, String apellido, int precio) {
        contexto.reservaEnviada = Reserva.de(nombre, apellido, precio);
        contexto.respuesta = reservas.crear(contexto.reservaEnviada);
        registrarSiSeCreo();
    }

    // ---------- Consultar ----------

    @Cuando("consulto esa reserva")
    public void consultoReserva() {
        contexto.respuesta = reservas.consultar(contexto.idReserva);
    }

    // ---------- Actualizar ----------

    @Cuando("actualizo el precio de esa reserva a {int}")
    public void actualizoPrecio(int precio) {
        contexto.respuesta = reservas.actualizar(
                contexto.idReserva, contexto.reservaEnviada.conPrecio(precio), contexto.token);
    }

    // ---------- Eliminar ----------

    @Cuando("elimino la reserva con id {int} sin autenticarme")
    public void eliminoSinToken(int id) {
        contexto.respuesta = reservas.eliminar(id, null);
    }

    // ---------- Verificar ----------

    @Entonces("el nombre de la reserva creada es {string}")
    public void nombreCreado(String nombre) {
        contexto.respuesta.then()
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo(nombre));
    }

    @Entonces("el precio de la reserva es {int}")
    public void precioEs(int precio) {
        contexto.respuesta.then().body("totalprice", equalTo(precio));
    }

    // ---------- Apoyo ----------

    /** Crea una reserva que DEBE crearse bien y guarda su id en el Contexto. */
    private void crearValida(Reserva reserva) {
        contexto.reservaEnviada = reserva;
        contexto.respuesta = reservas.crear(reserva);
        ReservaCreada creada = contexto.respuesta.then()
                .statusCode(200)
                .extract().as(ReservaCreada.class);   // JSON → record
        contexto.idReserva = creada.id();
        contexto.reservasCreadas.add(creada.id());
    }

    /** Si la API creó algo (aunque no debía), lo anotamos para limpiarlo. */
    private void registrarSiSeCreo() {
        Response r = contexto.respuesta;
        if (r.statusCode() == 200 && r.path("bookingid") != null) {
            contexto.idReserva = r.path("bookingid");
            contexto.reservasCreadas.add(contexto.idReserva);
        }
    }

    
    @Cuando("consulto la reserva con id {int}")
    public void consultoPorId(int id) {
        contexto.respuesta = reservas.consultar(id);
    }

    
    @Cuando("intento {word} esa reserva sin autenticarme")
    public void intentoSinToken(String operacion) {
        int id = contexto.idReserva;
        Reserva reserva = contexto.reservaEnviada;
        Map<String, Object> cambio = Map.of("additionalneeds", "Nada");
        contexto.respuesta = switch (operacion) {
            case "actualizar" -> reservas.actualizar(id, reserva, null);
            case "modificar" -> reservas.actualizarParcial(id, cambio, null);
            case "eliminar" -> reservas.eliminar(id, null);
            default -> throw new IllegalArgumentException("Operación desconocida: " + operacion);
        };
    }
}