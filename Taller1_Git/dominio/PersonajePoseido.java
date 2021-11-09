package Taller1_Git.dominio;
import Taller1_Git.logica.ListaSkin;
import Taller1_Git.dominio.Usuario;
import Taller1_Git.dominio.Personaje;

/**
 * @author Vicente Cristi & Sebastian Reyes (equipo S&V)
 */

public class PersonajePoseido {
    
    private Usuario propietario;
    private Personaje personaje;
    private ListaSkin listaSkinsPoseidos;

    public PersonajePoseido(){//SEGÚN YO NO SE COLOCA NADA EN EL PARENTESIS ESTA VEZ Y ABAJO SOLO LA LISTA, 
                                                                      //PERO QUEDARÍA VACÍO ACÁ, ENTONCES NO SÉ:(
        propietario = null;
        personaje = null;
        listaSkinsPoseidos = new ListaSkin(500);
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public ListaSkin getListaSkinsPoseidos() {
        return listaSkinsPoseidos;
    }

    public void setListaSkinsPoseidos(ListaSkin listaSkinsPoseidos) {
        this.listaSkinsPoseidos = listaSkinsPoseidos;
    }
    
}
