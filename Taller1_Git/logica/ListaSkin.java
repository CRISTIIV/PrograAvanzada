package Taller1_Git.logica;
import Taller1_Git.dominio.Skin;

public class ListaSkin {
    private int cantidad;
    private int maximo;
    private Skin[] listaSkins;

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

}
