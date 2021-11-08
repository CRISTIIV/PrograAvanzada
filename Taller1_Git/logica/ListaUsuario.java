package Taller1_Git.logica;
import Taller1_Git.dominio.PersonajePoseido;
import Taller1_Git.dominio.Skin;
import Taller1_Git.dominio.Usuario;
import java.util.Arrays;

public class ListaUsuario {
    private int cantidad;
    private int maximo;
    private Usuario[] listaUsuarios;
    private Skin skin;

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

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public boolean insertar(Usuario usuario){
        if (cantidad < maximo){
            listaUsuarios[cantidad]=usuario;
            cantidad++;
            return true;
        }
        return false;
    }

    public boolean bloquear(String nombreCuenta){
        int i;
        boolean eliminado = false;
        for (i = 0; i < cantidad; i++){
            Usuario usuario = listaUsuarios[i];
            if (usuario.getNombreCuenta().equals(nombreCuenta)){
                eliminado = true;
                break;
            }
        }
        if (eliminado){
            listaUsuarios[i].setBloqueado("bloqueado");
            }
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
    public String formatoRecRegion(){
        int las = 0;
        int lan = 0;
        int euw = 0;
        int kr = 0;
        int na = 0;
        int ru = 0;
        String salida = "";
        for (int i = 0; i < cantidad; i++){
            Usuario usuario = listaUsuarios[i];
            for (int j = 0; j < usuario.getListaPersonajesPoseidos().getCantidad(); j++){
                Usuario usuario2 = usuario.getListaPersonajesPoseidos().buscarPorI(j);
                if (usuario2.getRegion().equals("LAS")){
                    las += 975;
                }
                if (usuario2.getRegion().equals("LAN")){
                    lan += 975;
                }
                if (usuario2.getRegion().equals("EUW")){
                    euw += 975;
                }
                if (usuario2.getRegion().equals("KR")){
                    kr += 975;
                }
                if (usuario2.getRegion().equals("NA")){
                    na += 975;
                }
                if (usuario2.getRegion().equals("RU")){
                    ru += 975;
                }
            }
            for (int k = 0; k < usuario.getListaSkinsPoseidas().getCantidad(); k++){
                Usuario usuario3 = usuario.getListaSkinsPoseidas().buscarPorI(k);
                Skin skin2 = usuario3.getListaSkinsPoseidas().buscarPorI(k);
                if (skin2.getCalidad().equals("M")){
                    if (usuario3.getRegion().equals("LAS")){
                        las += 3250;
                    }
                    if (usuario3.getRegion().equals("LAN")){
                        lan += 3250;
                    }
                    if (usuario3.getRegion().equals("EUW")){
                        euw += 3250;
                    }
                    if (usuario3.getRegion().equals("KR")){
                        kr += 3250;
                    }
                    if (usuario3.getRegion().equals("NA")){
                        na += 3250;
                    }
                }
                if (skin2.getCalidad().equals("D")){
                    if (usuario3.getRegion().equals("LAS")){
                        las += 2750;
                    }
                    if (usuario3.getRegion().equals("LAN")){
                        lan += 2750;
                    }
                    if (usuario3.getRegion().equals("EUW")){
                        euw += 2750;
                    }
                    if (usuario3.getRegion().equals("KR")){
                        kr += 2750;
                    }
                    if (usuario3.getRegion().equals("NA")){
                        na += 2750;
                    }
                }
                if (skin2.getCalidad().equals("L")){
                    if (usuario3.getRegion().equals("LAS")){
                        las += 1820;
                    }
                    if (usuario3.getRegion().equals("LAN")){
                        lan += 1820;
                    }
                    if (usuario3.getRegion().equals("EUW")){
                        euw += 1820;
                    }
                    if (usuario3.getRegion().equals("KR")){
                        kr += 1820;
                    }
                    if (usuario3.getRegion().equals("NA")){
                        na += 1820;
                    }
                }
                if (skin2.getCalidad().equals("E")){
                    if (usuario3.getRegion().equals("LAS")){
                        las += 1350;
                    }
                    if (usuario3.getRegion().equals("LAN")){
                        lan += 1350;
                    }
                    if (usuario3.getRegion().equals("EUW")){
                        euw += 1350;
                    }
                    if (usuario3.getRegion().equals("KR")){
                        kr += 1350;
                    }
                    if (usuario3.getRegion().equals("NA")){
                        na += 1350;
                    }
                }
                if (skin2.getCalidad().equals("N")){
                    if (usuario3.getRegion().equals("LAS")){
                        las += 975;
                    }
                    if (usuario3.getRegion().equals("LAN")){
                        lan += 975;
                    }
                    if (usuario3.getRegion().equals("EUW")){
                        euw += 975;
                    }
                    if (usuario3.getRegion().equals("KR")){
                        kr += 975;
                    }
                    if (usuario3.getRegion().equals("NA")){
                        na += 975;
                    }
                }
            }
        double lasClp = las * (6.15);
        double lanClp = lan * (6.15);
        double euwClp = euw * (6.15);
        double krClp = kr * (6.15);
        double naClp = na * (6.15);
        double ruClp = ru * (6.15);
       
        salida += "Las recaudaciones por region son: \n LAS: $"+lasClp+" clp \n LAN: $"+lanClp+" clp \n EUW: $"+euwClp+" clp \n KR: $"+krClp+" clp \n NA: $"+naClp+" clp \n RU: $"+ruClp+" clp.";
        return salida;
        }   
    }
    public String formatoMayorMenor(){
        String salida = "";
        //Usuario usuarios = listaUsuarios;
        for(int i = 0; i < cantidad; i++){
            Usuario usuario = listaUsuarios[i];
			for(int j = i+1; j < cantidad; j++){
                Usuario usuario2 = listaUsuarios[j];
				if(usuario.getNivel() < usuario2.getNivel()){
					String aux = usuario.getNombreCuenta();
					usuario.getNombreCuenta() = usuario2.getNombreCuenta();
					usuario2.getNombreCuenta() = aux;
				}
			}
            //usuarios = usuario;
            salida += listaUsuarios[i].getNombreCuenta()+",";
        }
        
    }
}