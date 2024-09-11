package com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository;

import com.digitalhouse.ClinicaOdontologica.Repository.BDH2;
import com.digitalhouse.ClinicaOdontologica.Repository.IDao;
import com.digitalhouse.ClinicaOdontologica.Model.Odontologo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImpleOdontologoRepository implements IDao<Odontologo> {
    private static final Logger logger = LogManager.getLogger(ImpleOdontologoRepository.class);

    private static final String SQL_INSERT_ODONTOLOGO = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_OBTENER_ODONTOLOGOS = "SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_ONE = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";
    private static final String SQL_DELETE_ODONTOLOGO = "DELETE FROM ODONTOLOGOS WHERE ID = ?";
    private static final String SQL_UPDATE_ODONTOLOGO = "UPDATE ODONTOLOGOS SET MATRICULA = ?, NOMBRE = ?, APELLIDO = ? WHERE ID = ?";



    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Inciando operacion de guardado de un odontologo");
        try {
            Connection connection = BDH2.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT_ODONTOLOGO, Statement.RETURN_GENERATED_KEYS);

            psInsert.setString(1,odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.execute();
            logger.info("Datos de Odotologo guardados con Exito");
            ResultSet resultSet = psInsert.getGeneratedKeys();
            while (resultSet.next()) {
                odontologo.setId(resultSet.getInt(1));
            }

        } catch (Exception e) {
            odontologo = null;
            logger.error("Error al guardar Odontologo {}", e.getMessage());
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        logger.info("Buscando por ID a un Odontologo");
        Odontologo odontologo = null;
        try {
            Connection connection = BDH2.getConnection();
            PreparedStatement psBuscarID = connection.prepareStatement(SQL_SELECT_ONE);
            psBuscarID.setInt(1,id);
            ResultSet resultado = psBuscarID.executeQuery();
            if (resultado.next()){
                odontologo = new Odontologo(resultado.getInt("ID"), resultado.getString("MATRICULA"), resultado.getString("NOMBRE"), resultado.getString("APELLIDO") );
                logger.info("Odontologo encontrado con exito id: {}", id);
            }else {
                logger.info("Odontologo No encontrado con id: {}", id);
            }

        } catch (Exception e) {
            logger.error("Error al buscar por id a un odontologo: {}", e.getMessage());
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorString(String string) {
        return null;
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        logger.info("Iniciando Actualizacion de datos de Odontologo");
        try {
            Connection connection = BDH2.getConnection();
            PreparedStatement psActualizar = connection.prepareStatement(SQL_UPDATE_ODONTOLOGO);
            psActualizar.setString(1, odontologo.getMatricula());
            psActualizar.setString(2, odontologo.getNombre());
            psActualizar.setString(3, odontologo.getApellido());
            psActualizar.setInt(4, odontologo.getId());
            psActualizar.execute();
            logger.info("Odontologo actualizado con exito");
        } catch (Exception e) {
            logger.error("Error al Actualizar el odontologo: {}",e.getMessage());
        }
    }

    @Override
    public void eliminar(Integer id) {
        logger.warn("Iniciando operaciones para eliminar un Odontologo");
        try {
            Connection connection = BDH2.getConnection();
            PreparedStatement psEliminar = connection.prepareStatement(SQL_DELETE_ODONTOLOGO);
            psEliminar.setInt(1, id);
            logger.warn("Odontologo a eliminar con ID: {}", id);
            psEliminar.execute();
            logger.info("Odontologo eliminado con exito");
        } catch (Exception e) {
            logger.error("Error al Eliminar Odontologo: {}", e.getMessage());
        }
    }

    @Override
    public List<Odontologo> mostrarTodos() {
        List<Odontologo> listaOdontologos = new ArrayList<>();
        try {
            Connection connection = BDH2.getConnection();
            PreparedStatement psObtener = connection.prepareStatement(SQL_OBTENER_ODONTOLOGOS);
            ResultSet resultSet = psObtener.executeQuery();
            while (resultSet.next()){
                listaOdontologos.add(new Odontologo(resultSet.getInt("ID"), resultSet.getString("MATRICULA"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO")));
            }
            logger.info("se obtuvieron todos los odontologos de la Base de Datos");
        } catch (Exception e) {
            logger.error("Error al obtener todos los Odontologos: {}", e.getMessage());
        }
        return listaOdontologos;
    }
}
