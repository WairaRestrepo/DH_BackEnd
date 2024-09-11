package com.digitalhouse.ClinicaOdontologica.Controller;

import com.digitalhouse.ClinicaOdontologica.Model.Odontologo;
import com.digitalhouse.ClinicaOdontologica.Model.Paciente;
import com.digitalhouse.ClinicaOdontologica.Model.Turno;
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

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        Paciente pacienteBuscado = servicioPaciente.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologoBuscado = servicioOdontologo.buscarPorID(turno.getOdontologo().getId());
        if ((pacienteBuscado != null) && (odontologoBuscado != null)){
            turno.setPaciente(pacienteBuscado);
            turno.setOdontologo(odontologoBuscado);
            Turno nuevoTurno = servicioTurno.guardarTurno(turno);
            return new ResponseEntity<>(nuevoTurno, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> mostrarTurnos(){
        List<Turno> todosLosTurnos = servicioTurno.listarTodos();
        if (todosLosTurnos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(todosLosTurnos, HttpStatus.OK);
    }
}
