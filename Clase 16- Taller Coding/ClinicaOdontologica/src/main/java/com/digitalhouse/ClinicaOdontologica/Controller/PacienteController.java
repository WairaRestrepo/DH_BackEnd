package com.digitalhouse.ClinicaOdontologica.Controller;


import com.digitalhouse.ClinicaOdontologica.Model.Domicilio;
import com.digitalhouse.ClinicaOdontologica.Model.Paciente;
import com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository.ImpleDomicilioRepository;
import com.digitalhouse.ClinicaOdontologica.Service.ServicioPaciente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/paciente")
public class PacienteController {
    private final ServicioPaciente servicioPaciente;

    public PacienteController() {
        servicioPaciente = new ServicioPaciente();
    }

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        Paciente nuevoPaciente = servicioPaciente.guardarPaciente(paciente);
        if (paciente != null){
            return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Integer id, @RequestBody Paciente pacienteActualizado){
        Paciente pacienteExistente = servicioPaciente.buscarPorId(id);
        if (pacienteExistente != null){
            pacienteActualizado.setId(id);
            servicioPaciente.actualizarPaciente(pacienteActualizado);
            return new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Integer id){
        Paciente pacienteBuscado = servicioPaciente.buscarPorId(id);
        if (pacienteBuscado != null){
            return new ResponseEntity<>(pacienteBuscado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id){
        Paciente pacienteAEliminar = servicioPaciente.buscarPorId(id);
        if (pacienteAEliminar != null){
            servicioPaciente.eliminarPaciente(id);
            Domicilio domicilioAEliminar = new ImpleDomicilioRepository().buscarPorId(pacienteAEliminar.getDomicilio().getId());
            new ImpleDomicilioRepository().eliminar(domicilioAEliminar.getId());
            return new ResponseEntity<>("Paciente Eliminado con Exito", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Paciente no existe o error en la busqueda", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes(){
        List<Paciente> todosLosPacientes = servicioPaciente.obtenerLosPacientes();
        if(todosLosPacientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(todosLosPacientes, HttpStatus.OK);
    }
    @GetMapping("/buscar")
    //El RequestParam es para que el usuario pueda buscar por el correo
    //tambien se usa para gregarlo a la vista.

    //Para la vista esto va a ser un objeto Model model
    //@RequestParam- permite mas de un parametro
    //@PathVariable- permite un solo parametro.
    //ResquestBody- permite recibir un objeto.

    public String buscarPorCorreo(Model model, @RequestParam("correo") String correo){
        Paciente pacienteBuscado = servicioPaciente.buscarPorCorreo(correo);
        System.out.println(pacienteBuscado.getNombre());
        model.addAttribute("nombre",pacienteBuscado.getNombre());
        model.addAttribute("apellido",pacienteBuscado.getApellido());
        return "pacientes/index";
    }

}
