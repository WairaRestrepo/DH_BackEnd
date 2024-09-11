package com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository;

import com.digitalhouse.ClinicaOdontologica.Model.Odontologo;
import com.digitalhouse.ClinicaOdontologica.Model.Paciente;
import com.digitalhouse.ClinicaOdontologica.Model.Turno;
import com.digitalhouse.ClinicaOdontologica.Repository.BDH2;
import com.digitalhouse.ClinicaOdontologica.Repository.IDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ImpleTurnoRepository implements IDao<Turno> {
    private static final Logger logger = LogManager.getLogger(ImpleTurnoRepository.class);
    private static final String SQL_INSERT="INSERT INTO TURNOS (PACIENTE_ID, ODONTOLOGO_ID, FECHA) VALUES(?,?,?)";
    private static final String SQL_OBTENER_TURNOS = "SELECT * FROM TURNOS";



    @Override
    public Turno guardar(Turno turno) {
        logger.info("Iniciando Operaciones de Guardado de Turno");

        try {
            Connection connection = BDH2.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1,turno.getPaciente().getId());
            psInsert.setInt(2,turno.getOdontologo().getId());
            psInsert.setDate(3, Date.valueOf(turno.getFecha()));
            psInsert.execute();
            logger.info("Datos del turno Guardados con exito.");
            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()){
                turno.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            turno = null;
            logger.error("Error de conexion al guardar el Turno: {}", e.getMessage());
        }
        return turno;
    }


    @Override
    public Turno buscarPorId(Integer id) {
        logger.info("Inciando Operaciones de busqueda de turno por ID: {}", id);
        return null;
    }

    @Override
    public Turno buscarPorString(String string) {
        return null;
    }

    @Override
    public void actualizar(Turno turno) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Turno> mostrarTodos() {
        List<Turno> turnos = new ArrayList<>();
        ImplePacienteRepository pacienteAux = new ImplePacienteRepository();
        ImpleOdontologoRepository odotologoAux = new ImpleOdontologoRepository();
        try {
            Connection connection = BDH2.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_OBTENER_TURNOS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("ID");
                Paciente paciente = pacienteAux.buscarPorId(rs.getInt("PACIENTE_ID"));
                Odontologo odontologo = odotologoAux.buscarPorId(rs.getInt("ODONTOLOGO_ID"));
                LocalDate fecha = rs.getDate("FECHA").toLocalDate();
                turnos.add(new Turno(id, paciente, odontologo, fecha));
            }
            logger.info("Se obtuvieron todos los turnos con exito.");
        } catch (Exception e) {
            logger.error("Error en la conexion al obtener todos los turnos: {}", e.getMessage());
        }
        return turnos;
    }
}
