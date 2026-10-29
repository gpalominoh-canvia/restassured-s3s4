package com.canvia.qa.support;

/**
 * Lee la configuración de la ejecución. Nada de URLs ni credenciales escritas en el código.
 * Prioridad: variable de entorno (secretos de la CI) y luego propiedad del sistema (pom.xml o -D...).
 */
public final class Configuracion {

    private Configuracion() {
    }

    public static String usuario() {
        return valor("api.usuario", "API_USUARIO");
    }

    public static String clave() {
        return valor("api.clave", "API_CLAVE");
    }

    private static String valor(String propiedad, String variable) {
        String deEntorno = System.getenv(variable);
        if (deEntorno != null && !deEntorno.isBlank()) {
            return deEntorno;
        }
        String deSistema = System.getProperty(propiedad);
        if (deSistema != null && !deSistema.isBlank()) {
            return deSistema;
        }
        throw new IllegalStateException("Falta la configuración \"" + propiedad
                + "\": usa -D" + propiedad + "=... o la variable de entorno " + variable);
    }

    public static String baseUrl() {
        return valor("baseUrl", "BASE_URL");
    }
}