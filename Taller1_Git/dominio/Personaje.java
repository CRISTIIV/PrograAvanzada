package Taller1_Git.dominio;
import Taller1_Git.logica.ListaSkin;

/**
 * @author Vicente Cristi & Sebastian Reyes (equipo S&V)
 */

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
    
    public void setListaSkins(ListaSkin listaSkins) {
        this.listaSkins = listaSkins;
    }
    
    /**
     * This method rises the amount of recaudation of an especific character.
     * @param recaudacionPersonaje This pararameter refers to how much the recaudation has risen.
     */
    public void aumentarRecaudacion(int recaudacionPersonaje){
        this.recaudacionPersonaje += recaudacionPersonaje;
    }
    
    /**
     * Method to print the name of a character, it's role and it's skins. 
     * @return String
     */ 
    public String formatoEscritura(){
        return nombrePersonaje+","+rol+","+this.getListaSkins()+"\n";
    }
    
    /**
     * Method to print the income of a character.
     * @return Its total income.
     */
    public double formatoEstadisticas(){
        double clp = recaudacionPersonaje*(6.15);
        return clp;
    }
    
    /**
     * Method to print the income of a character and his name.
     * @return name of tha character and its income.
     */
    public String formatoEstadistica(){
        return nombrePersonaje+","+this.getRecaudacionPersonaje();
    }
    
}
