import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class taller0 {
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
		
		int[][] sala1M = new int [30][10];
		int[][] sala1T = new int [30][10];
		int[][] sala2M = new int [30][10];
		int[][] sala2T = new int [30][10];
		int[][] sala3M = new int [30][10];
		int[][] sala3T = new int [30][10];
		
		int cant_archClientes = LeerClientes(l_nombrePersonas, l_apellidos, l_ruts, l_passwords, l_saldos);
		LeerStatus(l_ruts, l_estados, cant_archClientes);
		int cant_archPeliculas = LeerPeliculas(l_nombrePeliculas, l_tipo, l_recaudacion);
		IniciarSesion(l_nombrePersonas, l_apellidos, l_ruts, l_passwords, l_saldos, l_estados, cant_archClientes, sc);
		
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
			//String funciones = datos[3];
			l_nombrePeliculas[cant] = nombrePelicula;
			l_tipo[cant] = tipo;
			l_recaudacion[cant] = recaudacion;	
			cant++;
		}
		
		arch.close();
		return cant;
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

	public static void IniciarSesion(String[] l_nombrePersonas, String[] l_apellidos, String[] l_ruts, String[] l_passwords, int[] l_saldos, String[] l_estados, int cant, Scanner sc) {
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
						//MenuAdmin();				
						System.out.println("Admin.");
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
							//MenuCliente();
							System.out.println("Cliente.");
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

    public static void reWriteClients(String file, String[] names, String[] lastNames, String[] ruts, String[] passwords, int[] balances){

    }

    public static void reWriteMovies(String file, String[] titles, String[] tipe, int[] moneyRaised, String[] schedules) {
        
    }

    public static void matrixFill() {
        
    }

    public static void buyTickets(){ //int[] buyTickets() {
        
    }

	public static void returnTickets() {
		
	}

	public static void salesCalculator(){//para calcular las recaudaciones O VER PARA AGREGAR DIRECTAMENTE AL DESPLEGAR RECAUDACIONES

	}

	public static void increaseCash() {
		
	}

	public static void displayUserInformation(){

	}

	public static void displayMovies() {
		
	}

	public static void displayMoneyRaised(){

	}

	public static void displayClientInformation() {
		
	}

	public static void displaySeating() {
		
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
}
