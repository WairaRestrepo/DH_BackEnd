package com.digitalhouse.ClinicaOdontologica.Controller;

import com.digitalhouse.ClinicaOdontologica.Model.Domicilio;
import com.digitalhouse.ClinicaOdontologica.Model.Odontologo;
import com.digitalhouse.ClinicaOdontologica.Model.Paciente;
import com.digitalhouse.ClinicaOdontologica.Model.Turno;
import com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository.ImpleDomicilioRepository;
import com.digitalhouse.ClinicaOdontologica.Service.ServicioOdontologo;
import com.digitalhouse.ClinicaOdontologica.Service.ServicioPaciente;
import com.digitalhouse.ClinicaOdontologica.Service.ServicioTurno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private final ServicioTurno servicioTurno;
    private final ServicioPaciente servicioPaciente;
    private final ServicioOdontologo servicioOdontologo;

    public TurnoController() {
        servicioTurno = new ServicioTurno();
        servicioPaciente = new ServicioPaciente();
        servicioOdontologo = new ServicioOdontologo();

    }

    //Este metodo es para guardar un turno
    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        // paciente service es que se encarga de buscar el paciente por id.
        Paciente pacienteBuscado = servicioPaciente.buscarPorId(turno.getPaciente().getId());
        // odontologo service es que se encarga de buscar el odontologo por id.
        Odontologo odontologoBuscado = servicioOdontologo.buscarPorID(turno.getOdontologo().getId());
        // si el paciente y el odontologo existen, se guarda el turno.
        if ((pacienteBuscado != null) && (odontologoBuscado != null)){
            System.out.println("ENTRE AL IF");
            System.out.println(pacienteBuscado.getNombre());
            System.out.println(odontologoBuscado.getNombre());
            turno.setPaciente(pacienteBuscado);
            turno.setOdontologo(odontologoBuscado);
            Turno nuevoTurno = servicioTurno.guardarTurno(turno);
            return new ResponseEntity<>(nuevoTurno, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        //Bad_Request es un error 400
    }

    //Este metodo es para actualizar un turno

    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizarTurno(@PathVariable Integer id, @RequestBody Turno turno){
        Turno turnoExistente = servicioTurno.buscarPorId(id);
        if (turnoExistente!= null){
            turno.setId(id);
            servicioTurno.actualizarTurno(turno);
            return new ResponseEntity<>(turno, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Este metodo es para buscar un turno por id

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
       Turno turnoAEliminar = servicioTurno.buscarPorId(id);
        if (turnoAEliminar != null){
            servicioTurno.eliminarTurno(id);
            Domicilio domicilioAEliminar = new ImpleDomicilioRepository().buscarPorId(turnoAEliminar.getPaciente().getDomicilio().getId());
            new ImpleDomicilioRepository().eliminar(domicilioAEliminar.getId());
            return new ResponseEntity<>("Turno Eliminado con Exito", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Turno no existe o error en la busqueda", HttpStatus.BAD_REQUEST);
        }
    }

  //Este metodo es para listar todos los turnos


    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> mostrarTurnos(){
        List<Turno> todosLosTurnos = servicioTurno.listarTodos();
        System.out.println(todosLosTurnos.isEmpty());
        if (todosLosTurnos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(todosLosTurnos, HttpStatus.OK);
    }



}
