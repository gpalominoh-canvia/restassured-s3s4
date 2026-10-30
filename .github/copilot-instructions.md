# Proyecto: pruebas de API con REST Assured + Cucumber (Java 17, Maven)

API bajo prueba: Restful Booker (https://restful-booker.herokuapp.com).

## Convenciones
- Todo en español: features, steps, métodos, variables y mensajes.
- Gherkin declarativo: qué hace el usuario, no cómo (nada de URLs, JSON ni códigos internos en los pasos, salvo el código de respuesta).
- Cada escenario crea sus propios datos; nunca depende de una reserva que ya exista.
- Los steps no llaman a `given()`: usan los clientes de `com.canvia.qa.api` (ReservaCliente, AutenticacionCliente).
- La respuesta se guarda en `Contexto`; los steps de verificación comunes están en `RespuestaSteps`.
- Validar contenido, no solo el código de respuesta.
- Credenciales y URL solo desde `Configuracion` (propiedades del pom o -D). Nunca en un `.java`.

## Etiquetas
- `@smoke`: lo mínimo en cada PR. `@regresion`: todo lo que ya funciona.
- `@negativo`, `@seguridad`, `@contrato`: tipo de prueba.
- `@bug`: describe lo correcto y hoy falla; no corre por defecto.
- `@limpieza`: borra lo creado al terminar.

## Antes de dar algo por terminado
- `mvn test` en verde.
- Si un escenario nuevo falla por un bug real de la API: `@bug`, no cambiar la aserción.