# language: es
@contrato
Característica: Contrato de las respuestas
  Como equipo que consume la API de reservas
  Quiero que cada respuesta tenga siempre la misma forma
  Para que nuestras pantallas no se rompan cuando la API cambie

  Antecedentes:
    Dado que la API de reservas está disponible

  @smoke @limpieza
  Escenario: Una reserva consultada cumple el contrato y llega rápido
    Dado que existe una reserva a nombre de "Inés"
    Cuando consulto esa reserva
    Entonces el código de respuesta es 200
    Y la respuesta cumple el contrato "reserva"
    Y la respuesta llega en menos de 5 segundos