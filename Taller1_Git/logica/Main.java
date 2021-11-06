package Taller1_Git.logica;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        SistemaRitoGames sistema = new SistemaRitoGamesImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de compra de Personajes y Skin para el videojuego <Juego Generico>");
        lecturaCuentas(sistema);
        lecturaPersonajes(sistema);
        lecturaEstadisticas(sistema);
    }
}
