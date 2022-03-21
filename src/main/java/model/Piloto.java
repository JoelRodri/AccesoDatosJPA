package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase sirve para anotar el objeto de piloto con su estructura
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "campeon")
public class Piloto implements Serializable {
    @Id
    @Column(name = "numero",length = 5)
    String numero;
    @Column(name = "nombre",length = 20)
    String nombre;

    @ManyToOne
    @JoinColumn(name = "escuderia")
    Escuderia escuderia;

    @Column(name = "pais", length = 20)
    String pais;
    @Column(name = "podiums", length = 20)
    String podiums;
    @Column(name = "puntosTotales", length = 20)
    String puntosTotales;
    @Column(name = "gpCompletados", length = 20)
    String gpCompletados;
    @Column(name = "titulosMundiales", length = 20)
    String titulosMundiales;
    @Column(name = "mejorPos", length = 20)
    String mejorPos;
    @Column(name = "mejorClas", length = 20)
    String mejorClas;
    @Column(name = "fechaNaci", length = 20)
    String fechaNaci;
    @Column(name = "nacionalidad", length = 20)
    String nacionalidad;




    /**
     * Este es el constructor de la clase
     * @param numero recibe el numero
     * @param nombre recibe un nombre
     * @param escuderia recibe un escuderia
     * @param pais recibe una historia
     * @param podiums numero de podiums
     * @param puntosTotales puntos totales
     * @param gpCompletados GP completados
     * @param titulosMundiales numero de titulos mundiales
     * @param mejorPos numero de veces mejor posicion
     * @param mejorClas numero de veces mejor calsificacion
     * @param fechaNaci fecha de nacimiento
     * @param nacionalidad nacionalidad
     */
    public Piloto(String nombre, String numero, Escuderia escuderia, String pais, String podiums, String puntosTotales, String gpCompletados, String titulosMundiales, String mejorPos, String mejorClas, String fechaNaci, String nacionalidad) {
        super();
        this.nombre = nombre;
        this.numero = numero;
        this.escuderia = escuderia;
        this.pais = pais;
        this.podiums = podiums;
        this.puntosTotales = puntosTotales;
        this.gpCompletados = gpCompletados;
        this.titulosMundiales = titulosMundiales;
        this.mejorPos = mejorPos;
        this.mejorClas = mejorClas;
        this.fechaNaci = fechaNaci;
        this.nacionalidad = nacionalidad;
    }

    /**
     * Es un constructor
     */
    public Piloto(){
        super();
    }



    /**
     * Esto para pillar la id.
     * @return devuelve la id.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Esto para asignar una id.
     * @param id recibe el que le vas a poner a la id.
     */
    public void setNumero(String id) {
        this.numero = id;
    }

    /**
     * Esto para pillar el nombre.
     * @return devuelve el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Esto para asignar un nombre.
     * @param name recibe el que le vas a poner a lla nombre.
     */
    public void setNombre(String name) {
        this.nombre = name;
    }

    /**
     * Esto para pillar historia.
     * @return devuelve la historia.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Esto para asignar una historia.
     * @param pais recibe el que le vas a poner a la historia.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Esto para pillar escuderia.
     * @return devuelve el escuderia.
     */
    public Escuderia getEscuderia() {
        return escuderia;
    }

    /**
     * Esto para asignar un escuderia.
     * @param escuderia recibe el que le vas a poner a el escuderia.
     */
    public void setEscuderia(Escuderia escuderia) {
        this.escuderia = escuderia;
    }

    /**
     * Este metodo sirve para decir de que formato para mostrar.
     * @return una frase
     */

    @Override
    public String toString() {
        return "Piloto{" +
                "numero='" + numero + '\'' +
                ", nombre='" + nombre + '\'' +
                ", escuderia=" + escuderia.toString() +
                ", pais='" + pais + '\'' +
                ", podiums='" + podiums + '\'' +
                ", puntosTotales='" + puntosTotales + '\'' +
                ", gpCompletados='" + gpCompletados + '\'' +
                ", titulosMundiales='" + titulosMundiales + '\'' +
                ", mejorPos='" + mejorPos + '\'' +
                ", mejorClas='" + mejorClas + '\'' +
                ", fechaNaci='" + fechaNaci + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}
