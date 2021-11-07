package Taller1_Git.logica;
import Taller1_Git.dominio.Usuario;

public class ListaUsuario {
    private int cantidad;
    private int maximo;
    private Usuario[] listaUsuarios;

    public ListaUsuario(int maximo){
        this.cantidad = 0;
        this.maximo = maximo;
        listaUsuarios = new Usuario[maximo];
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

    public Usuario[] getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(Usuario[] listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public boolean insertar(Usuario usuario){
        if (cantidad < maximo){
            listaUsuarios[cantidad]=usuario;
            cantidad++;
            return true;
        }
        return false;
    }

    public Usuario buscarPorNombreCuenta(String nombreCuenta){
        for (int i = 0; i < cantidad; i++){
            Usuario usuario = listaUsuarios[i];
            if (usuario.getNombreCuenta().equals(nombreCuenta)){
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorI(int i){
        Usuario usuario = listaUsuarios[i];
        if (usuario!=null){
            return usuario;
        }
        return null;
    }

    public String formatoEscritura(){
        String salida = "";
        for (int i = 0; i<cantidad; i++){
            Usuario usuario= listaUsuarios[i];
            salida += usuario.formatoEscritura();
        }
        return salida;
    }

}
