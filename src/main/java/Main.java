import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import controller.*;
import database.ConnectionFactory;
import model.*;
import view.Menu;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Esta clase es la principal donde inicializas el programa y muestra un menu
 */
public class Main {

    /**
     * Este metodo sirve para crear el Manager de Entity que esta Anotado en las clase.
     * @return
     */
    public static EntityManagerFactory createEntityManagerFactory() {
        EntityManagerFactory emf;
        try {
            emf = Persistence.createEntityManagerFactory("JPAMagazines");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return emf;
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection c = connectionFactory.connect();

        EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

        EscuderiaController escuderiaController = new EscuderiaController(c, entityManagerFactory);
        PilotoController pilotoController = new PilotoController(c, entityManagerFactory);


        Menu menu = new Menu();
        int opcio = menu.mainMenu();

        while (opcio > 0 && opcio < 14) {
            switch (opcio) {
                case 1:
                    List<Escuderia> escuderias = escuderiaController.readEscuderiaFile("src/main/resources/f1.csv");
                    for (Escuderia r : escuderias) {
                        try {
                            escuderiaController.addEscuderia(r);
                        } catch (Exception e) {
                        }
                    }

                    List<Piloto> pilotos = pilotoController.readPilotoFile("src/main/resources/f1.csv");
                    for (Piloto piloto : pilotos) {
                        pilotoController.addPiloto(piloto);
                    }
                    break;
                case 2:
                    pilotoController.showPilotoPorEscuderia();
                    break;
                case 3:
                    pilotoController.showPilotoCon();
                    break;
                case 4:
                    pilotoController.showPilotoPor();
                    break;
                case 5:
                    pilotoController.modificarPiloto();
                    break;
                case 6:
                    escuderiaController.modificarEscuderia();
                    break;
                case 7:
                    pilotoController.borrarPiloto();
                    break;
                case 8:
                    pilotoController.borrarPilotoPorEscuderia();
                    break;
                case 9:
                    System.out.println("----------------------");
                    System.out.println("Crear Escuderia");
                    System.out.println("----------------------");

                    System.out.println("Nombre:");
                    String escuderia = sc.nextLine().toUpperCase(Locale.ROOT);

                    escuderiaController.addEscuderia(new Escuderia(escuderia));

                    break;
                case 10:
                    System.out.println("----------------------");
                    System.out.println("Crear Piloto");
                    System.out.println("----------------------");

                    System.out.println("Numero: ");
                    String numero = sc.nextLine().toUpperCase(Locale.ROOT);

                    System.out.println("Nombre:");
                    String nom = sc.nextLine().toUpperCase(Locale.ROOT);

                    System.out.println("Elige una escuderia:");
                    String escuderia2 = menu.EscuderiaMenu(c, entityManagerFactory).toUpperCase(Locale.ROOT);

                    System.out.println("Elije un pais: ");
                    String pais = sc.nextLine().toUpperCase(Locale.ROOT);

                    System.out.println("Podiums: ");
                    String podiums = sc.nextLine();

                    System.out.println("Puntos totales: ");
                    String puntosTotales = sc.nextLine();

                    System.out.println("gpCompletados: ");
                    String gpCompletados = sc.nextLine();

                    System.out.println("Titulos mundiales: ");
                    String titulosMundiales = sc.nextLine();

                    System.out.println("Mejor posición: ");
                    String mejorPos = sc.nextLine();

                    System.out.println("Mejor clasificación: ");
                    String mejorClas = sc.nextLine();

                    System.out.println("Fecha de nacimiento: ");
                    String fechaNaci = sc.nextLine();

                    System.out.println("Nacionalidad: ");
                    String nacionalidad = sc.nextLine();

                    pilotoController.addPiloto(new Piloto(numero, nom, new Escuderia(escuderia2), pais,podiums,puntosTotales,gpCompletados,titulosMundiales,mejorPos,mejorClas,fechaNaci,nacionalidad));

                    break;
                case 11:
                    pilotoController.showPiloto();
                    break;
                case 12:
                    escuderiaController.showEscuderia();
                    break;
                case 13:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Introdueixi una de les opcions anteriors");
                    break;
            }
            opcio = menu.mainMenu();
        }
    }
}