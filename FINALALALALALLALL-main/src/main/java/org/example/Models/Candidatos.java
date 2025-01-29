package org.example.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
public class Candidatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use Identity for auto-increment
    private String lista;

    @Column(nullable = false, unique = true)
    private String nombre;
    private String edad;
    private String ciudad;
    private String u_titulo_reg;
    private String actividad;
    private String Partido;
    private String Binomio;

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getU_titulo_reg() {
        return u_titulo_reg;
    }

    public void setU_titulo_reg(String u_titulo_reg) {
        this.u_titulo_reg = u_titulo_reg;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getPartido() {
        return Partido;
    }

    public void setPartido(String partido) {
        Partido = partido;
    }

    public String getBinomio() {
        return Binomio;
    }

    public void setBinomio(String binomio) {
        Binomio = binomio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Candidatos{" +
                "Lista=" + lista +
                ", Edad='" + edad + '\'' +
                ", Ciudad='" + ciudad + '\'' +
                ", Ultimo titulo registrado='" + u_titulo_reg + '\'' +
                ", nombre='" + nombre + '\'' +
                ", actividad='" + actividad + '\'' +
                ", Partido='" + Partido + '\'' +
                ", Binomio='" + Binomio + '\'' +
                '}';
    }


}
