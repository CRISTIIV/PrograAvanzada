package Taller1_Git.logica;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import Taller1_Git.dominio.Usuario;

public class Main {
    public static void main(String[] args){
        SistemaRitoGames sistema = new SistemaRitoGamesImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de compra de Personajes y Skin para el videojuego <Juego Generico>");
        lecturaCuentas(sistema);
        lecturaPersonajes(sistema);
        lecturaEstadisticas(sistema);

        while(true){
            System.out.println("Ingrese su cuenta de usuario: ");
            String nombreCuenta = scanner.next();
            System.out.println("Ingrese su contrasena: ");
            String password = scanner.next();

            if (sistema.verificarBloqueo(nombreCuenta).equals("bloqueado")){
                System.out.println("Este usuario se encuentra bloqueado, no tiene permitido el ingreso al sistema.");
                break;
            }else{
                mainMenu(sistema, nombreCuenta, password);
                System.out.println("Desea cerrar el sistema? (si-no)");      
                String cerrar = scanner.next().toLowerCase();
                if(cerrar.equals("si"))break;   
            }
        }
        cierreSistema(sistema);
        scanner.close();
    }

    public static void lecturaCuentas(SistemaRitoGames sistema) {
        int contador = 1;
        try {
            Scanner archivo = new Scanner(new File("Cuentas.txt"));
            while (archivo.hasNextLine()){
                
                String linea = archivo.nextLine();
                String[] parte = linea.split(",");
    
                String nombreCuenta = parte[0];
                String password = parte[1];
                String nick = parte[2];
                int nivel = Integer.parseInt(parte[3]);
                int rp = Integer.parseInt(parte[4]);
                int totalPersonajes = Integer.parseInt(parte[5]);
                int totalSkins2 = 0;
                String nombresSkins = "";
                String nombresPersonajes = "";
                for (int i = 0; i < totalPersonajes; i++){
                    String nombrePersonajes = parte[6+i];
                    nombresPersonajes += nombrePersonajes+",";
                    int totalSkins = Integer.parseInt(parte[6+totalPersonajes]);
                    totalSkins2 += totalSkins;
                    for (int j = 0; j < totalSkins; j++){
                        String nombreSkins = parte[7+totalPersonajes];
                        nombresSkins += nombreSkins+",";
                    }
                }
				String region = parte[8+totalPersonajes+totalSkins2];
    
                try {
                    sistema.agregarUsuario(nombreCuenta, password, nick, nivel, rp, totalPersonajes, nombresPersonajes, totalSkins2, nombresSkins, region);
                } catch (Exception e) {
                    System.out.println("No se pudo agregar la cuenta en la linea" + contador + "ERROR: "+ e.getMessage());
                }
                contador++;
    
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo Cuentas.txt");
        }
    }

    public static void lecturaPersonajes(SistemaRitoGames sistema) {
        int contador = 1;
        try {
            Scanner archivo = new Scanner(new File("Personajes.txt"));
            while (archivo.hasNextLine()){
                
                String linea = archivo.nextLine();
                String[] parte = linea.split(",");
    
                String nombrePersonaje = parte[0];
                String rol = parte[1];
                int totalSkins = Integer.parseInt(parte[2]);
                String nombreSkins = "";
                String calidad = "";
                for (int i = 0; i < totalSkins; i++) {
                	String variableNombre = parte[3+i];
                	nombreSkins += variableNombre+",";
                	String variableCalidad = parte[4+i];
                	calidad += variableCalidad+",";
                }
                int recaudacionesPersonaje = Integer.parseInt(parte[5+totalSkins]);
    
                try {
                    sistema.agregarPersonaje(nombrePersonaje,rol,totalSkins,nombreSkins,recaudacionesPersonaje);
                    sistema.agregarSkin(nombreSkins, calidad, nombrePersonaje);
                } catch (Exception e) {
                    System.out.println("No se pudo agregar el personaje y las skin en la linea" + contador + "ERROR: "+ e.getMessage());
                }
                contador++;
    
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo Personajes.txt");
        }
    }

    public static void lecturaEstadisticas(SistemaRitoGames sistema) {
        int contador = 1;
        try {
            Scanner archivo = new Scanner(new File("Estadisticas.txt"));
            while (archivo.hasNextLine()){
                
                String linea = archivo.nextLine();
                String[] parte = linea.split(",");
    
                String nombrePersonaje = parte[0];
                int recaudacion = Integer.parseInt(parte[1]);
                try {
                    sistema.agregarEstadistica(nombrePersonaje,recaudacion);
                } catch (Exception e) {
                    System.out.println("No se pudo agregar la recaudacion en la linea" + contador + "ERROR: "+ e.getMessage());
                }
                contador++;
    
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo Estadisticas.txt");
        }
    }
    
    public static void mainMenu(SistemaRitoGames sistema, String nombreCuenta, String password){
        Scanner scanner = new Scanner(System.in);
        if(nombreCuenta.equals("ADMIN")) {
            if (password.equals("ADMIN")){
                menuAdmin(sistema);
            } else {
                System.out.println("Ingresaste mal la contrasena");
            }
        }else{
            String validacion = sistema.iniciarSesion(nombreCuenta, password);
            switch (validacion) {
                case "La cuenta ingresada no existe, Â¿desea registrase?"://ACA CAMBIÃ‰ POR EL TEXTO DEL return EN EL INICIAR SESION DEL TALLER NUEVO
                    System.out.println("La cuenta ingresada no existe, Â¿desea registrase?(si-no)");
                    String registro = scanner.next().toLowerCase();
                    if(registro.equals("si")){
                        System.out.println("Ingresa el nombre de tu nueva cuenta: ");
                        String nombreCuentaNueva = scanner.next();
                        
                        System.out.println("Ingrese su nueva contrasena: ");
                        String passwordNueva = scanner.next();
                        
                        System.out.println("Ingrese su nuevo nick: ");
                        String nickNuevo = scanner.next();

                        System.out.println("Ingrese su region: ");
                        String regionNueva = scanner.next();
                        regionNueva = hacerMayuscula(regionNueva);

                        int nivelInicial = 0;
                        int rpInicial = 0;
                        int totalPersonajesInicial = 0;
                        String nombrePersonajesInicial = "";
                        int totalSkinsInicial = 0;
                        String nombreSkinsInicial = "";

                        try {
                            sistema.agregarUsuario(nombreCuentaNueva, passwordNueva, nickNuevo, nivelInicial, rpInicial, totalPersonajesInicial, nombrePersonajesInicial, totalSkinsInicial, nombreSkinsInicial, regionNueva);
                            System.out.println("Se ha registrado con exito!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case "Bienvenido!":
                    menuCliente(nombreCuenta,sistema);
                    break;
                    
                case "Ingresaste mal la contrasena":
                    System.out.println("La contrasena se ingreso incorrectamente");
                    break;
                default:
                    System.out.println("Salio default");
                    
            }
        }    
    }
    
    public static void menuAdmin(SistemaRitoGames sistema){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido ADMINISTRADOR, Que desea realizar? ");
        System.out.println("\n1) Desplegar recaudacion de ventas por rol");
        System.out.println("2) Desplegar recaudacion total de ventas por region");
        System.out.println("3) Desplegar recaudacion de ventas por personaje");
        System.out.println("4) Desplegar la cantidad de personajes por rol existente");
        System.out.println("5) Agregar un personaje al juego");
        System.out.println("6) Agregar una skin a un personaje del juego");
        System.out.println("7) Bloquear un jugador");
        System.out.println("8) Desplegar todas las cuentas de mayor a menor");
        System.out.println("\nDigite 'salir' para salir");
        while(true){
            String opcion = scanner.next();
            Boolean salir = false;
            switch (opcion) {
                case "1":
                    System.out.println(sistema.obtenerRecaudacionPorRol());
                    break;
                
                case "2":
                    System.out.println(sistema.obtenerRecaudacionPorRegion());
                    break;
                
                case "3":
                    System.out.println(sistema.obtenerEstadisticasPersonaje());
                    break;
                
                case "4":
                    System.out.println(sistema.obtenerCantPersonajesPorRol());
                    break;
                    
                case "5":
                    System.out.println("Usted eligio agregar un nuevo personaje al juego, a continuacion ingrese los datos del personaje: ");
                    System.out.println("\nIngrese el nombre del personaje: ");
                    String nuevoNombre = scanner.next();
                    System.out.println("\nIngrese el rol del personaje: ");
                    String nuevoRol = scanner.next();
                    System.out.println("\nIngrese la cantidad de skins que tiene el personaje: ");
                    int nuevoTotalSkins = Integer.parseInt(scanner.next());
                    String nuevoNombresSkins = "";
                    for (int i = 0; i < nuevoTotalSkins; i++){
                        System.out.println("\nIngrese el nombre de la skin NÂ°"+(i+1)+": ");
                        String nuevoNombreSkins = scanner.next()+",";
                        nuevoNombresSkins += nuevoNombreSkins;
                    }
                    int nuevaRecaudacionPersonaje = 0;
                    sistema.agregarNuevoPersonaje(nuevoNombre, nuevoRol, nuevoTotalSkins, nuevoNombresSkins, nuevaRecaudacionPersonaje);
                    break;

                case "6":
                    System.out.println("Usted eligio agregar una nueva skin a un personaje del juego, a continuacion ingrese los datos del personaje: ");
                    System.out.println("\nIngrese el nombre del personaje al que desea agregar la skin: ");
                    String nombrePersonajeSkin = scanner.next();
                    System.out.println("\nIngrese el nombre de la skin que desea agregar al personaje: ");
                    String nombreNuevaSkin = scanner.next();
                    System.out.println("\nIngrese la calidad de la nueva skin: ");
                    String nuevaCalidad = scanner.next();
                    sistema.agregarNuevaSkin(nombrePersonajeSkin, nombreNuevaSkin, nuevaCalidad);
                    break;

                case "7":
                    System.out.println("\nIngrese el nombre de la cuenta que desea bloquear: ");
                    String nombreCuentaBloquear = scanner.next();
                    sistema.bloqueoUsuario(nombreCuentaBloquear);
                    break;
                
                case "8":
                    sistema.cuentasMayMen();
                    break;
                    
                case "salir":
                    salir = true;
                    break;
                default:
                    System.out.println("La opcion ingresada no es valida");
            }
            if(salir)break;
            System.out.println("\n1) Desplegar recaudacion de ventas por rol");
            System.out.println("2) Desplegar recaudacion total de ventas por region");
            System.out.println("3) Desplegar recaudacion de ventas por personaje");
            System.out.println("4) Desplegar la cantidad de personajes por rol existente");
            System.out.println("5) Agregar un personaje al juego");
            System.out.println("6) Agregar una skin a un personaje del juego");
            System.out.println("7) Bloquear un jugador");
            System.out.println("8) Desplegar todas las cuentas de mayor a menor");
            System.out.println("\nDigite 'salir' para salir");
        }
    }

    public static void menuCliente(String nombreCuenta,SistemaRitoGames sistema) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido, Que desea realizar?");
        System.out.println("\n1) Desplegar skins disponibles para comprar");
        System.out.println("2) Comprar Skin");
        System.out.println("3) Comprar Personaje");
        System.out.println("4) Mostrar inventario");
        System.out.println("5) Recargar RP");
        System.out.println("6) Mostrar datos del usuario");
        System.out.println("7) Cambiar contrasena");
        System.out.println("\nDigite 'salir' para salir");
        while(true){
            String opcion = scanner.next();
            Boolean salir = false;
            switch (opcion) {
                case "1":
                    System.out.println(sistema.obtenerSkinsPersonajes());
                    break;
                
                case "2":
                    System.out.println("\nIngrese el personaje al que le desea comprar una skin: ");
                    String nombrePersonajeSkinComprar = scanner.next();
                    System.out.println("\nIngrese el nombre de la skin: ");
                    String nombreSkinComprar = scanner.next();
                    sistema.comprarSkin(nombreCuenta, nombrePersonajeSkinComprar, nombreSkinComprar);
                    break;
                
                case "3":
                    System.out.println("\nIngrese el nombre del personaje que desea comprar: ");
                    String nombrePersonajeComprar = scanner.next();
                    sistema.comprarPersonaje(nombreCuenta, nombrePersonajeComprar);
                    break;
            
                case "4":
                    System.out.println(sistema.obtenerDatosUsuario(nombreCuenta));
                    break;

                case "5":
                    System.out.println("Ingrese la cantidad de rp a agregar: ");
                    int rp = scanner.nextInt();
                    try {
                        sistema.recargarRP(nombreCuenta,rp);
                        System.out.println("Se agregaron: " +rp+ " a la cuenta.");
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "6":
                    System.out.println(sistema.obtenerDatosUsuario(nombreCuenta));
                    break;
                    
                case "7":
                    System.out.println("Ingrese su contrasena actual");
                    String oldPassword = scanner.next();
                    System.out.println("Ingrese la nueva contrasena");
                    String newPassword = scanner.next();
                    System.out.println("Confirme la nueva contrasena");
                    String newPasswordConf = scanner.next();
                    if(sistema.iniciarSesion(nombreCuenta,oldPassword).equals("Bienvenido!") && newPassword.equals(newPasswordConf)) {
                        try {
                            sistema.cambiarPassword(nombreCuenta,oldPassword,newPassword);
                            System.out.println("Se cambio la contrasena con exito");
                        }catch(Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }else {
                        System.out.println("Ingresaste mal alguna contrasena");
                    }
                    break;

                case "salir":
                    salir = true;
                    break;
                default:
                    System.out.println("La opcion ingresada no es valida.");
            }
            if(salir)break;
            System.out.println("\n1) Desplegar skins disponibles para comprar");
            System.out.println("2) Comprar Skin");
            System.out.println("3) Comprar Personaje");
            System.out.println("4) Mostrar inventario");
            System.out.println("5) Recargar RP");
            System.out.println("6) Mostrar datos del usuario");
            System.out.println("7) Cambiar contrasena");
            System.out.println("\nDigite 'salir' para salir");
        }

    }
    
    private static void cierreSistema(SistemaRitoGames sistema)  {
        try {
            sobreEscribirCuentas(sistema);
            sobreEscribirPersonajes(sistema);
            sobreEscribirEstadisticas(sistema);
        } catch (Exception e) {
            System.out.println("Hubo un error en el cierre de sistema, comprobar archivos de texto");
        }
        
    }

    public static String hacerMayuscula(String txt){
        String original = txt;
        String stringFinal = txt.substring(0,1).toUpperCase();
        stringFinal += original.substring(1,original.length());
        return stringFinal;
    }

    private static void sobreEscribirCuentas(SistemaRitoGames sistema) {
        try {
            BufferedWriter escritura = new BufferedWriter(new FileWriter("Cuentas.txt"));
            escritura.write(sistema.obtenerDatosUsuarios());
            escritura.close();
        } catch (Exception e) {
            System.out.println("No se pudo sobreescribir el archivo Cuentas.txt");
        }
    }

    private static void sobreEscribirPersonajes(SistemaRitoGames sistema) {
        try {
            BufferedWriter escritura = new BufferedWriter(new FileWriter("Personajes.txt"));
            escritura.write(sistema.obtenerDatosPersonajes());
            escritura.close();
        } catch (Exception e) {
            System.out.println("No se pudo sobreescribir el archivo Personajes.txt");
        }
    }

    private static void sobreEscribirEstadisticas(SistemaRitoGames sistema) {
        try {
            BufferedWriter escritura = new BufferedWriter(new FileWriter("Estadisticas.txt"));
            escritura.write(sistema.obtenerDatosEstadisticas());
            escritura.close();
        } catch (Exception e) {
            System.out.println("No se pudo sobreescribir el archivo Estadisticas.txt");
        }
    }


}

