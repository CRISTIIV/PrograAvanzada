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
    
    @Override
    public void agregarPersonaje(String nombrePersonaje, String rol, int totalSkins, String nombreSkins, int recaudacionPersonaje){
        Personaje personaje = personajes.buscarPorNombrePersonaje(nombrePersonaje);
        if (personaje == null){
            Personaje personajeNuevo = new Personaje(nombrePersonaje, rol, totalSkins, nombreSkins, recaudacionPersonaje);
            if (!personajes.insertar(personajeNuevo)){
                throw new NullPointerException("La lista de personajes ya está llena, para ingresar nuevos personajes debe hacerlo manualmente.");
            }
        }
        else{
            throw new NullPointerException("El personaje ya existe.");
        }
    }

    @Override
    public void agregarSkin(String nombreSkin, String calidad, String nombrePersonaje){
        Skin skin = skins.buscarPorNombrePersonaje(nombrePersonaje);
        if (skin == null){
            Skin skinNueva = new Skin(nombreSkin, calidad, nombrePersonaje);
            if (!skins.insertar(skinNueva)){
                throw new NullPointerException("La lista de skins ya está llena, para ingresar nuevas skins debe hacerlo manualmente.");
            }
        }
        else{
            throw new NullPointerException("La skin ya existe.");
        }
    }

    @Override
    public void asociarPersonajeUsuario(String nombrePersonaje, String nombreCuenta){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        Personaje personaje = personajes.buscarPorNombrePersonaje(nombrePersonaje);
        PersonajePoseido personajeP = personajesPoseidos.buscarPorNombrePersonaje1(nombrePersonaje);

        if (usuario != null && personaje != null){
            personajeP.setPropietario(usuario);
            personajeP.setPersonaje(personaje);
            usuario.getListaPersonajesPoseidos().insertar(personajeP);
        } else {
            if(usuario == null)System.out.println("USUARIO");
            throw new NullPointerException("Alguno de los datos ingresados no existe");
        }
    }

    @Override
    public void asociarSkinPersonaje(String nombreSkin, String nombrePersonaje){
        Skin skin = skins.buscarPorNombreSkin(nombreSkin);
        Personaje personaje = personajes.buscarPorNombrePersonaje(nombrePersonaje);

        if (skin == null || personaje == null){
            throw new NullPointerException("Alguno de los datos ingresados no existe");
        } else {
            personaje.getListaSkins().insertar(skin);
        }
    }

    @Override
    public void asociarSkinUsuario(String nombreSkin, String nombreCuenta, String nombrePersonaje){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        Skin skin = skins.buscarPorNombreSkin(nombreSkin);
        PersonajePoseido personaje = personajesPoseidos.buscarPorNombrePersonaje1(nombrePersonaje)

        if (usuario == null || skin == null || personaje == null){
            throw new NullPointerException("Alguno de los datos ingresados no existe");
        } else {
            usuario.getListaSkinsPoseidas().insertar(skin);
            personaje.getListaSkinsPoseidos().insertar(skin);
        }
    }

    @Override
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

    @Override
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

    @Override
    public String obtenerDatosUsuario(String nombreCuenta){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        String salida = "";
        if (usuario!=null){
            salida += usuario.toStringDatosUsuario();
            return salida;
        }
        throw new NullPointerException("No existe el usuario ingresado");
    }

    @Override
    public String obtenerDatosUsuarios(){
        return usuarios.formatoEscritura();
    }

    @Override
    public String obtenerDatosPersonajes(){
        return personajes.formatoEscritura();
    }

    @Override
    public String obtenerDatosEstadisticas(){
        return personajes.formatoEstadistica();
    }

    @Override
    public String obtenerPersonajeSegunUsuario(String nombreCuenta){
        String texto = "Lista de personajes: \n";
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        if (usuario == null){
            throw new NullPointerException("El usuario no existe.");
        }else{
            if (usuario.getListaPersonajesPoseidos().getCantidad()==0){
                return "El usuario no cuenta con personajes";
            }
            for (int i = 0; i < usuario.getListaPersonajesPoseidos().getCantidad(); i++){
                PersonajePoseido personaje = usuario.getListaPersonajesPoseidos().buscarPorI(i);
                texto += "Personaje N°"+i+": "+personaje.getPersonaje().getNombrePersonaje()+"\n";
                for (int j = 0; j < usuario.getListaSkinsPoseidas().getCantidad(); j++){
                    Skin skin = usuario.getListaSkinsPoseidas().buscarPorI(j);
                    texto += "Skins del personaje: "+skin.getNombreSkin()+"\n";
                }
            }
        }
        return texto;
    }

    @Override
    public String obtenerSkinSegunPersonaje(String nombrePersonaje){
        String texto = "Lista de Skins del personaje: \n";
        Personaje personaje = personajes.buscarPorNombrePersonaje(nombrePersonaje);
        if (personaje == null){
            throw new NullPointerException("El personaje no existe.");
        }else{
            for (int i = 0; personaje.getListaSkins().getCantidad(); i++){
                Skin skin = personaje.getListaSkins().buscarPorI(i);
                texto += "Skin N°"+i+": "+skin.getNombreSkin()+"\n";
            }
        }
        return texto;
    }

    @Override
    public String obtenerSkinsPersonajes(){
        return personajes.obtenerSkins();
    }

    @Override
    public void comprarPersonaje(String nombreCuenta, String nombrePersonaje){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        Personaje personaje = personajes.buscarPorNombrePersonaje(nombrePersonaje);
        PersonajePoseido personajePoseido = personajesPoseidos.buscarPorNombrePersonaje1(nombrePersonaje);
        if (usuario != null && personaje != null){
            if (usuario.getRp() > 975){
                usuario.disminuirRp(975);
                usuario.getListaPersonajesPoseidos().insertar(personajePoseido);
                usuario.aumentarNivel(1);
                personajePoseido.setPropietario(usuario);
                personaje.aumentarRecaudacion(975);
            }else{
                throw new NullPointerException("No cuentas con RP suficiente para realizar la compra.");
            }
        }else{
            throw new NullPointerException("El personaje seleccionado no existe.");
        }
    }

    @Override
    public void comprarSkin(String nombreCuenta, String nombrePersonaje, String nombreSkin){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        Personaje personaje = skins.buscarPorNombrePersonaje(nombrePersonaje);
        Skin skin = skins.buscarPorNombreSkin(nombreSkin);
        int opcion = eleccion;
        if (usuario != null && personaje != null){
            while(opcion != 6){
                switch(opcion){
                    case "1":
                        if (usuario.getRp() > 3250){
                            usuario.disminuirRp(3250);
                            usuario.getListaSkinsPoseidas().insertar(skin);
                            usuario.aumentarNivel(1);
                        }else{
                            throw new NullPointerException("No cuentas con RP suficiente para realizar la compra.");
                        }
                        break;
                    case "2":
                        if (usuario.getRp() > 2750){
                            usuario.disminuirRp(2750);
                            usuario.getListaSkinsPoseidas().insertar(skin);
                            usuario.aumentarNivel(1);
                        }else{
                            throw new NullPointerException("No cuentas con RP suficiente para realizar la compra.");
                        }
                        break;
                    case "3":
                        if (usuario.getRp() > 1820){
                            usuario.disminuirRp(1820);
                            usuario.getListaSkinsPoseidas().insertar(skin);
                            usuario.aumentarNivel(1);
                        }else{
                            throw new NullPointerException("No cuentas con RP suficiente para realizar la compra.");
                        }
                        break;
                    case "4":
                        if (usuario.getRp() > 1350){
                            usuario.disminuirRp(1350);
                            usuario.getListaSkinsPoseidas().insertar(skin);
                            usuario.aumentarNivel(1);
                        }else{
                            throw new NullPointerException("No cuentas con RP suficiente para realizar la compra.");
                        }
                        break;
                    case "5":
                        if (usuario.getRp() > 975){
                            usuario.disminuirRp(975);
                            usuario.getListaSkinsPoseidas().insertar(skin);
                            usuario.aumentarNivel(1);
                        }else{
                            throw new NullPointerException("No cuentas con RP suficiente para realizar la compra.");
                        }
                        break;
                }
            }
        }else{
            throw new NullPointerException("El personaje seleccionado no existe.");
    }

    @Override
    public void recargarRP(String nombreCuenta, int rp){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        usuario.agregarRp(rp);
    }

    @Override
    public String obtenerEstadisticasPersonaje(){
        String texto = "La recaudacion de ventas que obtuvo por personaje: \n";
        texto += personaje.formatoEstadistica();
        return texto;
    }

    @Override
    public String obtenerRecaudacionPorRol(){
        return personajes.formatoRecRol();
    }

    @Override
    public String obtenerRecaudacionPorRegion(){
        return.usuarios.formatoRecRegion();
    }

    @Override
    public String obtenerCantPersonajesPorRol(){
        return personajes.formatoCantRol();
    }

    @Override
    public String cuentasMayMen(){
        String titulo = "Los usuarios ordenados de mayor a menor nivel: ";
        return titulo;
        return usuarios.formatoMayorMenor();
    }

    @Override
    public boolean agregarNuevaSkin(String nombrePersonaje, String nombreSkin, String calidad){
        Skin skin = skins.buscarPorNombreSkin(nombreSkin);
        if (skin==null){
            Skin nueva = new Skin(nombreSkin, calidad, nombrePersonaje);
            return skins.insertar(nueva);
        }
    }

    @Override
    public boolean agregarNuevoPersonaje(String nombrePersonaje, String rol, int totalSkins, String nombreSkins, int recaudacionPersonaje){
        Personaje personaje = personajes.buscarPorNombrePersonaje(nombrePersonaje);
        if (personaje==null){
            Personaje nuevo = new Personaje(nombrePersonaje, rol, totalSkins, nombreSkins, recaudacionPersonaje);
            return personajes.insertar(nuevo);
        }
    }

    @Override
    public void bloqueoUsuario(String nombreCuenta){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        if (usuario == null){
            throw new NullPointerException("La cuenta ingresada no existe");
        } else {
            usuario.setBloqueado("bloqueado");
        }
    }
    
    @Override
    public String verificarBloqueo(String nombreCuenta){
        Usuario usuario = usuarios.buscarPorNombreCuenta(nombreCuenta);
        return usuario.getBloqueado();
    }

}
