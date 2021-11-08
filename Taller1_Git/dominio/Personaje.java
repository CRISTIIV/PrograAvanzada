package Taller1_Git.dominio;
import Taller1_Git.logica.ListaSkin;

public class Personaje {
    private String nombrePersonaje;
    private String rol;
    private int totalSkins;
    private String nombreSkins;
    //private int precioPersonaje;
    private int recaudacionPersonaje;
    private ListaSkin listaSkins;

    public Personaje(String nombrePersonaje, String rol, int totalSkins, String nombreSkins, int recaudacionPersonaje){
        this.nombrePersonaje = nombrePersonaje;
        this.rol = rol;
        this.totalSkins = totalSkins;
        this.nombreSkins = nombreSkins;
        //this.precioPersonaje = precioPersonaje;
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
/*
    public int getPrecioPersonaje() {
        return precioPersonaje;
    }

    public void setPrecioPersonaje(int precioPersonaje) {
        this.precioPersonaje = precioPersonaje;
    }*/

    public int getRecaudacionPersonaje() {
        return recaudacionPersonaje;
    }

    public void setRecaudacionPersonaje(int recaudacionPersonaje) {
        this.recaudacionPersonaje = recaudacionPersonaje;
    }

    public ListaSkin getListaSkins() {
        return listaSkins;
    }

    public void aumentarRecaudacion(int recaudacionPersonaje){
        this.recaudacionPersonaje += recaudacionPersonaje;
    }

    public void setListaSkins(ListaSkin listaSkins) {
        this.listaSkins = listaSkins;
    }
    public String formatoEscritura(){
        return nombrePersonaje+","+rol+","+this.getListaSkins()+"\n";
    }
    public double formatoEstadisticas(){
        double clp = recaudacionPersonaje*(6.15);
        return clp;
    }
    public String formatoEstadistica(){
        return nombrePersonaje+","+this.getRecaudacionPersonaje();
    }
    /*
    public String recaudacionRol(){
        int sup = 0;
        int adc = 0;
        int top = 0;
        int mid = 0;
        int jg = 0;
        if (this.getRol().equals("SUP")){
            sup += this.formatoEstadisticas();
        }
        if (this.getRol().equals("ADC")){
            adc += this.formatoEstadisticas();
        }
        if (this.getRol().equals("TOP")){
            top += this.formatoEstadisticas();
        }
        if (this.getRol().equals("MID")){
            mid += this.formatoEstadisticas();
        }
        if (this.getRol().equals("JG")){
            jg += this.formatoEstadisticas();
        }
        return "Las recaudaciones por rol son: \n SUPORT: $"+sup+" clp \n ATACK DAMAGE CARRY: $"+adc+" clp \n TOP LANER: $"+top+" clp \n MIDDLE LANER: $"+mid+" clp \n JUNGLER: $"+jg+" clp.";

    }*/
    
}
