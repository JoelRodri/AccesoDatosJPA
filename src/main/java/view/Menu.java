package view;

import controller.PilotoController;
import controller.EscuderiaController;

import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Esta clase sirve para mostrar menus
 */
public class Menu {
    private int option;
    private String opciones;

    /**
     * Este es el constructor
     */
    public Menu() {
        super();
    }

    /**
     * Este metodo sirve para mostrar un menu
     * @return devuelte la opcion que elegiste en numero
     */
    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {

            System.out.println(" \nMENU PRINCIPAL \n");

            System.out.println("1. Rellenar Tablas");
            System.out.println("2. Mostrar los que sean ?");
            System.out.println("3. Mostrar los pilotos que tengan un X");
            System.out.println("4. Mostrar todos los pilotos que empiezan por X");
            System.out.println("5. Modificar el nombre de un piloto");
            System.out.println("6. Modificar las escuderias de los pilotos que empiezan por X");
            System.out.println("7. Eliminar un piloto");
            System.out.println("8. Eliminar campeones con la escuderia X");
            System.out.println("9. Añadir una escuderia");
            System.out.println("10. Añadir un piloto");
            System.out.println("11. Mostrar pilotos");
            System.out.println("12. Mostrar escuderias");
            System.out.println("13. Exit");
            System.out.println("Esculli opció: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();

            }

        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7
                && option != 8 && option != 9 && option != 10 && option != 11 && option != 12 && option != 13);


        return option;
    }

    /**
     * Este metodo sirve para mostrar un menu de rol
     * @param c recibe la coneccion
     * @return devuelve el rol que elegiste
     */
    public String EscuderiaMenu(Connection c, EntityManagerFactory entityManagerFactory){
        EscuderiaController escuderiaController = new EscuderiaController(c, entityManagerFactory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(;;){
            escuderiaController.showEscuderia();
            System.out.println("Elige el rol: ");
            try {
                opciones = br.readLine();
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return opciones;
        }
    }
    /**
     * Este metodo sirve para mostrar un menu de numeros y nombre de pilotos
     * @param c recibe la coneccion
     * @return devuelve el nombre que elegiste
     */
    public String NomMenu(Connection c, EntityManagerFactory entityManagerFactory){
        PilotoController pilotoController = new PilotoController(c, entityManagerFactory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n" + "Pilotos: ");
        for(;;){
            pilotoController.showPilotosNom();
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return String.valueOf(option);
        }
    }
}