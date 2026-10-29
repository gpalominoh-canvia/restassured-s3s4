# language: es
Característica: Autenticación y cambios protegidos
  Como administrador del hotel
  Quiero que solo quien tiene un token pueda modificar reservas
  Para que nadie altere una estadía ajena

  Antecedentes:
    Dado que la API de reservas está disponible

  @smoke @seguridad @limpieza
  Escenario: El administrador obtiene un token
    Cuando me autentico como administrador
    Entonces el código de respuesta es 200
    Y recibo un token de acceso
  
  @bug @seguridad @limpieza 
  Escenario: El administrador actualiza el precio
    Dado que estoy autenticado como administrador
    Y que existe una reserva a nombre de "Carlos"
    Cuando actualizo el precio de esa reserva a 999
    Entonces el código de respuesta es 200
    Y el precio de la reserva es 999