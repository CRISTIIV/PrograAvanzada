package Taller1.logica;

public interface SistemaRitoGames {
    
    boolean agregarUsuario(String nombreCuenta, String password, String nick, int nivel, int rp, int totalPersonajes, String nombrePersonajes, int totalSkins, String nombreSkins, String region);

    boolean agregarPersonaje(String nombrePersonaje, String rol, int totalSkins, String nombreSkins, int estadisticas);

    void setRecaudacionPersonaje(String nombrePersonaje, int estadisticas);

    boolean agregarSkin(String nombreSkin, String nombrePersonaje, String calidad);

    void asociarPersonajeUsuario(String nombrePersonaje, String nombreCuenta);

    void asociarSkinPersonaje(String nombreSkin, String nombrePersonaje);

    void asociarSkinUsuario(String nombreSkin, String nombreCuenta);

    String iniciarSesion(String nombreCuenta, String password);

    boolean registrarUsuario(String nombreCuenta, String nick, String password, String region);

    boolean cambiarPassword(String oldPassword, String newPassword);

    String obtenerDatosUsuario();

    String obtenerDatosPersonajes();

    String obtenerDatosSkins();

    String obtenerPersonajeSegunUsuario(String nombreCuenta);

    String obtenerSkinSegunUsuario(String nombreCuenta);

    String obtenerSkinSegunPersonaje(String nombrePersonaje);

    
}
