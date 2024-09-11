package com.digitalhouse.ClinicaOdontologica.Service;

import com.digitalhouse.ClinicaOdontologica.Repository.IDao;
import com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository.ImplePacienteRepository;
import com.digitalhouse.ClinicaOdontologica.Model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPaciente {
    private final IDao<Paciente> pacienteRepository;

    public ServicioPaciente() {
        pacienteRepository = new ImplePacienteRepository();
    }
    //metodos manuales CRUD
    public Paciente guardarPaciente(Paciente paciente){return pacienteRepository.guardar(paciente);}
    public Paciente buscarPorId(Integer id){
        return pacienteRepository.buscarPorId(id);
    }
    public void actualizarPaciente(Paciente paciente){ pacienteRepository.actualizar(paciente);}
    public Paciente buscarPorCorreo(String correo){ return pacienteRepository.buscarPorString(correo); }
    public void eliminarPaciente(Integer id){ pacienteRepository.eliminar(id);}
    public List<Paciente> obtenerLosPacientes(){ return pacienteRepository.mostrarTodos(); }
}
