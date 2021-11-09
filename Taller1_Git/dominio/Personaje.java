package Taller1_Git.dominio;
import Taller1_Git.logica.ListaSkin;

public class Personaje {
    private String nombrePersonaje;
    private String rol;
    private int totalSkins;
    private String nombreSkins;
    private int recaudacionPersonaje;
    private ListaSkin listaSkins;

    public Personaje(String nombrePersonaje, String rol, int totalSkins, String nombreSkins, int recaudacionPersonaje){
        this.nombrePersonaje = nombrePersonaje;
        this.rol = rol;
        this.totalSkins = totalSkins;
        this.nombreSkins = nombreSkins;
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

    public int getRecaudacionPersonaje() {
        return recaudacionPersonaje;
    }

    public void setRecaudacionPersonaje(int recaudacionPersonaje) {
        this.recaudacionPersonaje = recaudacionPersonaje;
    }

    public ListaSkin getListaSkins() {
        return listaSkins;
    }

    
    public String obtenerSkins(){
        String texto = "Lista de Skins del personaje: \n";
        for (int i = 0; i < listaSkins.getCantidad(); i++){
            Personaje personaje = listaSkins.buscarPorI(i);
            for (int i = 0; personaje.getListaSkins().getCantidad(); i++){
                Skin skin = personaje.getListaSkins().buscarPorI(i);
                texto += "Skin N°"+i+": "+skin.getNombreSkin()+"\n";
            }
        }
        return texto;
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
    
}
