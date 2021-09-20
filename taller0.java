import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taller0 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
    }

    public static int intIndex(int array [], int element){
        int i = 0;
        int cantidad = array.length;
        
        if (array[0] == 0){
            return -1;
        }
        
        while (i < cantidad) {
            if (array[i] == 0){
                return -1;
            }

            if (array[i] == element){
                return i;
            } else {
                i++;
            }
        }
        return -1;
        
    }

    public static int intVectorFill (int [] array, int data, int accountant){
        int index = intIndex(array, data);
        
        if (index == -1){
            array[accountant] = data;
            accountant++;
        }
        return accountant;
    
    }

    public static int stringIndex(String array [], String element){
        int i = 0;
        int cantidad = array.length;
        
        if (array[0] == null){
            return -1;
        }
        
        while (i < cantidad) {
            if (array[i] == null){
                return -1;
            }

            if (array[i].equals(element)){
                return i;
            } else {
                i++;
            }
        }
        return -1;   
    }

    public static int stringVectorFill (String [] array, String data, int accountant ){
        int index = stringIndex(array, data);
        
        if (index == -1){
            array[accountant] = data;
            accountant++;
        }
        return accountant;
    
    }

    //SE PUEDE CONSIDERAR CADA ASIENTO COMO UN PRODUCTO,
    //ASÍ SE DESCUENTA DEL STOCK DEL CINE Y AGREGA AL STOCK DEL CLIENTE
    /** //SUBPROGRAMA LLENADO MATRIZ INTEGER (para aumentar la cantidad de productos en una posición)
    public static void matrixFill(int [][] matrix, int rowIndex, int columnIndex, String operation){
        
    	if (operation == "suma"){
            matrix[rowIndex][columnIndex] += 1;
        } else if (operation == "resta"){
            matrix[rowIndex][columnIndex] -= 1;
        }
    }*/
    
    public static Boolean systemShutdown() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Desea cerrar el sistema? (si para cerrar sl sistema)");
        String cierre = sc.next();

        
        if(cierre.equals("si")){
            return true;
        } else {
            return false;
        }
    }

    public static int verify(String Id, String[] Ids){
       
    	int cantidadLetras = Id.length();

        int peopleIndex;
        if (cantidadLetras == 12){
            // con puntos y con guion (2 digitos antes del primer punto)
            if (Id.substring(11,12).equals("K") || Id.substring(11,12).equals("k")){
                peopleIndex = stringIndex(Ids,Id);
                String rutK = Id.substring(0, 11);
                if (peopleIndex ==-1 ){
                    rutK = rutK.concat("K");                
                    
                }
                peopleIndex = stringIndex (Ids,rutK);
                return peopleIndex;
            }
            peopleIndex = stringIndex(Ids,Id);
            if (Id.equals(Ids[peopleIndex])){
                return peopleIndex;
            }
        }

        if (cantidadLetras ==11){
            // con puntos y con guion (1 digito antes del primer punto)
            if (Id.substring(10,11).equals("K") || Id.substring(10,11).equals("k")){
                peopleIndex = stringIndex(Ids,Id);
                String rutK = Id.substring(0, 10);
                if (peopleIndex ==-1){
                    rutK = rutK.concat("K");
                } else {
                    return peopleIndex;
                }

                peopleIndex = stringIndex (Ids,rutK);
                return peopleIndex;
            }
            peopleIndex = stringIndex(Ids,Id);
            if (Id.equals(Ids[peopleIndex])){
                return peopleIndex;
            }
            
        }

        if (cantidadLetras == 10){
            // sin puntos y con guion (2 digitos antes del primer punto)
            String primeraParte = Id.substring(0, 2);
            String segundaParte = Id.substring(2, 5);
            String terceraParte = Id.substring(5, 8);
            String digitoVerificador = Id.substring(8, 10);
            
            String rutNuevo = primeraParte.concat("." + segundaParte + "." + terceraParte+digitoVerificador);

            if (rutNuevo.endsWith("K") || rutNuevo.endsWith("k")){
                peopleIndex = stringIndex(Ids,rutNuevo);
                String rutK = rutNuevo.substring(0, 11);
                if (peopleIndex ==-1){
                    rutK = rutK.concat("K");
                } else {
                    return peopleIndex;
                }

                peopleIndex = stringIndex (Ids,rutK);
                return peopleIndex;
            }
            
            peopleIndex = stringIndex(Ids,rutNuevo);
            if (rutNuevo.equals(Ids[peopleIndex])){
                return peopleIndex;
            }
        }
        //-->
        if (cantidadLetras == 9){

            if (Id.substring(0, 8).endsWith("-")){
                // sin puntos y con guion  (1 digito antes del primer punto)
                String primeraParte = Id.substring(0, 1);
                String segundaParte = Id.substring(1, 4);
                String terceraParte = Id.substring(4, 7);
                String digitoVerificador = Id.substring(7, 9);
    
                String rutUnDigito = primeraParte.concat("." + segundaParte + "." + terceraParte+digitoVerificador);
                if (rutUnDigito.endsWith("K") || rutUnDigito.endsWith("k")){
                    peopleIndex = stringIndex(Ids,rutUnDigito);
                    
                    String rutK = rutUnDigito.substring(0, 10);
                    if (peopleIndex ==-1){ 
                        rutK = rutK.concat("K");
                    } else {
                        return peopleIndex;
                    }
                    peopleIndex = stringIndex (Ids,rutK);
                    return peopleIndex;
                }
                
                peopleIndex = stringIndex(Ids,Id);
                if (Id.equals(Ids[peopleIndex])){
                    return peopleIndex;
                }
    
                
            } else {
                String primeraParte = Id.substring(0, 2);
                String segundaParte = Id.substring(2, 5);
                String terceraParte = Id.substring(5, 8);
                String digitoVerificador = Id.substring(8, 9);

                String rutDosDigitos = primeraParte.concat("." + segundaParte + "." + terceraParte+"-"+digitoVerificador);
                if (rutDosDigitos.endsWith("K") || rutDosDigitos.endsWith("k")){
                    peopleIndex = stringIndex(Ids,rutDosDigitos);
                    
                    String rutK = rutDosDigitos.substring(0, 11);
                    if (peopleIndex ==-1){ 
                        rutK = rutK.concat("K");
                    } else {
                        return peopleIndex;
                    }
                    peopleIndex = stringIndex (Ids,rutK);
                    return peopleIndex;
                }
                peopleIndex = stringIndex(Ids,rutDosDigitos);
                if (rutDosDigitos.equals(Ids[peopleIndex])){
                    return peopleIndex;
                }
            }
        }

        if (cantidadLetras == 8){
            // sin puntos y sin guion (1 digito antes del primer punto)

            String primeraParte = Id.substring(0, 1);
            String segundaParte = Id.substring(1, 4);
            String terceraParte = Id.substring(4, 7);
            String digitoVerificador = Id.substring(7, 8);

            String rutNuevo = primeraParte.concat("." + segundaParte + "." + terceraParte+"-"+digitoVerificador);
            if (rutNuevo.endsWith("K") || rutNuevo.endsWith("k")){
                peopleIndex = stringIndex(Ids,rutNuevo);
                
                String rutK = rutNuevo.substring(0, 10);
                if (peopleIndex ==-1){ 
                    rutK = rutK.concat("K");
                } else {
                    return peopleIndex;
                }
                peopleIndex = stringIndex (Ids,rutK);
                return peopleIndex;
            }
            peopleIndex = stringIndex(Ids,rutNuevo);
            if (rutNuevo.equals(Ids[peopleIndex])){
                return peopleIndex;
            }
        }

        return -1;
    }

    public static int register(int peopleQuantity, String[] names, String [] lastNames,String[]Ids, String[]passwords, int[]balances) {
        int contadorPersonas = peopleQuantity;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su nombre: ");
        String nombre = sc.next();
        stringVectorFill(names, nombre, contadorPersonas);

        System.out.println("Ingrese su apellido: ");
        String apellido = sc.next();
        stringVectorFill(lastNames, apellido, contadorPersonas);

        System.out.println("Ingrese su rut (con puntos y gui�n): ");
        String Id = sc.next();
        stringVectorFill(Ids, Id, contadorPersonas);

        System.out.println("Ingrese su contrase�a: ");
        String password = sc.next();
        stringVectorFill(passwords, password, contadorPersonas);

        System.out.println("Ingrese su saldo: ");
        int saldo = sc.nextInt();
        intVectorFill(balances, saldo, contadorPersonas);
        contadorPersonas++;
        System.out.println("Registrado correctamente");

        return contadorPersonas;
        
    }

    public static void displayInformation(int peopleIndex, String[] names, String [] lastNames,  String [] rut, int [] saldo, int [][] inventory, String[] products, int productsLength) {
        
        System.out.println("La información del usuario es: \n Nombre: "+names[peopleIndex] + "\n Apellido: " + lastNames[peopleIndex] + "\n Rut: "+ rut[peopleIndex] + "\n Saldo: " 
        +saldo[peopleIndex]+"\n Y sus entradas compradas son: ");

        /** //puede servir para mostrar las entradas de cine que posee el cliente
        System.out.println(names[peopleIndex] + " " + lastNames[peopleIndex] + " tu inventario es: ");
        
        for (int i = 0; i < productsLength; i++){
            if (inventory[peopleIndex][i] > 0){
                System.out.println(products[i]+": " + inventory[peopleIndex][i]);
            }
        } */
        
    }

    public static void increaseCash(int peopleIndex, int[]balances) {
        Scanner sc = new Scanner (System.in);
        System.out.println("Ingrese el saldo que quiere agregar");
        int saldo = sc.nextInt();

        balances[peopleIndex] += saldo;

    }

    public static int[] buy(int [][] stockMatrix,int[][] inventory,int [][] priceMatrix, String operation,String operation2, String [] roomVector, String [] timeVector,
    String[]movieVector, int peopleIndex, int[] peopleCash, int[] buyProductVector, int[] buyStoreVector, int productsLength, int storesLength, String[]producType) {
       
        Scanner sc = new Scanner(System.in);
        int comicsVendidos = 0;
        int mangasVendidos = 0;
        //CREAR UN DISPLAY DE LAS PELICULAS O REORDENAR ESTE PARA QUE DESPLIGUE EN ESTE FORMATO 
        //(A MEDIDA QUE VAN SELECCIONANDO UNO, SALE EL OTRO): PELICULAS >> HORARIOS >> SALAS >> ASIENTOS
        System.out.println("Listado de productos: ");//ESTE HABRÍA QUE MODIFICARLO PARA QUE SEA EL DESPLIGUE DE LAS PELÍCULAS EN CARTELERA
        
        for (int i = 0; i < productsLength; i++){
            System.out.println(movieVector[i]);
        }
        System.out.println("Ingrese la película que desea ver: ");//SE SELECCIONA LA PELÍCULA
        String movie = sc.nextLine();
        
        int movieIndex = stringIndex(movieVector, movie);
        if (movieIndex != -1){
            for (int i = 0; i < storesLength; i++){
                System.out.println("La película seleccionada tiene un valor de $" + priceMatrix[i][movieIndex] + 
                " por entrada, y tiene " + stockMatrix[i][movieIndex] + " funciones " + roomVector[i]);
            }

            System.out.println("Ingrese el horario en el que desea ver su película ");//SE SELECCIONA EL HORARIO
            String time = sc.nextLine();

            int indiceHorario = stringIndex(timeVector, time);
            //AQUÍ FALTA ALGO, AÚN NO SÉ QUÉ ES, PERO TODAVÍA HAY TIEMPO

            System.out.println("Ingrese la tienda en la que desea comprar ");//SE SELECCIONA LA SALA
            String room = sc.nextLine();

            int roomIndex = stringIndex(roomVector, room);

            if (roomIndex != -1){
                if (peopleCash[peopleIndex] >= priceMatrix[roomIndex][movieIndex]){
                	if (stockMatrix[roomIndex][movieIndex]==0) {
                		System.out.println("No quedan productos");
                	} else {
                		System.out.println("Compra de entradas realizada correctamente");
                		matrixFill(stockMatrix, roomIndex, movieIndex, operation);
                		matrixFill(inventory, peopleIndex, movieIndex, operation2);
                		buyProductVector[movieIndex] += priceMatrix[roomIndex][movieIndex];
                		buyStoreVector[roomIndex] += priceMatrix[roomIndex][movieIndex];  
                		if (producType[roomIndex].equals("Manga")) {
                			mangasVendidos +=1;
                		}
                		if (producType[roomIndex].equals("Comic")) {
                			comicsVendidos +=1;
                		}
                		
                	}
                } else {
                    System.out.println("Saldo insuficiente");
                }
            } else {
                System.out.println("La SALA fue ingresada incorrectamente");
            }
        } else {
            System.out.println("Asiento no disponible");
        }

        return new int[] {comicsVendidos,mangasVendidos};
        
    }






}
