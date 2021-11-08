package Taller1_Git.logica;
import Taller1_Git.dominio.Skin;

public class ListaSkinPoseida {
    private int cantidad;
    private int maximo;
    private Skin[] listaSkinsPoseidas;

    public ListaSkinPoseida(int maximo){
        this.cantidad = 0;
        this.maximo = maximo;
        listaSkinsPoseidas = new Skin[maximo];
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

    public Skin[] getListaSkinsPoseidas() {
        return listaSkinsPoseidas;
    }

    public void setListaSkinsPoseidas(Skin[] listaSkinsPoseidas) {
        this.listaSkinsPoseidas = listaSkinsPoseidas;
    }

    public boolean insertar(Skin skinPoseida){
        if (cantidad < maximo){
            listaSkinsPoseidas[cantidad]=skinPoseida;
            cantidad++;
            return true;
        }
        return false;
    }

    public Skin buscarPorNombreSkin(String nombreSkin){
        for (int i = 0; i < cantidad; i++){
            Skin skin = listaSkinsPoseidas[i];
            if (skin.getNombreSkin().equals(nombreSkin)){
                return skin;
            }
        }
        return null;
    }

}
