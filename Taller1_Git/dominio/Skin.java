package Taller1_Git.dominio;
import Taller1_Git.dominio.Personaje;

public class Skin {
    private String nombreSkin;
    private String calidad;
    private int precioSkin;
    private Personaje personaje;
    
    public Skin(String nombreSkin, String calidad, int precioSkin){
        this.nombreSkin = nombreSkin;
        this.calidad = calidad;
        this.precioSkin = precioSkin;
    }

    public String getNombreSkin() {
        return nombreSkin;
    }

    public void setNombreSkin(String nombreSkin) {
        this.nombreSkin = nombreSkin;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public int getPrecioSkin() {
        return precioSkin;
    }

    public void setPrecioSkin(int precioSkin) {
        this.precioSkin = precioSkin;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
    
}
