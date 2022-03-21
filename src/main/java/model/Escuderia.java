package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase sirve para anotar el objeto de escuderia con su estructura
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "escuderia")
public class Escuderia implements Serializable {
    @Id
    @Column(name = "escuderia",length = 310)
    private String nombre;

    /**
     * Esto para pillar escuderia.
     * @return devuelve la escuderia.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Esto para asignar un escuderia.
     * @param escuderia recibe el que le vas a poner a el escuderia.
     */
    public void setNombre(String escuderia) {
        this.nombre = escuderia;
    }

    /**
     * Este metodo sirve para decir de que formato para mostrar.
     * @return un frase
     */
    @Override
    public String toString() {
        return "Escuderias{" +
                "escuderia='" + nombre + '\'' +
                '}';
    }

    /**
     * Este es el constructor de la clase
     * @param escuderia recibe un escuderia
     */
    public Escuderia(String escuderia){
        this.nombre = escuderia;
    }

    /**
     * Es un constructor
     */
    public Escuderia(){
        super();
    }

}
