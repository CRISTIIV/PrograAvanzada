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
    //ARREGLARLO
    public String formatoRecRegion(String nombreCuenta){
        int las = 0;
        int lan = 0;
        int euw = 0;
        int kr = 0;
        int na = 0;
        int ru = 0;
        String salida = "";
        Usuario usuario = listaUsuarios[i];
        if (usuario.getRegion().equals("LAS")){
            las += this.formatoEstadisticas();
        }
        if (usuario.getRegion().equals("LAN")){
            lan += this.formatoEstadisticas();
        }
        if (usuario.getRegion().equals("EUW")){
            euw += this.formatoEstadisticas();
        }
        if (usuario.getRegion().equals("KR")){
            kr += this.formatoEstadisticas();
        }
        if (usuario.getRegion().equals("NA")){
            na += this.formatoEstadisticas();
        }
        if (usuario.getRegion().equals("RU")){
            ru += this.formatoEstadisticas();
        }
        salida += "Las recaudaciones por rol son: \n SUPORT: $"+sup+" clp \n ATACK DAMAGE CARRY: $"+adc+" clp \n TOP LANER: $"+top+" clp \n MIDDLE LANER: $"+mid+" clp \n JUNGLER: $"+jg+" clp.";
    }

}
