package com.digitalhouse.ClinicaOdontologica.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Domicilio {
    private Integer id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

    public Domicilio() {
    }


    public Domicilio(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
}
