package com.digitalhouse.ClinicaOdontologica.Controller;

import com.digitalhouse.ClinicaOdontologica.Model.Odontologo;
import com.digitalhouse.ClinicaOdontologica.Service.ServicioOdontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private final ServicioOdontologo servicioOdontologo;

    public OdontologoController() {
        servicioOdontologo = new ServicioOdontologo();
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo nuevoOdontologo = servicioOdontologo.guardarOdontologo(odontologo);
        if(odontologo != null){
            return new ResponseEntity<>(nuevoOdontologo, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizarOdontologo(@PathVariable Integer id, @RequestBody Odontologo odontologo){
        Odontologo odontologoExistente = servicioOdontologo.buscarPorID(id);
        if (odontologoExistente != null){
            odontologo.setId(id);
            servicioOdontologo.actualizarOdontologo(odontologo);
            return new ResponseEntity<>(odontologo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorIdOdontologo(@PathVariable Integer id){
        Odontologo odontologoABuscar = servicioOdontologo.buscarPorID(id);
        if (odontologoABuscar != null){
            return new ResponseEntity<>(odontologoABuscar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
        Odontologo odontologoAEliminar = servicioOdontologo.buscarPorID(id);
        if(odontologoAEliminar != null){
            servicioOdontologo.eliminarOdontologo(id);
            return new ResponseEntity<>("Odontologo Eliminado con Exito", HttpStatus.OK);
        }
        return new ResponseEntity<>("Odontologo no eliminado, error en la busqueda", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Odontologo>> mostrarOdontologos(){
        List<Odontologo> todosLosOdontologos = servicioOdontologo.mostrarOdontologos();
        if (todosLosOdontologos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(todosLosOdontologos, HttpStatus.OK);
    }
}
