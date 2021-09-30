import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taller0 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		String[] l_nombrePersonas = new String[500];
		String[] l_apellidos = new String[500];
		String[] l_ruts = new String[500];
		String[] l_passwords = new String[500];
		int[] l_saldos = new int[500];
		String[] l_estados = new String[500];

		String[] l_nombrePeliculas = new String[500];
		String[] l_tipo = new String[500];
		int[] l_recaudacion = new int[500];

		int[][] sala1M = new int[10][30];
		int[][] sala1T = new int[10][30];
		int[][] sala2M = new int[10][30];
		int[][] sala2T = new int[10][30];
		int[][] sala3M = new int[10][30];
		int[][] sala3T = new int[10][30];

		Boolean[][] carteleraM = new Boolean[500][3];
		Boolean[][] carteleraT = new Boolean[500][3];

		int cant_archClientes = LeerClientes(l_nombrePersonas, l_apellidos, l_ruts, l_passwords, l_saldos);
		LeerStatus(l_ruts, l_estados, cant_archClientes);
		int cant_archPeliculas = LeerPeliculas(l_nombrePeliculas, l_tipo, l_recaudacion);
		definirCarteleras(carteleraM,carteleraT,l_nombrePeliculas,cant_archPeliculas);

		llenadoMatrizBase(sala1M);
		llenadoMatrizBase(sala1T);
		llenadoMatrizBase(sala2M);
		llenadoMatrizBase(sala2T);
		llenadoMatrizBase(sala3M);
		llenadoMatrizBase(sala3T);


		IniciarSesion(l_nombrePersonas, l_ruts, cant_archClientes, l_apellidos, l_passwords, l_saldos, l_estados);

		sc.close();

	}

	public static int LeerClientes(String[] l_nombrePersonas, String[] l_apellidos, String[] l_ruts,String[] l_passwords, int[] l_saldos) throws FileNotFoundException {
		Scanner arch = new Scanner(new File("clientes.txt"));
		int cant = 0;
		while (arch.hasNextLine()) {
			String linea = arch.nextLine();
			String[] datos = linea.split(",");
			String nombrePersona = datos[0];
			String apellido = datos[1];
			String rut = datos[2];
			String password = datos[3];
			int saldo = Integer.parseInt(datos[4]);
			l_nombrePersonas[cant] = nombrePersona;
			l_apellidos[cant] = apellido;
			l_ruts[cant] = rut;
			l_passwords[cant] = password;
			l_saldos[cant] = saldo;
			cant++;
		}

		arch.close();
		return cant;
	}

	public static void LeerStatus(String[] l_ruts, String[] l_estados, int cant_archClientes) throws FileNotFoundException {
		Scanner arch = new Scanner(new File("status.txt"));
		while (arch.hasNextLine()) {
			String linea = arch.nextLine();
			String[] datos = linea.split(",");
			String rut = datos[0];
			int auxIndex = buscarIndexString(l_ruts, rut, cant_archClientes);

			if (auxIndex != -1) {
				l_estados[auxIndex] = datos[1];
			}
		}
	}

	public static int LeerPeliculas(String[] l_nombrePeliculas, String[] l_tipo, int[] l_recaudacion) throws FileNotFoundException{
		Scanner arch = new Scanner(new File("peliculas.txt"));
		int cant = 0;
		while (arch.hasNextLine()) {
			String linea = arch.nextLine();
			String[] datos = linea.split(",");
			String nombrePelicula = datos[0];
			String tipo = datos[1];
			int recaudacion = Integer.parseInt(datos[2]);

			l_nombrePeliculas[cant] = nombrePelicula;
			l_tipo[cant] = tipo;
			l_recaudacion[cant] = recaudacion;
			cant++;
		}

		arch.close();
		return cant;
	}

	public static void definirCarteleras(Boolean[][] carteleraM, Boolean[][] carteleraT, String[] peliculas, int cant) throws FileNotFoundException {
		Scanner arch = new Scanner (new File("peliculas.txt"));
		
		for (int i = 0 ; i <= cant ; i++) {
			for (int j = 0 ; j <= 2 ; j++) {
				carteleraM[i][j] = false;
				carteleraT[i][j] = false;
			}
		}
		
		while(arch.hasNextLine()) {
			String linea = arch.nextLine();
			String [] datos = linea.split(",");
			String nombrePelicula = datos[0];
			int auxPelicula = buscarIndexString(peliculas, nombrePelicula, cant);
			
			for (int i = 3 ; i < datos.length ; i+=2 ) {
				int sala = Integer.parseInt(datos[i]);
				String horario = datos[i+1];
				
				switch (horario) {
				
				case "M":
					if (sala == 1) {
						carteleraM[auxPelicula][0] = true;						
					}
					
					if (sala == 2) {
						carteleraM[auxPelicula][1] = true;	
						
					}
				
					if (sala == 3) {
						carteleraM[auxPelicula][2] = true;	
					}
					
					break;
					
				case "T":
					
					if (sala == 1) {
						carteleraT[auxPelicula][0] = true;	
					}
					
					if (sala == 2) {
						carteleraT[auxPelicula][1] = true;	
					}
				
					if (sala == 3) {
						carteleraT[auxPelicula][2] = true;	
					}
					
					break;							
				}
			}
		}
		arch.close();
	}

	public static int buscarIndexString(String[] array, String key, int cant) {
		for (int i = 0; i <= cant; i++) {
			if (array[i].equals(key)) {
				return i;
			}
		}

		return -1;
	}

	public static int buscarIndexInt(int[] array, int key, int cant) {
		for (int i = 0; i <= cant; i++) {
			if (array[i] == key) {
				return i;
			}
		}
		return -1;
	}

	//FALTAN COSAS (LA TAQUILLA, LOS DESPLEGAR INFORMACIÓN, COMPRAR Y DEVOLVER ENTRADAS)
    public static void IniciarSesion(String[] nombres,String[] ruts,int cantidadClientes,String[]apellidos,String[]passwords,int[]saldos,String[]estados) {
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<cantidadClientes;i++)
        {
            System.out.println("\n"+nombres[i]+","+apellidos[i]+","+ruts[i]+","+passwords[i]+",$"+saldos[i]);
        }
        System.out.println("\n==============\nINICIAR SESION\n==============");
        System.out.println("\nIngrese su rut: ");
        String Id = sc.next();

        System.out.println("\nIngrese su password: ");
        String password = sc.next();
        
        while(true){
        	Boolean indiceNegativo = false;
        	int verificacion = -1;
            try {

                verificacion = VerificarRut(ruts, Id, cantidadClientes);
            } catch (Exception e) {
                verificacion = -1;
            }
            //int peopleIndex = index(Ids, Id);
            
            if (verificacion==(-1)|| !password.equals(passwords[verificacion])){
            	if (Id.equals("ADMIN") && password.equals("ADMIN")){
        			while(true){                
        				System.out.println("\nADMIN: Que opcion desea realizar?");
        				System.out.println("a) revisar taquilla");
        				System.out.println("b) informacion de clientes\n");
        				String opcion = sc.next();
        				
        				switch(opcion){
        				case "a":   
        					System.out.println("\nLa taquilla es: \n");
        					break;
        					
        				case "b":
        					System.out.println("\nIngrese el rut del cliente que quiere consultar informacion: ");
                            String rutAnalizar = sc.next();
                            while(true){
                                Boolean indiceNegativo2 = false;
                                int verificacion2 = -1;
                                try {
                                    verificacion2 = VerificarRut(ruts, rutAnalizar, cantidadClientes);
                                } 
                                catch (Exception e) {
                                    verificacion2 = -1;
                                }
                                if(!indiceNegativo2){
                                    while(true){
                                        System.out.println("\nINFORMACION DEL CLIENTE: ");
                                        System.out.println("El nombre del cliente es: "+nombres[verificacion2]+" "+apellidos[verificacion2]+", de rut: "+ruts[verificacion2]+"\nCon saldo: $"+saldos[verificacion2]+"\nY sus entradas compradas son: ");
                                        break;
                                    }
                                }
                            break;
                            }
        					
        				default:
        					System.out.println("\nOpcion no valida");
        				}
        				System.out.println("\nDesea cerrar sesion?\n<si> para salir.\n<cualquier otra cosa> para continuar.");
        				String salir = sc.next();
        				
        				if (salir.equals("si")){
        					break;
        				}
        			}
        		
            	} else {
            		System.out.println("\nDatos ingresados incorrectamente");
					System.out.println("\nDesea registrarse? (si-no)");
            		String registro = sc.next();
            		

            		if (registro.equals("si")){
            			registro(nombres,apellidos,ruts,passwords,saldos,estados,cantidadClientes);
						break;
            		}
            	}
            	indiceNegativo = true;
            }
            if(!indiceNegativo) {
            	while(true){
            		System.out.println("\nBienvenid@ "+nombres[verificacion] + ": Que opcion desea realizar?");
            		System.out.println("a) Comprar entradas");
            		System.out.println("b) Agregar saldo");
            		System.out.println("c) Ver informacion de usuario");
					System.out.println("d) Devolver entradas\n");
            		String opcion = sc.next();
            				
            		switch(opcion){
            			case "a":   
                            //saleByType = buy(stock,inventory, precioUnitario, "resta","suma", storesName, productsName,verificacion, balances, recaudacionProductoCompras, recaudacionCompras, cantidadProductos, cantidadTiendas,productsType);
                            System.out.println("\nUsted ha seleccionado <comprar entradas>, a continuacion le mostraremos la cartelera: ");
                            break;

            			case "b":
            				increaseCash(verificacion, saldos);
                            System.out.println("\nAumento de saldo realizado con exito");
            				break;
            					
            			case "c":
                            //displayInformation(verificacion, names, lastNames, inventory, productsName,cantidadProductos);
                            //System.out.println("Su saldo es: $"+balances[verificacion]);
                            System.out.println("\nUsted ha seleccionado <Ver informacion de usuario>,\na continuacion se desplegará la información perteneciente al usuario en uso");
                            System.out.println("Su nombre es: "+nombres[verificacion]+" "+apellidos[verificacion]+", de rut: "+ruts[verificacion]+"\nCon saldo: $"+saldos[verificacion]+"\nY sus entradas compradas son: ");
                            break;
						case "d":
							System.out.println("\nUsted ha seleccionado <devolver entradas>, a continuacion le mostraremos sus entradas para que las seleccione: ");
							break;
            					
            			default:
            				System.out.println("\nOpcion no valida");
            		}
            		System.out.println("\nDesea cerrar sesion?\n<si> para salir.\n<cualquier otra cosa> para continuar.");
            		String salir = sc.next();
            				
            		if (salir.equals("si")){
            			break;
            		}
            	}	
            }
   
            Boolean cierre = CierreSistema();
            if (cierre) {
            	break;
            } else {
            	System.out.println("\nIngrese su rut: ");
            	Id = sc.next();
            		
            	System.out.println("\nIngrese su password: ");
            	password = sc.next();
            }
        }
        sc.close();
    }

    public static Boolean CierreSistema() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("\nDesea cerrar el sistema?\n<si> para cerrar el sistema.\n<cualquier otra cosa> para continuar.");
        String cierre = sc.nextLine();

        
        if(cierre.equals("si")){
            return true;
        } else {
            return false;
        }
    }        

	public static int VerificarRut(String[] l_ruts, String rut, int cant) {

		int cantLetras = rut.length();
		int auxIndex;

		if (cantLetras == 12) {
			// con puntos y con guion.
			if (rut.endsWith("K") || rut.endsWith("k")) {
				String rutK = rut.substring(0, 11);
				auxIndex = buscarIndexString(l_ruts, rut, cant);
				if (auxIndex == -1) {
					rutK = rutK.concat("K");
					auxIndex = buscarIndexString(l_ruts, rutK, cant);
					return auxIndex;
				}

				else {
					return auxIndex;
				}
			}

			else {
				auxIndex = buscarIndexString(l_ruts, rut, cant);
				if (auxIndex != -1) {
					return auxIndex;
				}

				else {
					return auxIndex;
				}
			}
		}

		if (cantLetras == 11) {
			// con puntos y con guion (1 digito antes del primer punto)
			if (rut.endsWith("K") || rut.endsWith("k")) {
				String rutK = rut.substring(0, 10);
				auxIndex = buscarIndexString(l_ruts, rut, cant);
				if (auxIndex == -1) {
					rutK = rutK.concat("K");
					auxIndex = buscarIndexString(l_ruts, rutK, cant);
					return auxIndex;
				}

				else {
					return auxIndex;
				}
			}

			else {
				auxIndex = buscarIndexString(l_ruts, rut, cant);
				if (auxIndex != -1) {
					return auxIndex;
				}

				else {
					return auxIndex;
				}
			}
		}

		if (cantLetras == 10) {
			// sin puntos y con guion (2 digitos antes del primer punto)
			String primeraParte = rut.substring(0, 2);
			String segundaParte = rut.substring(2, 5);
			String terceraParte = rut.substring(5, 8);
			String digitoVerificador = rut.substring(8, 10);
			String rutNuevo = primeraParte.concat("." + segundaParte + "." + terceraParte + digitoVerificador);

			if (rutNuevo.endsWith("K") || rutNuevo.endsWith("k")) {
				String rutK = rutNuevo.substring(0, 11);

				auxIndex = buscarIndexString(l_ruts, rutNuevo, cant);
				if (auxIndex == -1) {
					rutK = rutK.concat("K");
					auxIndex = buscarIndexString(l_ruts, rutK, cant);
					return auxIndex;
				}

				else {
					return auxIndex;
				}
			}

			else {
				auxIndex = buscarIndexString(l_ruts, rutNuevo, cant);
				if (auxIndex != -1) {
					return auxIndex;
				}

				else {
					return auxIndex;
				}
			}
		}

		if (cantLetras == 9) {

			if (rut.substring(0, 8).endsWith("-")) { // sin puntos y con guion (1 digito antes del primer punto)

				String primeraParte = rut.substring(0, 1);
				String segundaParte = rut.substring(1, 4);
				String terceraParte = rut.substring(4, 7);
				String digitoVerificador = rut.substring(7, 9);
				String rutUnDigito = primeraParte.concat("." + segundaParte + "." + terceraParte + digitoVerificador);

				if (rutUnDigito.endsWith("K") || rutUnDigito.endsWith("k")) {
					String rutK = rutUnDigito.substring(0, 10);

					auxIndex = buscarIndexString(l_ruts, rutUnDigito, cant);
					if (auxIndex == -1) {
						rutK = rutK.concat("K");
						auxIndex = buscarIndexString(l_ruts, rutK, cant);
						return auxIndex;
					}

					else {
						return auxIndex;
					}
				}

				else {
					auxIndex = buscarIndexString(l_ruts, rutUnDigito, cant);
					if (auxIndex != -1) {
						return auxIndex;
					}

					else {
						return auxIndex;
					}
				}
			}

			else { // sin puntos y sin guion (2 digitos antes del primer punto)
				String primeraParte = rut.substring(0, 2);
				String segundaParte = rut.substring(2, 5);
				String terceraParte = rut.substring(5, 8);
				String digitoVerificador = rut.substring(8, 9);
				String rutDosDigitos = primeraParte
						.concat("." + segundaParte + "." + terceraParte + "-" + digitoVerificador);

				if (rutDosDigitos.endsWith("K") || rutDosDigitos.endsWith("k")) {
					String rutK = rutDosDigitos.substring(0, 11);

					auxIndex = buscarIndexString(l_ruts, rutDosDigitos, cant);
					if (auxIndex == -1) {
						rutK = rutK.concat("K");
						auxIndex = buscarIndexString(l_ruts, rutK, cant);
						return auxIndex;
					}

					else {
						return auxIndex;
					}
				}

				else {

					auxIndex = buscarIndexString(l_ruts, rutDosDigitos, cant);
					if (auxIndex != -1) {
						return auxIndex;
					}

					else {
						return auxIndex;
					}
				}
			}
		}

		if (cantLetras == 8) {
			// sin puntos y sin guion (1 digito antes del primer punto)
			String primeraParte = rut.substring(0, 1);
			String segundaParte = rut.substring(1, 4);
			String terceraParte = rut.substring(4, 7);
			String digitoVerificador = rut.substring(7, 8);
			String rutNuevo = primeraParte.concat("." + segundaParte + "." + terceraParte + "-" + digitoVerificador);

			if (rutNuevo.endsWith("K") || rutNuevo.endsWith("k")) {
				String rutK = rutNuevo.substring(0, 10);

				auxIndex = buscarIndexString(l_ruts, rutNuevo, cant);
				if (auxIndex == -1) {
					rutK = rutK.concat("K");
					auxIndex = buscarIndexString(l_ruts, rutK, cant);
					return auxIndex;
				}

				else {
					return auxIndex;
				}
			}

			else {
				auxIndex = buscarIndexString(l_ruts, rutNuevo, cant);
				if (auxIndex != -1) {
					return auxIndex;
				}

				else {
					return auxIndex;
				}
			}
		}

		return -1;
	}
	
	public static void registro(String[] nombres, String[] apellidos, String[] ruts, String[] passwords, int[] saldos, String[] estados, int cantidadClientes) {
        Scanner sc = new Scanner(System.in);
		System.out.println("\nIngrese su nombre: ");
		String nombre = sc.nextLine();
		StringLlenarArray(nombres, nombre, cantidadClientes);

		System.out.println("\nIngrese su apellido: ");
		String apellido = sc.nextLine();
		StringLlenarArray(apellidos, apellido, cantidadClientes);

		System.out.println("\nIngrese su rut (con puntos y guion): ");
		String Id = sc.nextLine();
		StringLlenarArray(ruts, Id, cantidadClientes);

		System.out.println("\nIngrese su saldo: ");
		int saldo = Integer.parseInt(sc.nextLine());
		IntLlenarArray(saldos, saldo, cantidadClientes);

		System.out.println("\nIngrese su estado covid (HABILITADO - NO HABILITADO): ");
		String estado = sc.nextLine();
		StringLlenarArray(estados, estado, cantidadClientes);

		System.out.println("\nIngrese su contraseña: ");
		String passwordRegistro = sc.nextLine();

		System.out.println("\nConfirme su contraseña: ");
		String password2 = sc.next();
		if(passwordRegistro.equals(password2)) {
			StringLlenarArray(passwords, passwordRegistro, cantidadClientes);
			System.out.println("Registrado correctamente");
		} else {
			System.out.println("Hubo un error en el registro");
		}
	}

	public static void StringLlenarArray(String[] array, String variable, int cant) {

		int i = 0;

		while (i <= cant - 1) {
			i++;
		}

		for (int j = cant; j >= i + 1; j--) { // Corrimiento
			array[j] = array[j - 1];
		}

		array[i] = variable;
	}

	public static void IntLlenarArray(int[] array, int variable, int cant) {

		int i = 0;

		while (i <= cant - 1) {
			i++;
		}

		for (int j = cant; j >= i + 1; j--) { // Corrimiento
			array[j] = array[j - 1];
		}

		array[i] = variable;
	}

	//FALTA
	public static void reWriteClients(String file, String[] names, String[] lastNames, String[] ruts,
			String[] passwords, int[] balances) {
			
	}

	//FALTA
	public static void reWriteMovies(String file, String[] titles, String[] tipe, int[] moneyRaised,
			String[] schedules) {

	}

	//FALTA
	public static void buyTickets() { // int[] buyTickets() {

	}

	//FALTA
	public static void returnTickets() {

	}

	//FALTA
	public static void salesCalculator() {// para calcular las recaudaciones O VER PARA AGREGAR DIRECTAMENTE AL DESPLEGAR RECAUDACIONES

	}

    public static void increaseCash(int peopleIndex, int[]balances) {
        Scanner sc = new Scanner (System.in);
        System.out.println("\nIngrese el saldo que quiere agregar: ");
        int saldo = sc.nextInt();

        balances[peopleIndex] += saldo;

    }

	//FALTA
	public static void displayUserInformation() {

	}

	//FALTA
	public static void displayMovies() {

	}
	
	//FALTA
	public static void displayMoneyRaised() {

	}

	//FALTA
	public static void displayClientInformation() {

	}

	//FALTA
	public static void displaySeating() {

	}

    public static void llenadoMatrizBase(int[][] salaCine){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 30; j++){
				salaCine[i][j] = 1;
                
                salaCine[0][0] = 0;salaCine[0][1] = 0;salaCine[0][2] = 0;salaCine[0][3] = 0;salaCine[0][4] = 0;salaCine[0][29] = 0;salaCine[0][28] = 0;salaCine[0][27] = 0;salaCine[0][26] = 0;salaCine[0][25] = 0;
                salaCine[1][0] = 0;salaCine[1][1] = 0;salaCine[1][2] = 0;salaCine[1][3] = 0;salaCine[1][4] = 0;salaCine[1][29] = 0;salaCine[1][28] = 0;salaCine[1][27] = 0;salaCine[1][26] = 0;salaCine[1][25] = 0;
                salaCine[2][0] = 0;salaCine[2][1] = 0;salaCine[2][2] = 0;salaCine[2][3] = 0;salaCine[2][4] = 0;salaCine[2][29] = 0;salaCine[2][28] = 0;salaCine[2][27] = 0;salaCine[2][26] = 0;salaCine[2][25] = 0;
                salaCine[3][0] = 0;salaCine[3][1] = 0;salaCine[3][2] = 0;salaCine[3][3] = 0;salaCine[3][4] = 0;salaCine[3][29] = 0;salaCine[3][28] = 0;salaCine[3][27] = 0;salaCine[3][26] = 0;salaCine[3][25] = 0;
               
                //LOS ASIENTOS CON "0" SON LOS DISPONIBLES O UTILIZABLES, LOS "1" SON LOS INCAPACITADOS
                
                if (salaCine[i][j] == 1 && i % 2 == 0 && j % 2 == 0) {
                	salaCine[i][j] = 0;
                }
                if (salaCine[i][j] == 1 && i % 2 != 0 && j % 2 != 0) {
                	salaCine[i][j] = 0;
                }
                
                //System.out.println(salaCine[i][j]+" ");
			}
			//System.out.println("\n");
		}
    }

	// SE PUEDE CONSIDERAR CADA ASIENTO COMO UN PRODUCTO,
	// ASÍ SE DESCUENTA DEL STOCK DEL CINE Y AGREGA AL STOCK DEL CLIENTE
	/**
	 * //SUBPROGRAMA LLENADO MATRIZ INTEGER (para aumentar la cantidad de productos
	 * en una posición) public static void matrixFill(int [][] matrix, int rowIndex,
	 * int columnIndex, String operation){
	 * 
	 * if (operation == "suma"){ matrix[rowIndex][columnIndex] += 1; } else if
	 * (operation == "resta"){ matrix[rowIndex][columnIndex] -= 1; } }
	 */
}
