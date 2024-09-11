package com.digitalhouse.ClinicaOdontologica.Controller;

import com.digitalhouse.ClinicaOdontologica.Model.Domicilio;
import com.digitalhouse.ClinicaOdontologica.Model.Odontologo;
import com.digitalhouse.ClinicaOdontologica.Service.ServicioDomicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Noo se utiliza
@RestController
@RequestMapping("/domicilio")

public class DomicilioController {

    private  ServicioDomicilio servicioDomicilio;

    public DomicilioController() {
        servicioDomicilio = new ServicioDomicilio();
    }

    @PostMapping
    public ResponseEntity<Domicilio> guardarOdontologo(@RequestBody Domicilio domicilio){
            Domicilio nuevoDomicilio = servicioDomicilio.guardarDomicilio(domicilio);
        if(domicilio != null){
            return new ResponseEntity<>(nuevoDomicilio, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Domicilio> actualizarDomicilio(@PathVariable Integer id, @RequestBody Domicilio domicilio){
        Domicilio domicilioExistente = servicioDomicilio.buscarPorID(id);
        if (domicilioExistente != null){
            domicilio.setId(id);
            servicioDomicilio.actualizarDomicilio(domicilio);
            return new ResponseEntity<>(domicilio, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDomicilio(@PathVariable Integer id){
        Domicilio domicilioAEliminar = servicioDomicilio.buscarPorID(id);
        if(domicilioAEliminar != null){
            servicioDomicilio.eliminarDomicilio(id);
            return new ResponseEntity<>("Domicilio Eliminado con Exito", HttpStatus.OK);
        }
        return new ResponseEntity<>("Domicilio no eliminado, error en la busqueda", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Domicilio>> mostraDomicilio(){
        List<Domicilio> todosLosDomicilios = servicioDomicilio.mostrarDomicilios();
        if (todosLosDomicilios.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(todosLosDomicilios, HttpStatus.OK);
    }




}
