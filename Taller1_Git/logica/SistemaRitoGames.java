package Taller1_Git.logica;

public interface SistemaRitoGames {
    
    void agregarUsuario(String nombreCuenta, String password, String nick, int nivel, int rp, int totalPersonajes, String nombrePersonajes, int totalSkins, String nombreSkins, String region);

    void agregarPersonaje(String nombrePersonaje, String rol, int totalSkins, String nombreSkins, int precioPersonaje, int recaudacionPersonaje);

    void setRecaudacionPersonaje(String nombrePersonaje, int estadisticas);

    boolean agregarSkin(String nombreSkin, String calidad, int precioSkin, String nombrePersonaje);

    void asociarPersonajeUsuario(String nombrePersonaje, String nombreCuenta);

    void asociarSkinPersonaje(String nombreSkin, String nombrePersonaje);

    void asociarSkinUsuario(String nombreSkin, String nombreCuenta);

    String iniciarSesion(String nombreCuenta, String password);

    void cambiarPassword(String nombreCuenta, String oldPassword, String newPassword);

    String obtenerDatosUsuario();

    String obtenerDatosPersonajes();

    String obtenerDatosSkins();

    String obtenerPersonajeSegunUsuario(String nombreCuenta);

    String obtenerSkinSegunUsuario(String nombreCuenta);

    String obtenerSkinSegunPersonaje(String nombrePersonaje);

    void comprarPersonaje(String nombreCuenta, String nombrePersonaje);

    void comprarSkin(String nombreCuenta, String nombrePersonaje, String nombreSkin);

    void recargarRP(String nombreCuenta, int rp);

    String obtenerEstadisticasPersonaje(String nombrePersonaje);

    String obtenerRecaudacionPorRol();

    String obtenerRecaudacionPorRegion();

    String obtenerCantPersonajesPorRol();

    String cuentasMayMen();

    boolean agregarNuevaSkin(String nombrePersonaje, String nombreSkin, String calidad);

    boolean agregarNuevoPersonaje(String nombrePersonaje, String rol, int numeroSkins);

    void bloqueoUsuario(String nombreCuenta);
    
}
