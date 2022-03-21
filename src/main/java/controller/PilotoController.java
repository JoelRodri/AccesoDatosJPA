package controller;

import model.Piloto;
import model.Escuderia;
import view.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

/**
 * Esta clase sirve para controlar la tabla piloto situada en mi base de datos
 */
public class PilotoController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    List<Piloto> pilotos;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     * @param entityManagerFactory recibe el entityManagerFactory
     */
    public PilotoController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        pilotos = new ArrayList<>();
    }

    /**
     * Este metodo sirve para leer el fichero, lo mete en una lista y lo devuelve
     * @param file rebie la ruta del fichero
     * @return devuelve una lista de Piloto
     * @throws IOException
     */
    public List<Piloto> readPilotoFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while((linea = br.readLine()) != null){
            List<String> listToken = getTokenList(linea, "\"");
            pilotos.add(new Piloto(listToken.get(0),listToken.get(2),new Escuderia(listToken.get(4)),listToken.get(6),listToken.get(8),listToken.get(10),listToken.get(12),listToken.get(14),listToken.get(16),listToken.get(18),listToken.get(20),listToken.get(22)));
        }

        return pilotos;
    }

    /**
     * Para añadir piloto
     * @param piloto recibe un piloto y lo añade
     */
    public void addPiloto(Piloto piloto) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(piloto);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar pilotos
     */
    public void showPiloto(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Piloto> result = em.createQuery("from Piloto", Piloto.class).getResultList();
        for (Piloto piloto : result) {
            System.out.println(piloto.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar pilotos por escuderias
     */
    public void showPilotoPorEscuderia(){
        String escuderia = menu.EscuderiaMenu(connection,entityManagerFactory).toUpperCase(Locale.ROOT);
        String sql = "from Piloto where escuderia='" + escuderia + "'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Piloto> result = em.createQuery(sql, Piloto.class).getResultList();
        for (Piloto piloto : result) {
            System.out.println(piloto.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar pilotos con un texto especificado
     */
    public void showPilotoCon(){
        System.out.println("Escribe el texto a contener: ");
        String letra = sc.nextLine().toUpperCase(Locale.ROOT);

        String sql = "from Piloto where nombre" + "like '%" + letra + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Piloto> result = em.createQuery(sql, Piloto.class).getResultList();
        for (Piloto piloto : result) {
            System.out.println(piloto.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar pilotos que empiezan por tal letra
     */
    public void showPilotoPor(){
        System.out.println("Escribe la letra de inicio: ");
        String letra = sc.nextLine().toUpperCase(Locale.ROOT);

        String sql = "from Piloto where nombre like '" + letra + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Piloto> result = em.createQuery(sql, Piloto.class).getResultList();
        for (Piloto piloto : result) {
            System.out.println(piloto.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar las id y los nombres de pilotos que hay
     */
    public void showPilotosNom(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Piloto> result = em.createQuery("from Piloto order by puntosTotales", Piloto.class).getResultList();
        for (Piloto piloto : result) {
            System.out.println(piloto.getNumero() + " " + piloto.getNombre());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para modificar el nombre de un piloto
     */
    public void modificarPiloto() {
        String numero = menu.NomMenu(connection,entityManagerFactory);
        System.out.println("Escribe el nuevo nombre: ");
        String nuevoNombre = sc.nextLine();

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Piloto piloto = (Piloto) em.find(Piloto.class, numero);
        piloto.setNombre(nuevoNombre);
        em.merge(piloto);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para borrar un piloto
     */
    public void borrarPiloto() {
        String numero = menu.NomMenu(connection,entityManagerFactory);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Piloto piloto = (Piloto) em.find(Piloto.class, numero);
        em.remove(piloto);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para borrar pilotos por escuderias
     */
    public void borrarPilotoPorEscuderia() {
        String escuderias = menu.EscuderiaMenu(connection,entityManagerFactory).toUpperCase(Locale.ROOT);
        String sql = "from Piloto where escuderia='" + escuderias + "'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Piloto> result = em.createQuery(sql, Piloto.class).getResultList();
        for (Piloto piloto : result) {
            em.remove(piloto);
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para dividir una frase en trozos depediendo del separador
     * @param string recibe una frase
     * @param delimiters recibe cual es el separador
     * @return devuelve un array de palabras separadas.
     */
    private static List<String> getTokenList(String string, String delimiters) {

        List<String> listTokens = new ArrayList<String>();

        try {

            StringTokenizer st = new StringTokenizer(string, delimiters);

            while( st.hasMoreTokens() ) {
                //add token to the list
                listTokens.add( st.nextToken() );
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        return listTokens;
    }
}
