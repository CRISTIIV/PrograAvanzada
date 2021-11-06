package Taller1_Git.logica;

import Taller1_Git.dominio.Personaje;
import Taller1_Git.dominio.PersonajePoseido;
import Taller1_Git.dominio.Skin;
import Taller1_Git.dominio.Usuario;

public class SistemaRitoGamesImpl implements SistemaRitoGames{

    private ListaPersonaje personajes;
    private ListaPersonajePoseido personajesPoseidos;
    private ListaSkin skins;
    private ListaUsuario usuarios;

    public SistemaRitoGamesImpl(){
        usuarios = new ListaUsuario(1000);
        skins = new ListaSkin(1000);
        personajes = new ListaPersonaje(200);
        personajesPoseidos = new ListaPersonajePoseido(1000);
    }
    @Override
    void agregarUsuario(String nombreCuenta, String password, String nick, int nivel, int rp, int totalPersonajes, String nombrePersonajes, int totalSkins, String nombreSkins, String region){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        if (usuario == null){
            Usuario usuarioNuevo = new Usuario(nombreCuenta,password,nick,nivel,rp,totalPersonajes,nombrePersonajes,totalSkins,nombreSkins,region);
            if (!usuarios.insertar(usuarioNuevo)){
                throw new NullPointerException("La lista de cuentas está llena");
            }
        } else {
            throw new NullPointerException("La cuenta ya existe");
        }
    }

    boolean agregarPersonaje(String nombrePersonaje, String rol, int totalSkins, String nombreSkins, int estadisticas){

    }

    void setRecaudacionPersonaje(String nombrePersonaje, int estadisticas){

    }

    boolean agregarSkin(String nombreSkin, String nombrePersonaje, String calidad){

    }

    void asociarPersonajeUsuario(String nombrePersonaje, String nombreCuenta){

    }

    void asociarSkinPersonaje(String nombreSkin, String nombrePersonaje){

    }

    void asociarSkinUsuario(String nombreSkin, String nombreCuenta){

    }

    String iniciarSesion(String nombreCuenta, String password){

    }

    boolean registrarUsuario(String nombreCuenta, String nick, String password, String region){

    }

    boolean cambiarPassword(String oldPassword, String newPassword){

    }

    String obtenerDatosUsuario(){

    }

    String obtenerDatosPersonajes(){

    }

    String obtenerDatosSkins(){

    }

    String obtenerPersonajeSegunUsuario(String nombreCuenta){

    }

    String obtenerSkinSegunUsuario(String nombreCuenta){

    }

    String obtenerSkinSegunPersonaje(String nombrePersonaje){

    }

    void comprarPersonaje(String nombreCuenta, String nombrePersonaje){

    }

    void comprarSkin(String nombreCuenta, String nombrePersonaje, String nombreSkin){

    }

    void recargarRP(String nombreCuenta, int rp){

    }

    String obtenerEstadisticasPersonaje(String nombrePersonaje){

    }

    String obtenerRecaudacionPorRol(){

    }

    String obtenerRecaudacionPorRegion(){

    }

    String obtenerCantPersonajesPorRol(){

    }

    String cuentasMayMen(){

    }

    boolean agregarNuevaSkin(String nombrePersonaje, String nombreSkin, String calidad){

    }

    boolean agregarNuevoPersonaje(String nombrePersonaje, String rol, int numeroSkins){

    }

    void bloqueoUsuario(String nombreCuenta){
        
    }
    
}
