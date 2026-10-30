---
description: Agrega un escenario de API con su step y su cliente, siguiendo las convenciones del proyecto
agent: agent
---
Agrega un escenario nuevo para este comportamiento de la API: ${input:comportamiento:qué debe pasar}

Pasos:
1. Elige el .feature que corresponde en src/test/resources/features (o crea uno si es otro recurso).
2. Escribe el escenario en Gherkin declarativo, con sus etiquetas.
3. Reutiliza los steps que ya existen; si falta uno, agrégalo en la clase de steps correcta usando los clientes de com.canvia.qa.api.
4. Ejecuta `mvn test "-Dcucumber.filter.tags=<etiqueta del escenario>"` y corrige hasta que quede en verde.
5. Si falla por un bug real de la API, márcalo con @bug y explícame por qué.

Al final muéstrame qué archivos cambiaste.