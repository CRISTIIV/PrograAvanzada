package Taller1_Git.logica;
import Taller1_Git.dominio.PersonajePoseido;
import Taller1_Git.dominio.Personaje;

/**
 * @author Vicente Cristi & Sebastian Reyes (equipo S&V)
 */

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

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
    
    /**
     * Method to insert a new possedssed character to the array of possessed characters.
     * @param personaje Name of the character.
     * @return Boolean to know if there is still a place on the array. 
     */
    public boolean insertar(PersonajePoseido personajePoseido){
        if (cantidad < maximo){
            listaPersonajesPoseidos[cantidad]=personajePoseido;
            cantidad++;
            return true;
        }
        return false;
    }
    
    /** 
     * Method to find a possessed character by using it's name.
     * @param nombrePersonaje Name of the character.
     * @return The character that we were looking for or, null to clarify that this possessed character doesn't exists.
     */ 
    public Personaje buscarPorNombrePersonaje1(String nombrePersonaje){
        for (int i = 0; i < cantidad; i++){
            Personaje personaje = listaPersonajesPoseidos[i];
            if (personaje.getNombrePersonaje().equals(nombrePersonaje)){
                return personaje;
            }
        }
        return null;
    }
    
    /**
     * Method to find a possessed character by using it's position in the array.
     * @param i index
     * @return The posssessed character that we were looking for or a null, to clarify that this character doesn't exists.
     */
    public PersonajePoseido buscarPorI(int i){
        PersonajePoseido personajePoseido = listaPersonajesPoseidos[i];
        if (personajePoseido!=null){
            return personajePoseido;
        }
        return null;
    }
}
