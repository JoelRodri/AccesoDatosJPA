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
 * Esta clase sirve para controlar la tabla escuderia situada en la base de datos
 */
public class EscuderiaController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner sc;
    List<Escuderia> escuderias;
    Menu menu = new Menu();

    /**
     * Esto es el constructor de la clase
     * @param connection recibe la coneccion hacia postgres
     * @param entityManagerFactory recibe el entityManagerFactory
     */
    public EscuderiaController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.sc = new Scanner(System.in);
        escuderias = new ArrayList<>();
    }

    /**
     * Este metodo sirve para leer el fichero, lo mete en una lista y lo devuelve
     * @param file rebie la ruta del fichero
     * @return devuelve una lista de Escuderia
     * @throws IOException
     */
    public List<Escuderia> readEscuderiaFile(String file) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while((linea = br.readLine()) != null){
            List<String> listToken = getTokenList(linea, "\"");
             escuderias.add(new Escuderia(listToken.get(4)));
        }

        return escuderias;
    }

    /**
     * Para añadir escuderia
     * @param escuderia
     */
    public void addEscuderia(Escuderia escuderia) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(escuderia);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar escuderias
     */
    public void showEscuderia(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Escuderia> result = em.createQuery("from Escuderia", Escuderia.class).getResultList();
        for (Escuderia escuderia : result) {
            System.out.println(escuderia.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para modificar la escuderia de los pilotos
     */
    public void modificarEscuderia(){
        String escuderia = menu.EscuderiaMenu(connection,entityManagerFactory).toUpperCase(Locale.ROOT);
        System.out.println("Escribe la primera letra del piloto que quieras modificar ?");
        String letra = sc.nextLine().toUpperCase(Locale.ROOT);
        String sql = "from Piloto where nom like '" + letra + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Piloto> result = em.createQuery(sql, Piloto.class).getResultList();
        for (Piloto piloto : result) {
            piloto.setEscuderia(new Escuderia(escuderia));
            em.merge(piloto);
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para dividir una frase depediendo del separador
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
