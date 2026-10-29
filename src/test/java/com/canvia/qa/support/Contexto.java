package com.canvia.qa.support;

import io.restassured.response.Response;
import com.canvia.qa.modelo.Reserva;
import java.util.ArrayList;
import java.util.List;


/** Estado compartido entre los steps de un mismo escenario. */
public class Contexto {
    public String token;
    /** Lo que se envió, para compararlo con lo que la API guardó. */
    public Reserva reservaEnviada;
    public Response respuesta;
    public Integer idReserva;
     /** Ids creados por el escenario: el hook @After("@limpieza") los borra. */
    public final List<Integer> reservasCreadas = new ArrayList<>();
}