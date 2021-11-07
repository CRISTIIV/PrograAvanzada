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
            Usuario usuarioNuevo = new Usuario(nombreCuenta, password, nick, nivel, rp, totalPersonajes, nombrePersonajes, totalSkins, nombreSkins, region);
            if (!usuarios.insertar(usuarioNuevo)){
                throw new NullPointerException("La lista de cuentas está llena");
            }
        }
        else{
            throw new NullPointerException("La cuenta ya existe.");
        }
    }

    void agregarPersonaje(String nombrePersonaje, String rol, int totalSkins, String nombreSkins, int precioPersonaje, int recaudacionPersonaje){
        Personaje personaje = personajes.buscarPorNombrePersonaje(nombrePersonaje);
        if (personaje == null){
            Personaje personajeNuevo = new Personaje(nombrePersonaje, rol, totalSkins, nombreSkins, precioPersonaje, recaudacionPersonaje);
            if (!personajes.insertar(personajeNuevo)){
                throw new NullPointerException("La lista de personajes ya está llena, para ingresar nuevos personajes debe hacerlo manualmente.");
            }
        }
        else{
            throw new NullPointerException("El personaje ya existe.");
        }
    }

    //FALTA
    void setRecaudacionPersonaje(String nombrePersonaje, int estadisticas){

    }

    void agregarSkin(String nombreSkin, String calidad, int precioSkin, String nombrePersonaje){
        Skin skin = skins.buscarPorNombrePersonaje(nombrePersonaje);
        if (skin == null){
            Skin skinNueva = new Skin(nombreSkin, calidad, precioSkin, nombrePersonaje);
            if (!skins.insertar(skinNueva)){
                throw new NullPointerException("La lista de skins ya está llena, para ingresar nuevas skins debe hacerlo manualmente.");
            }
        }
        else{
            throw new NullPointerException("La skin ya existe.");
        }
    }

    void asociarPersonajeUsuario(String nombrePersonaje, String nombreCuenta){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        Personaje personaje = personajes.buscarPorNombrePersonaje(nombrePersonaje);

        if (usuario == null || personaje == null){
            throw new NullPointerException("Alguno de los datos ingresados no existe");
        } else {
            usuario.getListaPersonajesPoseidos().insertar(personaje);
        }
    }

    void asociarSkinPersonaje(String nombreSkin, String nombrePersonaje){
        Skin skin = skins.buscarPorNombreSkin(nombreSkin);
        Personaje personaje = personajes.buscarPorNombrePersonaje(nombrePersonaje);

        if (skin == null || personaje == null){
            throw new NullPointerException("Alguno de los datos ingresados no existe");
        } else {
            personaje.getListaSkins().insertar(skin);
        }
    }

    void asociarSkinUsuario(String nombreSkin, String nombreCuenta){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        Skin skin = skins.buscarPorNombreSkin(nombreSkin);

        if (usuario == null || skin == null){
            throw new NullPointerException("Alguno de los datos ingresados no existe");
        } else {
            usuario.getListaPersonajesPoseidos().insertar(skin);
        }
    }

    String iniciarSesion(String nombreCuenta, String password){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);

        if (usuario == null){
            return "La cuenta ingresada no existe, ¿desea registrase?";
        }
        else{
            if (usuario != null && usuario.getPassword().equals(password)){
                return "Bienvenido!";
            }
            return "Ingresaste mal la password";
        }
    }

    //FALTA
    boolean registrarUsuario(String nombreCuenta, String nick, String password, String region){

    }

    //FALTA
    boolean cambiarPassword(String oldPassword, String newPassword){

    }

    //FALTA
    String obtenerDatosUsuario(){

    }

    //FALTA
    String obtenerDatosPersonajes(){

    }

    //FALTA
    String obtenerDatosSkins(){

    }

    //FALTA
    String obtenerPersonajeSegunUsuario(String nombreCuenta){

    }

    //FALTA
    String obtenerSkinSegunUsuario(String nombreCuenta){

    }

    //FALTA
    String obtenerSkinSegunPersonaje(String nombrePersonaje){

    }

    //FALTA
    void comprarPersonaje(String nombreCuenta, String nombrePersonaje){

    }

    //FALTA
    void comprarSkin(String nombreCuenta, String nombrePersonaje, String nombreSkin){

    }

    //FALTA
    void recargarRP(String nombreCuenta, int rp){

    }

    //FALTA
    String obtenerEstadisticasPersonaje(String nombrePersonaje){

    }

    //FALTA
    String obtenerRecaudacionPorRol(){

    }

    //FALTA
    String obtenerRecaudacionPorRegion(){

    }

    //FALTA
    String obtenerCantPersonajesPorRol(){

    }

    //FALTA
    String cuentasMayMen(){

    }

    //FALTA
    boolean agregarNuevaSkin(String nombrePersonaje, String nombreSkin, String calidad){

    }

    //FALTA
    boolean agregarNuevoPersonaje(String nombrePersonaje, String rol, int numeroSkins){

    }

    //FALTA
    void bloqueoUsuario(String nombreCuenta){
        
    }
    
}
