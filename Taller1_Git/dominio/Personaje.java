package Taller1_Git.dominio;
import Taller1_Git.logica.ListaSkin;

public class Personaje {
    private String nombrePersonaje;
    private String rol;
    private int precioPersonaje;
    private int recaudacionPersonaje;
    private ListaSkin listaSkins;

    public Personaje(String nombrePersonaje, String rol, int precioPersonaje, int recaudacionPersonaje){
        this.nombrePersonaje = nombrePersonaje;
        this.rol = rol;
        this.precioPersonaje = precioPersonaje;
        this.recaudacionPersonaje = recaudacionPersonaje;
        listaSkins = new ListaSkin(500);
    }
    public String getNombrePersonaje() {
        return nombrePersonaje;
    }
    public void setNombrePersonaje(String nombrePersonaje) {
        this.nombrePersonaje = nombrePersonaje;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public int getPrecioPersonaje() {
        return precioPersonaje;
    }
    public void setPrecioPersonaje(int precioPersonaje) {
        this.precioPersonaje = precioPersonaje;
    }
    public int getRecaudacionPersonaje() {
        return recaudacionPersonaje;
    }
    public void setRecaudacionPersonaje(int recaudacionPersonaje) {
        this.recaudacionPersonaje = recaudacionPersonaje;
    }
    public ListaSkin getListaSkins() {
        return listaSkins;
    }
    public void setListaSkins(ListaSkin listaSkins) {
        this.listaSkins = listaSkins;
    }
    public void aumentarRecaudacion(double d){
        recaudacionPersonaje += d;
    }
}
