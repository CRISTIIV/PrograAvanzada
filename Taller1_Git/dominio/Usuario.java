package Taller1_Git.dominio;
import Taller1_Git.logica.ListaPersonajePoseido;
import Taller1_Git.logica.ListaSkin;

/**
 * @author Vicente Cristi & Sebastian Reyes (equipo S&V)
 */

public class Usuario {
    private String nombreCuenta;
    private String password;
    private String nick;
    private int nivel;
    private int rp;
    private int totalPersonajes;
    private String nombrePersonajes;
    private int totalSkins;
    private String nombreSkins;
    private String region;
    private String bloqueado;
    private ListaPersonajePoseido ListaPersonajesPoseidos;
    private ListaSkin ListaSkinsPoseidas;


    public Usuario(String nombreCuenta, String password, String nick, int nivel, int rp, int totalPersonajes, String nombrePersonajes, int totalSkins, String nombreSkins, String region, String bloqueado) {
        this.nombreCuenta = nombreCuenta;
        this.password = password;
        this.nick = nick;
        this.nivel = nivel;
        this.rp = rp;
        this.totalPersonajes = totalPersonajes;
        this.nombrePersonajes = nombrePersonajes;
        this.totalSkins = totalSkins;
        this.nombreSkins = nombreSkins;
        this.region = region;
        this.bloqueado = bloqueado;
        ListaPersonajesPoseidos = new ListaPersonajePoseido(156);
        ListaSkinsPoseidas = new ListaSkin(2000);
        
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }
    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public int getRp() {
        return rp;
    }
    public void setRp(int rp) {
        this.rp = rp;
    }
    public int getTotalPersonajes() {
        return totalPersonajes;
    }
    public void setTotalPersonajes(int totalPersonajes) {
        this.totalPersonajes = totalPersonajes;
    }
    public String getNombrePersonajes() {
        return nombrePersonajes;
    }
    public void setNombrePersonajes(String nombrePersonajes) {
        this.nombrePersonajes = nombrePersonajes;
    }
    public int getTotalSkins() {
        return totalSkins;
    }
    public void setTotalSkins(int totalSkins) {
        this.totalSkins = totalSkins;
    }
    public String getNombreSkins() {
        return nombreSkins;
    }
    public void setNombreSkins(String nombreSkins) {
        this.nombreSkins = nombreSkins;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getBloqueado() {
        return bloqueado;
    }
    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }
    public ListaPersonajePoseido getListaPersonajesPoseidos() {
        return ListaPersonajesPoseidos;
    }
    public void setListaPersonajesPoseidos(ListaPersonajePoseido listaPersonajesPoseidos) {
        ListaPersonajesPoseidos = listaPersonajesPoseidos;
    }
    
    public ListaSkin getListaSkinsPoseidas() {
        return ListaSkinsPoseidas;
    }

    public void setListaSkinsPoseidas(ListaSkin listaSkinsPoseidas) {
        ListaSkinsPoseidas = listaSkinsPoseidas;
    }
    
    /**
     * This method allows to print the user with its respective name, password, nickname, level, rp and country.
     * @return String
     */ 
    public String formatoEscritura(){
        String texto = "";
        for (int i = 0; i < this.getListaPersonajesPoseidos().getCantidad(); i++){
            PersonajePoseido personaje = this.getListaPersonajesPoseidos().buscarPorI(i);
            texto += personaje.getPersonaje().getNombrePersonaje()+",";
            for (int j = 0; j < this.getListaSkinsPoseidas().getCantidad(); j++){
                Skin skin = this.getListaSkinsPoseidas().buscarPorI(j);
                texto += skin.getNombreSkin()+",";
            }
        }
        return nombreCuenta+","+password+","+nick+","+nivel+","+rp+","+texto+region+"\n";
    }
    
    /**
     * This method rises the amount of rp of an especific account.
     * @param rp This pararameter refers to the rp.
     */
    public void agregarRp(int rp) {
    	this.rp += rp;
    }
    
    /**
     * This method rises the level of an especific account.
     * @param nivel This pararameter refers to the nivel of the user.
     */
    public void aumentarNivel(int nivel){
        this.nivel += nivel;
    }
    
    /**
     * This method lowers the amount of rp of an especific account.
     * @param rp This pararameter refers to the rp.
     */
    public void disminuirRp(int rp) {
    	this.rp -= rp;
    }

    public String toStringDatosUsuario(){
        String ultimos3 = password.substring(Math.max(0, text.length() - 3));
        String passwordConf = "******"+ultimos3;
        return "Nombre de la cuenta: "nombreCuenta+"\nNick del usuario: "+nick+"\nUltimos caracteres de la contrasena: "+passwordConf+"\n";
    }
    
}
