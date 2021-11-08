package Taller1_Git.dominio;
import Taller1_Git.logica.ListaPersonajePoseido;

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
    private boolean bloqueado;
    private ListaPersonajePoseido ListaPersonajesPoseidos;


    public Usuario(String nombreCuenta, String password, String nick, int nivel, int rp, int totalPersonajes, String nombrePersonajes, int totalSkins, String nombreSkins, String region) {
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
        ListaPersonajesPoseidos = new ListaPersonajePoseido(156);
        
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
    public boolean isBloqueado() {
        return bloqueado;
    }
    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
    public ListaPersonajePoseido getListaPersonajesPoseidos() {
        return ListaPersonajesPoseidos;
    }
    public void setListaPersonajesPoseidos(ListaPersonajePoseido listaPersonajesPoseidos) {
        ListaPersonajesPoseidos = listaPersonajesPoseidos;
    }
    public String formatoEscritura(){

    }

    public void agregarRp(double rp) {
    	this.rp += rp;
    }
    
    public void disminuirRp(double saldo) {
    	this.rp -= rp;
    }

    public String toStringDatosUsuario(){
        String ultimos3 = password.substring(Math.max(0, text.length() - 3));
        String passwordConf = "******"+ultimos3;
        return "Nombre de la cuenta: "nombreCuenta+"\nNick del usuario: "+nick+"\nUltimos caracteres de la contrasena: "+passwordConf+"\n";
    }
}
