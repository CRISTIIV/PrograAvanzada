package Taller1.logica;

public interface SistemaRitoGames {
    
    boolean agregarUsuario(String nombreCuenta, String password, String nick, int nivel, int rp, int totalPersonajes, String nombrePersonajes, int totalSkins, String nombreSkins, String region);

    boolean agregarPersonaje(String nombrePersonaje, String rol, int totalSkins, String nombreSkins, int estadisticas);

    void setRecaudacionPersonaje(String nombrePersonaje, int estadisticas);

    boolean agregarSkin(String nombreSkin, String nombrePersonaje, String calidad);

    void asociarPersonajeUsuario(String nombrePersonaje, String nombreCuenta);

    void asociarSkinPersonaje(String nombreSkin, String nombrePersonaje);

    
}
