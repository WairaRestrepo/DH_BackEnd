package com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository;

import com.digitalhouse.ClinicaOdontologica.Model.Odontologo;
import com.digitalhouse.ClinicaOdontologica.Model.Paciente;
import com.digitalhouse.ClinicaOdontologica.Model.Turno;
import com.digitalhouse.ClinicaOdontologica.Repository.BDH2;
import com.digitalhouse.ClinicaOdontologica.Repository.IDao;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository.ImpleOdontologoRepository.SQL_INSERT_ODONTOLOGO;

public class ImpleListTurnoRepository implements IDao<Turno> {
    private static final Logger logger = LogManager.getLogger(ImpleListTurnoRepository.class);
    private static final String SQL_INSERT="INSERT INTO TURNOS (PACIENTE_ID, ODONTOLOGO_ID, FECHA) VALUES(?,?,?)";

    private static final String SQL_UPDATE = "UPDATE TURNOS SET PACIENTE_ID = ?, ODONTOLOGO_ID = ?, FECHA = ?, WHERE ID = ?";

    private static final String SQL_OBTENER_TURNOS = "SELECT * FROM TURNOS";

    private static final String SQL_SELECT_ONE = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";

    private static final String SQL_DELETE = "DELETE FROM PACIENTES WHERE ID = ?";
    private List<Turno> turnos= new ArrayList<>();

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
                turnos.add(turno);
            }
        }catch (Exception e) {
           turno = null;
            logger.error("Error al guardar turno{}", e.getMessage());
        }
        return turno;


    }


    @Override
    public Turno buscarPorId(Integer id) {
        logger.info("Inciando Operaciones de busqueda de turno por ID: {}", id);
       for (Turno turno: turnos) {
           if (turno.getId().equals(id)) {
               logger.info("Turno encontrado con exito");
               return turno;
           }
       }
         logger.error("No se encontro el turno con el ID: {}", id);
        return null;
    }


    @Override
    public Turno buscarPorString(String string) {
        return null;
    }

    @Override
    public void actualizar(Turno turno) {

        logger.info("Iniciando Operaciones de Guardado de Turno");
        try {
            Connection connection = BDH2.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1,turno.getPaciente().getId());
            psInsert.setInt(2,turno.getOdontologo().getId());
            psInsert.setDate(3, Date.valueOf(turno.getFecha()));
            psInsert.execute();
            logger.info("Datos del turno Actualizados con exito.");
            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()){
                turno.setId(rs.getInt(1));
                turnos.add(turno);
            }
        }catch (Exception e) {
            turno = null;
            logger.error("Error al actualizar turno{}", e.getMessage());
        }


    }

    @Override
    public void eliminar(Integer id) {
            logger.info("Intentando eliminar el Domcilio con id: {}", id);
            try {
                Connection connection = BDH2.getConnection();
                PreparedStatement psEliminar = connection.prepareStatement(SQL_DELETE);
                psEliminar.setInt(1, id);
                psEliminar.execute();
                logger.info("Turno Eliminado con exito");
            } catch (Exception e) {
                logger.error("Error de conexion al Eliminar el turno con id: {}, error: {} ", id, e.getMessage());
            }


    }

    @Override
    public List<Turno> mostrarTodos() {
        logger.info("Iniciando Operaciones de busqueda de todos los turnos");
        return turnos;
    }
}
