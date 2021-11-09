package Taller1_Git.logica;
import Taller1_Git.dominio.Personaje;

public class ListaPersonaje {
    private int cantidad;
    private int maximo;
    private Personaje[] listaPersonajes;

    public ListaPersonaje(int maximo){
        this.cantidad = 0;
        this.maximo = maximo;
        listaPersonajes = new Personaje[maximo];
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

    public Personaje[] getListaPersonajes() {
        return listaPersonajes;
    }

    public void setListaPersonajes(Personaje[] listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }
    
    /**
     * Method to insert a new character in the array of characters.
     * @param personaje Name of the character.
     * @return Boolean to know if there is still a place on the array. 
     */ 
    public boolean insertar(Personaje personaje){
        if (cantidad < maximo){
            listaPersonajes[cantidad]=personaje;
            cantidad++;
            return true;
        }
        return false;
    }
    
    /** 
     * Method to find a character by using it's name.
     * @param nombrePersonaje Name of the character.
     * @return The character that we were looking for or, null to clarify that this character doesn't exists.
     */
    public Personaje buscarPorNombrePersonaje(String nombrePersonaje){
        for (int i = 0; i < cantidad; i++){
            Personaje personaje = listaPersonajes[i];
            if (personaje.getNombrePersonaje().equals(nombrePersonaje)){
                return personaje;
            }
        }
        return null;
    }
    
    /**
     * Method to find a character by using it's position on the array.
     * @param i index
     * @return The character that we were looking for or a null, to clarify that this character doesn't exists.
     */ 
    public Personaje buscarPorI(int i){
        Personaje personaje = listaPersonajes[i];
        if (personaje!=null){
            return personaje;
        }
        return null;
    }
    
    /**
     * Method to print the names of the characters.
     * @return String 
     */
    public String formatoEscritura(){
        String salida = "";
        for (int i = 0; i<cantidad; i++){
            Personaje personaje = listaPersonajes[i];
            salida += personaje.formatoEscritura();
        }
        return salida;
    }
    
    /**
     * Method to print the names of the characters and the income of them.
     * @return String 
     */
    public String formatoEstadistica(){
        String salida = "";
        for (int i = 0; i < cantidad; i++){
            Personaje personaje = listaPersonajes[i];
            salida += personaje.formatoEstadistica();
        }
        return salida;
    }

    public String obtenerSkins(){
        String texto = "Lista de Skins de los personajes: \n";
        for (int i = 0; i < cantidad; i++){
            Personaje personaje = listaPersonajes[i];
            String nombrePersonajeSeleccionado = personaje.getNombrePersonaje();
            for (int j = 0; j < personaje.getListaSkins().getCantidad(); j++){
                Skin skin = personaje.getListaSkins().buscarPorI(j);
                texto += "Personaje: "+nombrePersonajeSeleccionado+" sus Skin: N°"+j+": "+skin.getNombreSkin()+"\n";
            }
        }
        return texto;
    }

    public String formatoRecRol(){
        int sup = 0;
        int adc = 0;
        int top = 0;
        int mid = 0;
        int jg = 0;
        String salida = "";
        for (int i = 0; i<cantidad; i++){
            Personaje personaje = listaPersonajes[i];
            if (personaje.getRol().equals("SUP")){
                sup += personaje.formatoEstadisticas();
            }
            if (personaje.getRol().equals("ADC")){
                adc += personaje.formatoEstadisticas();
            }
            if (personaje.getRol().equals("TOP")){
                top += personaje.formatoEstadisticas();
            }
            if (personaje.getRol().equals("MID")){
                mid += personaje.formatoEstadisticas();
            }
            if (personaje.getRol().equals("JG")){
                jg += personaje.formatoEstadisticas();
            }
        }
        salida += "Las recaudaciones por rol son: \n SUPORT: $"+sup+" clp \n ATACK DAMAGE CARRY: $"+adc+" clp \n TOP LANER: $"+top+" clp \n MIDDLE LANER: $"+mid+" clp \n JUNGLER: $"+jg+" clp.";
        return salida;
    }
    
    /**
     * Method to print the names of the characters and how many characters per role exists.
     * @return String 
     */ 
    public String formatoCantRol(){
        int cantSup = 0;
        int cantAdc = 0;
        int cantTop = 0;
        int cantMid = 0;
        int cantJg = 0;
        String salida = "";
        for (int i = 0; i<cantidad; i++){
            Personaje personaje = listaPersonajes[i];
            if (personaje.getRol().equals("SUP")){
                cantSup += 1;
            }
            if (personaje.getRol().equals("ADC")){
                cantAdc += 1;
            }
            if (personaje.getRol().equals("TOP")){
                cantTop += 1;
            }
            if (personaje.getRol().equals("MID")){
                cantMid += 1;
            }
            if (personaje.getRol().equals("JG")){
                cantJg += 1;
            }
        }
        salida += "Las cantidad de personajes por rol son: \n SUPORT: "+cantSup+" personajes \n ATACK DAMAGE CARRY: "+cantAdc+" personajes \n TOP LANER: "+cantTop+" personajes \n MIDDLE LANER: "+cantMid+" personajes \n JUNGLER: "+cantJg+" personajes.";
        return salida;
    }


}
