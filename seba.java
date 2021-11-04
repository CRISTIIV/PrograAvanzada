import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class seba {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		String[] l_nombrePersonas = new String [500];
		String[] l_apellidos = new String [500];
		String[] l_ruts = new String [500];
		String[] l_passwords = new String [500];
		int [] l_saldos = new int [500];
		String[] l_estados = new String[500];
		
		String[] l_nombrePeliculas = new String[500];
		String[] l_tipo = new String[500];
		int[] l_recaudacion = new int[500];
		//String[] funciones = new String[500];
		
		int[][] sala1M = new int [10][30];
		int[][] sala1T = new int [10][30];
		int[][] sala2M = new int [10][30];
		int[][] sala2T = new int [10][30];
		int[][] sala3M = new int [10][30];
		int[][] sala3T = new int [10][30];
			
		Boolean[][] carteleraM = new Boolean[500][3];
		Boolean[][] carteleraT = new Boolean[500][3];
		
		int cant_archClientes = LeerClientes(l_nombrePersonas, l_apellidos, l_ruts, l_passwords, l_saldos);
		LeerStatus(l_ruts, l_estados, cant_archClientes);
		int cant_archPeliculas = LeerPeliculas(l_nombrePeliculas, l_tipo, l_recaudacion);
		LeerCarteleras(carteleraM, carteleraT, l_nombrePeliculas, cant_archPeliculas);
		
		llenadoMatrizBase(sala1M);
		llenadoMatrizBase(sala1T);
		llenadoMatrizBase(sala2M);
		llenadoMatrizBase(sala2T);
		llenadoMatrizBase(sala3M);
		llenadoMatrizBase(sala3T);
		
		IniciarSesion(l_nombrePersonas,l_apellidos,l_ruts,l_passwords,l_saldos, l_estados,l_nombrePeliculas,l_tipo, l_recaudacion, carteleraM,carteleraT,sala1M,sala1T,sala2M,sala2T,sala3M,sala3T,cant_archClientes,cant_archPeliculas,sc);
		
		sc.close();
					
	}
	
	public static int LeerClientes(String[] l_nombrePersonas, String[] l_apellidos, String[] l_ruts, String[] l_passwords, int[] l_saldos) throws IOException {
		Scanner arch = new Scanner (new File("Clientes.txt"));
		int cant = 0;
		while(arch.hasNextLine()) {
			String linea = arch.nextLine();
			String [] datos = linea.split(",");
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
	
	public static void LeerStatus(String[] l_ruts, String[] l_estados, int cant_archClientes) throws IOException {
		Scanner arch = new Scanner(new File("Status.txt"));
		while (arch.hasNextLine()) {
			String linea = arch.nextLine();
			String[] datos = linea.split(",");
			String rut = datos[0];
			int auxIndex = buscarIndexString(l_ruts, rut, cant_archClientes);
		
			if (auxIndex!=-1) {
				l_estados[auxIndex] = datos[1];
			}	
		}	
	}
	
	public static int LeerPeliculas(String[] l_nombrePeliculas, String[] l_tipo, int[] l_recaudacion) throws IOException {
		Scanner arch = new Scanner (new File("Peliculas.txt"));
		int cant = 0;
		while(arch.hasNextLine()) {
			String linea = arch.nextLine();
			String [] datos = linea.split(",");
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
	
	public static void LeerCarteleras(Boolean[][] carteleraM, Boolean[][] carteleraT, String[] peliculas, int cant) throws IOException {
		Scanner arch = new Scanner (new File("Peliculas.txt"));
		
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
		for (int i = 0; i<= cant; i++) {
			if (array[i].equals(key)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static int buscarIndexInt(int[] array, int key, int cant) {
		for (int i = 0; i<= cant; i++) {
			if (array[i] == key) {
				return i;
			}
		}
		return -1;
	}

	public static void IniciarSesion(String[] l_nombrePersonas, String[] l_apellidos, String[] l_ruts, String[] l_passwords, int[] l_saldos, String[] l_estados, String[] l_peliculas,String[]l_tipos,int[] l_recaudacion, Boolean[][] carteleraM, Boolean[][] carteleraT,int[][] Sala1M,int[][] Sala1T,int[][] Sala2M,int[][] Sala2T,int[][] Sala3M,int[][] Sala3T, int cant, int cantPeliculas, Scanner sc) {
		System.out.println("Bienvenido a nuestra boleteria online!");
		boolean flag = true;
		boolean cierre;
		
		do {
			System.out.println("\nSi desea iniciar sesion, presione 1. Si desea registrarse, presione 2");
			int opcion = Integer.parseInt(sc.nextLine());
			
			switch (opcion) {
			case 1:
				System.out.print("Ingrese su rut: ");
				String rut = sc.nextLine();
				String password;
				
				if(rut.equals("ADMIN")) {
					System.out.print("Ingrese su contraseña: ");
					password = sc.nextLine();
					if (password.equals("ADMIN")) {
						Admin(l_nombrePersonas,l_apellidos,l_ruts,l_saldos,cant,sc);				
					}
					
					else {
						System.out.println("Parece que te equivocaste de contraseña. ADMIN.");
						cierre = CierreSistema(sc);
						if (cierre == true) {
							flag = false;
						}						
					}	
				}
					
				else {
					
					int auxRut = VerificarRut(l_ruts, rut, cant);					
					if (auxRut != -1) {
						System.out.print("Ingrese su contraseña: ");
						password = sc.nextLine();
						
						int auxPassword = buscarIndexString(l_passwords, password, cant);					
						if (auxPassword != -1) {
							Cliente(l_nombrePersonas,l_apellidos,l_ruts,l_saldos,l_peliculas,l_estados, l_tipos, l_recaudacion, carteleraM,carteleraT,Sala1M,Sala1T,Sala2M,Sala2T,Sala3M,Sala3T,auxRut,cantPeliculas,sc);						
						}
						
						else {
							System.out.println("Contraseña equivocada.");
							cierre = CierreSistema(sc);
							if (cierre == true) {
								flag = false;
							}
						}					
					}
				
					else {
						System.out.println("Este rut no se encuentra registrado o esta erroneo.");
						cierre = CierreSistema(sc);
						if (cierre == true) {
							flag = false;
						}
					}
				}
				
				break;
								
			case 2:
				registro(l_nombrePersonas, l_apellidos, l_ruts, l_passwords, l_saldos, l_estados, cant, sc);
				break;
				
			default:
				System.out.println("Ingrese una opcion valida.");
				cierre = CierreSistema(sc);
				if (cierre == true) {
					flag = false;
				}
			}
			
		} while (flag);	
	}
	
	public static int VerificarRut(String[] l_ruts, String rut, int cant){
	       
    	int cantLetras = rut.length();
        int auxIndex;
        
        if (cantLetras == 12) {
            // con puntos y con guion.
            if (rut.endsWith("K") || rut.endsWith("k")) {            	
            	String rutK = rut.substring(0,11);            	
                auxIndex = buscarIndexString(l_ruts,rut,cant); 
                if (auxIndex == -1) {
                    rutK = rutK.concat("K"); 
                    auxIndex = buscarIndexString(l_ruts,rutK,cant);
                    return auxIndex;
                }
                
                else {
                	return auxIndex;
                }
            }
            
            else {           
            	auxIndex = buscarIndexString(l_ruts,rut,cant);
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
            	String rutK = rut.substring(0,10);
                auxIndex = buscarIndexString(l_ruts,rut,cant);
                if (auxIndex ==-1){
                    rutK = rutK.concat("K");
                    auxIndex = buscarIndexString(l_ruts,rutK,cant);
                    return auxIndex;
                } 
                
                else {
                    return auxIndex;
                }
            }
            
            else {
            	auxIndex = buscarIndexString(l_ruts,rut,cant);
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
            String primeraParte = rut.substring(0,2);
            String segundaParte = rut.substring(2,5);
            String terceraParte = rut.substring(5,8);
            String digitoVerificador = rut.substring(8,10);            
            String rutNuevo = primeraParte.concat("." + segundaParte + "." + terceraParte + digitoVerificador);

            if (rutNuevo.endsWith("K") || rutNuevo.endsWith("k")){
            	String rutK = rutNuevo.substring(0,11);
            	
                auxIndex = buscarIndexString(l_ruts,rutNuevo,cant);                
                if (auxIndex == -1) {
                    rutK = rutK.concat("K");
                    auxIndex = buscarIndexString(l_ruts,rutK,cant);
                    return auxIndex;    
                } 
                
                else {
                    return auxIndex;
                }    
            }
            
            else {            
            	auxIndex = buscarIndexString(l_ruts,rutNuevo,cant);
            	if (auxIndex != -1) {
            		return auxIndex;
            	}
            	
            	else {
            		return auxIndex;
            	}
            }
        }
        
        if (cantLetras == 9) {
        	
            if (rut.substring(0,8).endsWith("-")) { // sin puntos y con guion  (1 digito antes del primer punto)
             
                String primeraParte = rut.substring(0,1);
                String segundaParte = rut.substring(1,4);
                String terceraParte = rut.substring(4,7);
                String digitoVerificador = rut.substring(7,9);   
                String rutUnDigito = primeraParte.concat("." + segundaParte + "." + terceraParte + digitoVerificador);
                
                if (rutUnDigito.endsWith("K") || rutUnDigito.endsWith("k")){
                	String rutK = rutUnDigito.substring(0,10);
                	
                    auxIndex = buscarIndexString(l_ruts,rutUnDigito,cant);                      
                    if (auxIndex == -1) { 
                        rutK = rutK.concat("K");
                        auxIndex = buscarIndexString(l_ruts,rutK,cant);
                        return auxIndex;
                    } 
                    
                    else {
                        return auxIndex;
                    }                    
                }
                
                else {                
                	auxIndex = buscarIndexString(l_ruts,rutUnDigito,cant);
                	if (auxIndex != -1) {
                		return auxIndex;
                	}
                
                	else {
                		return auxIndex;
                	}
                }  
            } 
            
            else { // sin puntos y sin guion (2 digitos antes del primer punto)
                String primeraParte = rut.substring(0,2);
                String segundaParte = rut.substring(2,5);
                String terceraParte = rut.substring(5,8);
                String digitoVerificador = rut.substring(8,9);
                String rutDosDigitos = primeraParte.concat("." + segundaParte + "." + terceraParte+ "-" + digitoVerificador);
                
                if (rutDosDigitos.endsWith("K") || rutDosDigitos.endsWith("k")) {
                	String rutK = rutDosDigitos.substring(0, 11);
              	
                    auxIndex = buscarIndexString(l_ruts,rutDosDigitos,cant);
                    if (auxIndex == -1){ 
                        rutK = rutK.concat("K");
                        auxIndex = buscarIndexString(l_ruts,rutK,cant);
                        return auxIndex;
                    } 
                    
                    else {
                        return auxIndex;
                    }                 
                }
                
                else {
                
                	auxIndex = buscarIndexString(l_ruts,rutDosDigitos,cant);
                	if (auxIndex != -1) {
                		return auxIndex;
                	}
                
                	else {
                		return auxIndex;
                	}
                }
            }
        }

        if (cantLetras == 8){
            // sin puntos y sin guion (1 digito antes del primer punto)
            String primeraParte = rut.substring(0, 1);
            String segundaParte = rut.substring(1, 4);
            String terceraParte = rut.substring(4, 7);
            String digitoVerificador = rut.substring(7, 8);
            String rutNuevo = primeraParte.concat("." + segundaParte + "." + terceraParte+"-"+digitoVerificador);
            
            if (rutNuevo.endsWith("K") || rutNuevo.endsWith("k")){
            	String rutK = rutNuevo.substring(0, 10);
            	
                auxIndex = buscarIndexString(l_ruts,rutNuevo,cant);                                
                if (auxIndex ==-1){ 
                    rutK = rutK.concat("K");
                    auxIndex = buscarIndexString(l_ruts,rutK,cant);
                    return auxIndex;
                } 
                
                else {
                    return auxIndex;
                }
            }
            
            else {            
            	auxIndex = buscarIndexString(l_ruts,rutNuevo,cant);
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
	
	public static Boolean CierreSistema(Scanner sc) {
    	
    	System.out.println("\nDesea cerrar el sistema? 1 para cerrar el sistema, cualquier otra tecla para intentarlo nuevamente.");
        String cierre = sc.nextLine();
        
        if(cierre.equals("1")){
            return true;
        } 
        
        else {
            return false;
        }
    }

	public static void registro(String[] nombres, String[] apellidos, String[] ruts, String[] passwords, int[] saldos, String[] l_estados, int cant_clientes , Scanner sc) {
        System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();
        StringLlenarArray(nombres, nombre, cant_clientes);

        System.out.println("Ingrese su apellido: ");
        String apellido = sc.nextLine();
        StringLlenarArray(apellidos, apellido, cant_clientes);

        System.out.println("Ingrese su rut (con puntos y guion): ");
        String Id = sc.nextLine();
        StringLlenarArray(ruts, Id, cant_clientes);

        System.out.println("Ingrese su contraseña: ");
        String password = sc.nextLine();
        StringLlenarArray(passwords, password, cant_clientes);

        System.out.println("Ingrese su saldo: ");
        int saldo = Integer.parseInt(sc.nextLine());
        IntLlenarArray(saldos, saldo, cant_clientes);
        
        System.out.println("Ingrese su estado del pase: ");
        String estado = sc.nextLine();
        StringLlenarArray(l_estados, estado, cant_clientes);
        System.out.println("Registrado correctamente");     
    }
	
	public static void StringLlenarArray(String[] array, String variable, int cant) {
		
		int i = 0;
		
		while (i <= cant-1) {
			i++;
		}
		
		for (int j = cant ; j >= i + 1 ; j--) { //Corrimiento
			array[j] = array[j-1];
		}	
	
		array[i] = variable;
	}
	
	public static void IntLlenarArray(int[] array, int variable, int cant) {
	
		int i = 0;
		
		while (i <= cant-1) {
			i++;
		}
		
		for (int j = cant ; j >= i + 1 ; j--) { //Corrimiento
			array[j] = array[j-1];
		}	
	
		array[i] = variable;
	}

	public static void Cliente(String[]nombres,String[]apellidos,String[]ruts,int[]saldos,String[]estados,String[]l_peliculas,String[]tipo,int[]recaudacion,Boolean[][]carteleraM,Boolean[][]carteleraT,int[][]Sala1M,int[][] Sala1T,int[][] Sala2M,int[][] Sala2T,int[][] Sala3M,int[][] Sala3T, int auxRut, int cantPeliculas, Scanner sc) {
			
		while(true){
			
    		System.out.println("\nBienvenid@ "+nombres[auxRut] + ": Que opcion desea realizar?");
    		System.out.println("a) Comprar entradas");
    		System.out.println("b) Mi informacion");
    		System.out.println("c) Devolucion de entrada");
    		System.out.println("d) Ver cartelera");
    		String opcion = sc.nextLine();
    				
    		switch(opcion){
    			case "a":   
    				System.out.print("Ingrese el nombre de la pelicula que quiere ver: ");
    		        String pelicula = sc.nextLine();
    		        int auxPelicula = buscarIndexString(l_peliculas, pelicula, cantPeliculas);
    		        
    		        if (auxPelicula != -1) {
    		        	System.out.println("\nSalas disponibles segun el horario de la mañana: ");
    		        	
    		        	for (int j = 0 ; j <= 2 ; j++) {
    		        		
    		        		if (carteleraM[auxPelicula][j]) {
    		        			System.out.println(j+1);
    		        		} 
    		        	}
    		        	
    		        	System.out.println("Salas disponibles segun el horario de la tarde: ");
    		        	
    		        	for (int j = 0 ; j <= 2 ; j++) {
    		        		
    		        		if (carteleraT[auxPelicula][j]) {
    		        			System.out.println(j+1);
    		        		} 
    		        	}
    		        	
    		        	System.out.print("Ingresa horario que deseas (M / T): ");
    		        	String horario = sc.nextLine().toUpperCase();
    		        	char[] ABC = {'A','B','C','D','E','F','G','H','I','J'};
    		        	int cantBoletos = 0;
    		        	String asiento;
    		        	int numeroFila = 0;
    		        	int numeroColumna = 0;
    		        	
    		        	if (horario.equals("M")) {
    		        		System.out.print("Ingrese sala deseada: ");
    		        		int sala = Integer.parseInt(sc.nextLine());
    		        		
    		        		if (carteleraM[auxPelicula][sala-1]) {
    		        			
    		        			System.out.println("Desplegando sala...");    		        			
    		        			System.out.println("\nLos asientos no disponibles se les asigno un numero 0.");
    		        			System.out.println("A los asientos disponibles se les asigno un numero 1.");
    		        			System.out.println("Y a los asientos comprados se les asigno un numero 2.");
      		        			
    		        			switch (sala) {
    		        			
								case 1:									
									for (int i = 0 ; i < ABC.length ; i++) {
										System.out.print("\n [" + ABC[i] + "] ");									
										for (int j = 0 ; j < 30 ; j++) {
											System.out.print(Sala1M[i][j] + " ");												
										}
									}
									
									System.out.print("\n\nCuantos boletos compraras?: ");
									cantBoletos = Integer.parseInt(sc.nextLine());
																			
									for (int i = 0 ; i < cantBoletos ; i++) {
										System.out.print("Ingresa el asiento deseado (Ej: A23): ");
										asiento = sc.nextLine().toUpperCase();
										numeroFila = AsientoFilaInt(asiento);
										numeroColumna = AsientoColumnaInt(asiento);
										
										while (numeroFila != -1 && numeroColumna != -1) {
											
											if(Sala1M[numeroFila][numeroColumna] == 1) {											
												Sala1M[numeroFila][numeroColumna] = 2;
												break;
											}
											
											else {
												System.out.println("Este asiento esta ocupado o no disponible. Intente nuevamente\n");
											
												System.out.print("Ingresa el asiento deseado (Ej: A23): ");
												asiento = sc.nextLine().toUpperCase();
												numeroFila = AsientoFilaInt(asiento);
												numeroColumna = AsientoColumnaInt(asiento);											
											}
										}
										
										if (numeroFila == -1 || numeroColumna == -1) {
											System.out.print("El asiento esta mal ingresado.");
										}									
									}
									
									ComprarEntradas(Sala1M,saldos,estados,tipo,recaudacion,cantBoletos,auxRut,auxPelicula,sc);																								
									break;
								
								case 2:    									
									for (int i = 0 ; i < ABC.length ; i++) {
										System.out.print("\n [" + ABC[i] + "] ");									
										for (int j = 0 ; j < 30 ; j++) {
											System.out.print(Sala2M[i][j] + " ");												
										}
									}
										
									System.out.print("\n\nCuantos boletos compraras?: ");
									cantBoletos = Integer.parseInt(sc.nextLine());
																			
									for (int i = 0 ; i < cantBoletos ; i++) {
										System.out.print("Ingresa el asiento deseado (Ej: A23): ");
										asiento = sc.nextLine().toUpperCase();
										numeroFila = AsientoFilaInt(asiento);
										numeroColumna = AsientoColumnaInt(asiento);
										
										while (numeroFila != -1 && numeroColumna != -1) {
											
											if(Sala2M[numeroFila][numeroColumna] == 1) {											
												Sala2M[numeroFila][numeroColumna] = 2;
												break;
											}
											
											else {
												System.out.println("Este asiento esta ocupado o no disponible. Intente nuevamente\n");
											
												System.out.print("Ingresa el asiento deseado (Ej: A23): ");
												asiento = sc.nextLine().toUpperCase();
												numeroFila = AsientoFilaInt(asiento);
												numeroColumna = AsientoColumnaInt(asiento);											
											}
										}
										
										if (numeroFila == -1 || numeroColumna == -1) {
											System.out.print("El asiento esta mal ingresado.");
										}									
									}
									ComprarEntradas(Sala2M,saldos,estados,tipo,recaudacion,cantBoletos,auxRut,auxPelicula,sc);																			
									break;
									
								case 3:									
									for (int i = 0 ; i < ABC.length ; i++) {
										System.out.print("\n [" + ABC[i] + "] ");									
										for (int j = 0 ; j < 30 ; j++) {
											System.out.print(Sala3M[i][j] + " ");												
										}
									}	
									
									System.out.print("\n\nCuantos boletos compraras?: ");
									cantBoletos = Integer.parseInt(sc.nextLine());
																			
									for (int i = 0 ; i < cantBoletos ; i++) {
										System.out.print("Ingresa el asiento deseado (Ej: A23): ");
										asiento = sc.nextLine().toUpperCase();
										numeroFila = AsientoFilaInt(asiento);
										numeroColumna = AsientoColumnaInt(asiento);
										
										while (numeroFila != -1 && numeroColumna != -1) {
											
											if(Sala3M[numeroFila][numeroColumna] == 1) {											
												Sala3M[numeroFila][numeroColumna] = 2;
												break;
											}
											
											else {
												System.out.println("Este asiento esta ocupado o no disponible. Intente nuevamente\n");
											
												System.out.print("Ingresa el asiento deseado (Ej: A23): ");
												asiento = sc.nextLine().toUpperCase();
												numeroFila = AsientoFilaInt(asiento);
												numeroColumna = AsientoColumnaInt(asiento);											
											}
										}
										
										if (numeroFila == -1 || numeroColumna == -1) {
											System.out.print("El asiento esta mal ingresado.");
										}									
									}
									ComprarEntradas(Sala3M,saldos,estados,tipo,recaudacion,cantBoletos,auxRut,auxPelicula,sc);	
									break;
								}
    		        		}
    		        		
    		        		else {
    		        			System.out.println("Esta sala no se encuentra disponible en este horario.");
    		        		}   		        		   		        	
    		        	}
    		        	
    		        	
    		        	
    		        	if (horario.equals("T")) {
    		        		System.out.print("Ingrese sala deseada: ");
    		        		int sala = Integer.parseInt(sc.nextLine());
    		        		
    		        		if (carteleraT[auxPelicula][sala-1]) {
    		        			
    		        			switch (sala) {
								case 1:
									for (int i = 0 ; i < ABC.length ; i++) {
										System.out.print("\n [" + ABC[i] + "] ");									
										for (int j = 0 ; j < 30 ; j++) {
											System.out.print(Sala1T[i][j] + " ");												
										}
									}
									
									System.out.print("\n\nCuantos boletos compraras?: ");
									cantBoletos = Integer.parseInt(sc.nextLine());
																			
									for (int i = 0 ; i < cantBoletos ; i++) {
										System.out.print("Ingresa el asiento deseado (Ej: A23): ");
										asiento = sc.nextLine().toUpperCase();
										numeroFila = AsientoFilaInt(asiento);
										numeroColumna = AsientoColumnaInt(asiento);
										
										while (numeroFila != -1 && numeroColumna != -1) {
											
											if(Sala1T[numeroFila][numeroColumna] == 1) {											
												Sala1T[numeroFila][numeroColumna] = 2;
												break;
											}
											
											else {
												System.out.println("Este asiento esta ocupado o no disponible. Intente nuevamente\n");
											
												System.out.print("Ingresa el asiento deseado (Ej: A23): ");
												asiento = sc.nextLine().toUpperCase();
												numeroFila = AsientoFilaInt(asiento);
												numeroColumna = AsientoColumnaInt(asiento);											
											}
										}
										
										if (numeroFila == -1 || numeroColumna == -1) {
											System.out.print("El asiento esta mal ingresado.");
										}									
									}
									ComprarEntradas(Sala1T,saldos,estados,tipo,recaudacion,cantBoletos,auxRut,auxPelicula,sc);	
									break;
								
								case 2:
									for (int i = 0 ; i < ABC.length ; i++) {
										System.out.print("\n [" + ABC[i] + "] ");									
										for (int j = 0 ; j < 30 ; j++) {
											System.out.print(Sala2T[i][j] + " ");												
										}
									}
									
									System.out.print("\n\nCuantos boletos compraras?: ");
									cantBoletos = Integer.parseInt(sc.nextLine());
																			
									for (int i = 0 ; i < cantBoletos ; i++) {
										System.out.print("Ingresa el asiento deseado (Ej: A23): ");
										asiento = sc.nextLine().toUpperCase();
										numeroFila = AsientoFilaInt(asiento);
										numeroColumna = AsientoColumnaInt(asiento);
										
										while (numeroFila != -1 && numeroColumna != -1) {
											
											if(Sala2T[numeroFila][numeroColumna] == 1) {											
												Sala2T[numeroFila][numeroColumna] = 2;
												break;
											}
											
											else {
												System.out.println("Este asiento esta ocupado o no disponible. Intente nuevamente\n");
											
												System.out.print("Ingresa el asiento deseado (Ej: A23): ");
												asiento = sc.nextLine().toUpperCase();
												numeroFila = AsientoFilaInt(asiento);
												numeroColumna = AsientoColumnaInt(asiento);											
											}
										}
										
										if (numeroFila == -1 || numeroColumna == -1) {
											System.out.print("El asiento esta mal ingresado.");
										}									
									}
									ComprarEntradas(Sala2T,saldos,estados,tipo,recaudacion,cantBoletos,auxRut,auxPelicula,sc);	
									break;
									
								case 3:
									for (int i = 0 ; i < ABC.length ; i++) {
										System.out.print("\n [" + ABC[i] + "] ");									
										for (int j = 0 ; j < 30 ; j++) {
											System.out.print(Sala3T[i][j] + " ");												
										}
									}
									
									System.out.print("\n\nCuantos boletos compraras?: ");
									cantBoletos = Integer.parseInt(sc.nextLine());
																			
									for (int i = 0 ; i < cantBoletos ; i++) {
										System.out.print("Ingresa el asiento deseado (Ej: A23): ");
										asiento = sc.nextLine().toUpperCase();
										numeroFila = AsientoFilaInt(asiento);
										numeroColumna = AsientoColumnaInt(asiento);
										
										while (numeroFila != -1 && numeroColumna != -1) {
											
											if(Sala2T[numeroFila][numeroColumna] == 1) {											
												Sala2T[numeroFila][numeroColumna] = 2;
												break;
											}
											
											else {
												System.out.println("Este asiento esta ocupado o no disponible. Intente nuevamente\n");
											
												System.out.print("Ingresa el asiento deseado (Ej: A23): ");
												asiento = sc.nextLine().toUpperCase();
												numeroFila = AsientoFilaInt(asiento);
												numeroColumna = AsientoColumnaInt(asiento);											
											}
										}
										
										if (numeroFila == -1 || numeroColumna == -1) {
											System.out.print("El asiento esta mal ingresado.");
										}									
									}
									ComprarEntradas(Sala3T,saldos,estados,tipo,recaudacion,cantBoletos,auxRut,auxPelicula,sc);	
									break;

								}
    		        		}
    		        		
    		        		else {
    		        			System.out.println("Esta sala no se encuentra disponible en este horario.");
    		        		}
    		        	}
    		        	   		        	   		         		        	        
    		         }
    		        
    		        else {
    		        	System.out.println("La pelicula ingresada no existe o esta mal escrita.");
					}
    				                  
                    break;

    			case "b":
    				
    				System.out.println("\nUsted ha seleccionado <Ver informacion de usuario>,\na continuacion se desplegará la información perteneciente al usuario en uso");
                    System.out.println("Su nombre es: "+nombres[auxRut]+" "+apellidos[auxRut]+", de rut: "+ruts[auxRut]+"\nCon saldo: $"+saldos[auxRut]+"\nY sus entradas compradas son: ");
                    break;
                    
    				//AumentarSaldo(auxRut, saldos);
                    //System.out.println("\nAumento de saldo realizado con exito");
    				//break;
    					
    			case "c":
                    
                    
    			case "d":
    				break;
    					
    			default:
    				System.out.println("\nOpcion no valida");
    		}
    		
    		System.out.println("\nDesea cerrar sesion?\n<si> para salir.\n<cualquier otra cosa> para continuar.");
    		String salir = sc.nextLine();
    				
    		if (salir.equals("si")){
    			break;
    		}
    	
		}
	}
	
	public static int AsientoFilaInt(String asiento) {
		String filaAsiento = asiento.substring(0,1);
		int numeroFila;
		
		switch (filaAsiento) {
		case "A":
			numeroFila = 0;
			return numeroFila;
			
		case "B":
			numeroFila = 1;
			return numeroFila;
			
		case "C":
			numeroFila = 2;
			return numeroFila;
			
		case "D":
			numeroFila = 3;
			return numeroFila;
			
		case "E":
			numeroFila = 4;
			return numeroFila;
			
		case "F":
			numeroFila = 5;
			return numeroFila;
			
		case "G":
			numeroFila = 6;
			return numeroFila;
			
		case "H":
			numeroFila = 7;
			return numeroFila;
			
		case "I":
			numeroFila = 8;
			return numeroFila;
			
		case "J":
			numeroFila = 9;
			return numeroFila;
		}
		
		return -1;		
	}
		
	public static void Admin(String[] nombres, String[] apellidos, String[] ruts, int[] saldos, int cant, Scanner sc) {
		
		while(true){     
			
			System.out.println("\nADMIN: Que opcion desea realizar?");
			System.out.println("a) revisar taquilla");
			System.out.println("b) informacion de clientes\n");
			String opcion = sc.nextLine().toLowerCase();
			
			switch(opcion){			
			case "a":   
				System.out.println("\nLa taquilla es: \n");
				//MostrarTaquilla();
				break;
				
			case "b":				
				System.out.println("\nIngrese el rut del cliente que quiere consultar informacion: ");
                String rut = sc.nextLine();
                
                int auxRut = VerificarRut(ruts, rut, cant);
                
                if (auxRut != -1) {
                	System.out.println("\nINFORMACION DEL CLIENTE: ");
                	System.out.println("El nombre del cliente es: " + nombres[auxRut] + " " + apellidos[auxRut]);
                	System.out.println("El rut del cliente es: " + ruts[auxRut]);
                	System.out.println("El saldo del cliente es: " + saldos[auxRut]);
                	System.out.println("Sus entradas son: ");
                }
               
                break;
                	
			default:
				System.out.println("\nOpcion no valida");
			}
			
			System.out.println("\nDesea cerrar sesion?\n<si> para salir.\n<cualquier otra cosa> para continuar.");
			String salir = sc.nextLine();
			
			if (salir.equals("si")){
				break;
			}
			
		}
		
	}
	
	public static void AumentarSaldo(int peopleIndex, int[]balances) {
        Scanner sc = new Scanner (System.in);
        System.out.println("\nIngrese el saldo que quiere agregar: ");
        int saldo = Integer.parseInt(sc.nextLine());

        balances[peopleIndex] += saldo;
        sc.close();
    }

	public static int AsientoColumnaInt(String asiento) {
		int numeroColumna;
		int largo = asiento.length();
		
		if (largo == 2) {
			numeroColumna = Integer.parseInt(asiento.substring(1,2));
			return numeroColumna-1;
		}
		
		if (largo == 3) {
			numeroColumna = Integer.parseInt(asiento.substring(1,3));
			
			if (numeroColumna > 30) {
				return -1;
			}
			
			return numeroColumna-1;
		}
		
		return -1;
	}
	
	public static void llenadoMatrizBase(int[][] salaCine){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 30; j++){
				salaCine[i][j] = 1;
                
                salaCine[0][0] = 0;salaCine[0][1] = 0;salaCine[0][2] = 0;salaCine[0][3] = 0;salaCine[0][4] = 0;salaCine[0][29] = 0;salaCine[0][28] = 0;salaCine[0][27] = 0;salaCine[0][26] = 0;salaCine[0][25] = 0;
                salaCine[1][0] = 0;salaCine[1][1] = 0;salaCine[1][2] = 0;salaCine[1][3] = 0;salaCine[1][4] = 0;salaCine[1][29] = 0;salaCine[1][28] = 0;salaCine[1][27] = 0;salaCine[1][26] = 0;salaCine[1][25] = 0;
                salaCine[2][0] = 0;salaCine[2][1] = 0;salaCine[2][2] = 0;salaCine[2][3] = 0;salaCine[2][4] = 0;salaCine[2][29] = 0;salaCine[2][28] = 0;salaCine[2][27] = 0;salaCine[2][26] = 0;salaCine[2][25] = 0;
                salaCine[3][0] = 0;salaCine[3][1] = 0;salaCine[3][2] = 0;salaCine[3][3] = 0;salaCine[3][4] = 0;salaCine[3][29] = 0;salaCine[3][28] = 0;salaCine[3][27] = 0;salaCine[3][26] = 0;salaCine[3][25] = 0;
               
                //LOS ASIENTOS CON "1" SON LOS DISPONIBLES O UTILIZABLES, LOS "0" SON LOS INCAPACITADOS
                
                if (salaCine[i][j] == 1 && i % 2 == 0 && j % 2 == 0) {
                	salaCine[i][j] = 0;
                }
                if (salaCine[i][j] == 1 && i % 2 != 0 && j % 2 != 0) {
                	salaCine[i][j] = 0;
                }                             
			}			
		}
    }
	
	public static void ComprarEntradas(int[][]matriz,int[]saldos,String[]estados,String[]tipo,int[]recaudacion,int cantBoletos,int auxRut, int auxPelicula, Scanner sc) {
		System.out.println("\nProcederemos con su compra.");
		double precio = 0;
		double precioFinal = 0;
		
		if (tipo[auxPelicula].equals("estreno")) {
			precio = 5500;
		}
		
		else {
			precio = 4000;
		}
		
		if(estados[auxRut].equals("HABILITADO")) {
			System.out.println("Posees descuento por pase de movilidad.");
			precioFinal = precio - precio*0.15;
			precioFinal *= cantBoletos;
		}
		
		else {
			System.out.println("No posees descuento por pase de movilidad.");
			precioFinal = precio;
			precioFinal *= cantBoletos;
		}
		
		System.out.println("El total a pagar es de: $" + precioFinal);
		System.out.print("Desea continuar con su compra? (si/no): ");
		String confirmarCompra = sc.nextLine().toLowerCase();
		
		while (!confirmarCompra.equals("si") && !confirmarCompra.equals("no")) {
			System.out.println("Ingresa una opcion valida.");			
			System.out.print("\nDesea continuar con su compra? (si/no): ");
			confirmarCompra = sc.nextLine().toLowerCase();
		}
		
		if (confirmarCompra.equals("si")) {
			
			if(saldos[auxRut]>=precioFinal) {				
				saldos[auxRut] -= precioFinal;
				recaudacion[auxPelicula] += precioFinal;			
			}
			
			else {
				System.out.println("Saldo insuficiente.");
				System.out.print("\nDesea agregar saldo? (si/no): ");
				String recargarSaldo = sc.nextLine().toLowerCase(); 
				
				while (!recargarSaldo.equals("si") && !recargarSaldo.equals("no")) {
					System.out.println("Ingresa una opcion valida.");			
					System.out.print("Dese agregar saldo? (si/no)");
					recargarSaldo = sc.nextLine().toLowerCase();
				}
				
				if (recargarSaldo.equals("si")) {
					System.out.print("Ingrese el monto que desea agregar: $");
					int saldo = Integer.parseInt(sc.nextLine());
					saldos[auxRut] += saldo;
				}
				
				else {
					llenadoMatrizBase(matriz);
				}
			}		
		}	
		
		else {
			llenadoMatrizBase(matriz);
		}
	}
	
}