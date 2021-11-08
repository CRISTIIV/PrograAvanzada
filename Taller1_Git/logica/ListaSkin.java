package Taller1_Git.logica;
import Taller1_Git.dominio.Skin;
import Taller1_Git.dominio.Personaje;

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

    public boolean insertar(Skin skin){
        if (cantidad < maximo){
            listaSkins[cantidad]=skin;
            cantidad++;
            return true;
        }
        return false;
    }

    public Personaje buscarPorNombrePersonaje(String nombrePersonaje){
        for (int i = 0; i < cantidad; i++){
            Personaje personaje = listaSkins[i];
            if (personaje.getNombrePersonaje().equals(nombrePersonaje)){
                return personaje;
            }
        }
        return null;
    }

    public Skin buscarPorNombreSkin(String nombreSkin){
        for (int i = 0; i < cantidad; i++){
            Skin skin = listaSkins[i];
            if (skin.getNombreSkin().equals(nombreSkin)){
                return skin;
            }
        }
        return null;
    }

    public Skin buscarPorI(int i){
        Skin skin = listaSkins[i];
        if (skin!=null){
            return skin;
        }
        return null;
    }

}
