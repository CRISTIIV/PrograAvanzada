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
    public void agregarUsuario(String nombreCuenta, String password, String nick, int nivel, int rp, int totalPersonajes, String nombrePersonajes, int totalSkins, String nombreSkins, String region){
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

    public void agregarPersonaje(String nombrePersonaje, String rol, int totalSkins, String nombreSkins, int precioPersonaje, int recaudacionPersonaje){
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
    public void setRecaudacionPersonaje(String nombrePersonaje, int estadisticas){

    }

    public void agregarSkin(String nombreSkin, String calidad, int precioSkin, String nombrePersonaje){
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

    public void asociarPersonajeUsuario(String nombrePersonaje, String nombreCuenta){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        Personaje personaje = personajes.buscarPorNombrePersonaje(nombrePersonaje);

        if (usuario == null || personaje == null){
            throw new NullPointerException("Alguno de los datos ingresados no existe");
        } else {
            usuario.getListaPersonajesPoseidos().insertar(personaje);
        }
    }

    public void asociarSkinPersonaje(String nombreSkin, String nombrePersonaje){
        Skin skin = skins.buscarPorNombreSkin(nombreSkin);
        Personaje personaje = personajes.buscarPorNombrePersonaje(nombrePersonaje);

        if (skin == null || personaje == null){
            throw new NullPointerException("Alguno de los datos ingresados no existe");
        } else {
            personaje.getListaSkins().insertar(skin);
        }
    }

    public void asociarSkinUsuario(String nombreSkin, String nombreCuenta){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        Skin skin = skins.buscarPorNombreSkin(nombreSkin);

        if (usuario == null || skin == null){
            throw new NullPointerException("Alguno de los datos ingresados no existe");
        } else {
            usuario.getListaPersonajesPoseidos().insertar(skin);
        }
    }

    public String iniciarSesion(String nombreCuenta, String password){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);

        if (usuario == null){
            return "La cuenta ingresada no existe, ¿desea registrase?";
        }
        else{
            if (usuario != null && usuario.getPassword().equals(password)){
                return "Bienvenido!";
            }
            return "Ingresaste mal la contrasena";
        }
    }

    public void cambiarPassword(String nombreCuenta, String oldPassword, String newPassword){
        String validacion = iniciarSesion(nombreCuenta,oldPassword);
		Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
		if(validacion.equals("Bienvenido!")) {
			usuario.setPassword(newPassword);
		} else if (validacion.equals("Ingresaste mal la contrasena")) {
			throw new NullPointerException("Password ingresada incorrectamente");
		} else {
			throw new NullPointerException("La cuenta ingresada no existe");	
		}
    }

    public String obtenerDatosUsuario(String nombreCuenta){
        Usuario
    }

    //FALTA
    public String obtenerDatosPersonajes(){

    }

    //FALTA
    public String obtenerDatosSkins(){

    }

    //FALTA
    public String obtenerPersonajeSegunUsuario(String nombreCuenta){

    }

    //FALTA
    public String obtenerSkinSegunUsuario(String nombreCuenta){

    }

    //FALTA
    public String obtenerSkinSegunPersonaje(String nombrePersonaje){

    }

    //FALTA
    public void comprarPersonaje(String nombreCuenta, String nombrePersonaje){

    }

    //FALTA
    public void comprarSkin(String nombreCuenta, String nombrePersonaje, String nombreSkin){

    }

    public void recargarRP(String nombreCuenta, int rp){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        usuario.agregarRp(rp);
    }

    //FALTA
    public String obtenerEstadisticasPersonaje(String nombrePersonaje){

    }

    //FALTA
    public String obtenerRecaudacionPorRol(){

    }

    //FALTA
    public String obtenerRecaudacionPorRegion(){

    }

    //FALTA
    public String obtenerCantPersonajesPorRol(){

    }

    //FALTA
    public String cuentasMayMen(){

    }

    //FALTA
    public boolean agregarNuevaSkin(String nombrePersonaje, String nombreSkin, String calidad){

    }

    //FALTA
    public boolean agregarNuevoPersonaje(String nombrePersonaje, String rol, int numeroSkins){

    }

    //FALTA
    public void bloqueoUsuario(String nombreCuenta){
        
    }
    
}
