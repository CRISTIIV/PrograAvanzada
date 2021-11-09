package Taller1_Git.logica;
import Taller1_Git.dominio.Skin;
import Taller1_Git.dominio.Personaje;

/**
 * @author Vicente Cristi & Sebastian Reyes (equipo S&V)
 */

public class ListaSkin {
    private int cantidad;
    private int maximo;
    private Skin[] listaSkins;
    private Personaje personaje;

    public ListaSkin(int maximo){
        this.cantidad = 0;
        this.maximo = maximo;
        listaSkins = new Skin[maximo];
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public Skin[] getListaSkins() {
        return listaSkins;
    }

    public void setListaSkins(Skin[] listaSkins) {
        this.listaSkins = listaSkins;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
    
    /**
     * Method to insert a new skin to the array of skins.
     * @param skin Name of the skin.
     * @return Boolean to know if there is still a place on the array. 
     */
    public boolean insertar(Skin skin){
        if (cantidad < maximo){
            listaSkins[cantidad]=skin;
            cantidad++;
            return true;
        }
        return false;
    }

    public Skin buscarPorNombrePersonaje(String nombrePersonaje){
        for (int i = 0; i < cantidad; i++){
            Skin personaje = listaSkins[i];
            if (personaje.getNombrePersonaje().equals(nombrePersonaje)){
                return personaje;
            }
        }
        return null;
    }
    
    /** 
     * Method to find a skin by using it's name.
     * @param nombreSkin Name of the skin.
     * @return The skin that we were looking for or, null to clarify that this skin doesn't exists.
     */
    public Skin buscarPorNombreSkin(String nombreSkin){
        for (int i = 0; i < cantidad; i++){
            Skin skin = listaSkins[i];
            if (skin.getNombreSkin().equals(nombreSkin)){
                return skin;
            }
        }
        return null;
    }
    
    /**
     * Method to find a skin by using it's position in the array.
     * @param i index
     * @return The skin that we were looking for or a null, to clarify that this skin doesn't exists.
     */
    public Skin buscarPorI(int i){
        Skin skin = listaSkins[i];
        if (skin!=null){
            return skin;
        }
        return null;
    }

}
