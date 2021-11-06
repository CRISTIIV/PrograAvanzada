package Taller1_Git.logica;
import Taller1_Git.dominio.PersonajePoseido;
import Taller1_Git.dominio.Personaje;

public class ListaPersonajePoseido {
    private int cantidad;
    private int maximo;
    private PersonajePoseido[] listaPersonajesPoseidos;
    private Personaje personaje;

    public ListaPersonajePoseido(int maximo){
        this.cantidad = 0;
        this.maximo = maximo;
        listaPersonajesPoseidos = new PersonajePoseido[maximo];
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

    public PersonajePoseido[] getListaPersonajesPoseidos() {
        return listaPersonajesPoseidos;
    }

    public void setListaPersonajesPoseidos(PersonajePoseido[] listaPersonajesPoseidos) {
        this.listaPersonajesPoseidos = listaPersonajesPoseidos;
    }

    public boolean insertar(PersonajePoseido personajePoseido){
        if (cantidad < maximo){
            listaPersonajesPoseidos[cantidad]=personajePoseido;
            cantidad++;
            return true;
        }
        return false;
    }
    
      //PARA EL BUSCAR POR NOMBRE CREO QUE HAY DOS OPCIONES, Y SI HAY OTRA NO SÉ:(
    public Personaje buscarPorNombrePersonaje1(String nombrePersonaje){
        for (int i = 0; i < cantidad; i++){
            Personaje personaje = listaPersonajesPoseidos[i];
            if (personaje.getNombrePersonaje().equals(nombrePersonaje)){
                return personaje;
            }
        }
        return null;
    }
    //VIÉNDOLO BIEN ESTE NO TIENE MUCHO SENTIDO, PERO ESPERAR A QUE REVISEN
    public PersonajePoseido buscarPorNombrePersonaje2(Personaje personaje){
        for (int i = 0; i < cantidad; i++){
            personaje = listaPersonajesPoseidos[i];
            if (personaje.getNombrePersonaje().equals(personaje)){
                return personaje;
            }
        }
        return null;
    }

    public PersonajePoseido buscarPorI(int i){
        PersonajePoseido personajePoseido = listaPersonajesPoseidos[i];
        if (personajePoseido!=null){
            return personajePoseido;
        }
        return null;
    }

    public boolean agregarNewPersonajeP(String nombreNewPersonajeP){
        if (cantidad < maximo){
            listaPersonajesPoseidos[cantidad]=nombreNewPersonajeP;
            cantidad++;
            return true;
        }
        if (cantidad = maximo){
            maximo+=1;
            listaPersonajesPoseidos[cantidad]=nombreNewPersonajeP;
            cantidad++;
            return true;
        }
        return false;
    }

}
