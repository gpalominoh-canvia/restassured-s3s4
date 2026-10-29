# language: es
@regresion @limpieza @negativo
Característica: Casos negativos que la API ya maneja bien
  Como responsable de la seguridad de las reservas
  Quiero comprobar que la API rechaza lo que no corresponde
  Para que un error de uso no termine en datos alterados

  Antecedentes:
    Dado que la API de reservas está disponible

  @seguridad
  Esquema del escenario: Cambiar una reserva exige autenticación (<operación>)
    Dado que existe una reserva a nombre de "Pedro"
    Cuando intento <operación> esa reserva sin autenticarme
    Entonces el código de respuesta es 403

    Ejemplos:
      | operación  |
      | actualizar |
      | modificar  |
      | eliminar   |

  Esquema del escenario: Pedir una reserva que no existe (<caso>)
    Cuando consulto la reserva con id <id>
    Entonces el código de respuesta es 404

    Ejemplos:
      | caso                 | id        |
      | id cero              | 0         |
      | id negativo          | -1        |
      | id que nunca existió | 999999999 |