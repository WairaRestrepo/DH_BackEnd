package com.digitalhouse.ClinicaOdontologica.Repository.ImplementacionRepository;

import com.digitalhouse.ClinicaOdontologica.Repository.BDH2;
import com.digitalhouse.ClinicaOdontologica.Repository.IDao;
import com.digitalhouse.ClinicaOdontologica.Model.Domicilio;

import com.digitalhouse.ClinicaOdontologica.Model.Paciente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ImplePacienteRepository implements IDao<Paciente> {
    private static final Logger logger= LogManager.getLogger(ImplePacienteRepository.class);
    private static final String SQL_INSERT="INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID, EMAIL) " +
            "VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM PACIENTES WHERE ID=? ";
    private static final String SQL_OBTENER_PACIENTES = "SELECT * FROM PACIENTES";
    private static final String SQL_SELECT_EMAIL="SELECT * FROM PACIENTES WHERE EMAIL=? ";
    private static final String SQL_UPDATE_PACIENTE = "UPDATE PACIENTES SET NOMBRE = ?, APELLIDO = ?, CEDULA = ?, FECHA_INGRESO = ?, DOMICILIO_ID = ?, EMAIL = ? WHERE ID = ?";
    private static final String SQL_DELETE_PACIENTE = "DELETE FROM PACIENTES WHERE ID = ?";

    @Override
    public Paciente guardar(Paciente paciente) {
        logger.info("iniciando las operaciones de guardado de un paciente");
        Connection connection= null;
        ImpleDomicilioRepository impleDomicilioRepository = new ImpleDomicilioRepository();
        //la responsabilidad de guardar el domicilio la delegamos a la clase ImpleDomicilioRepository(DAOdomicilio.)
        Domicilio domicilio = impleDomicilioRepository.guardar(paciente.getDomicilio());
        try{
            connection= BDH2.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5,domicilio.getId());
            psInsert.setString(6,paciente.getEmail());
            psInsert.execute();// no es necesario ejecutar el preparedstatement ya que en el resulset se ejecuta de forma automatica y
            logger.info("Datos de Paciente guardados con Exito");
            ResultSet rs= psInsert.getGeneratedKeys(); //se van a generar el ID
            while(rs.next()){
                paciente.setId(rs.getInt(1));// al generarse el ID con el resulset, lo obtenemos de la misma consulta y lo asignamos a nuestro objeto
            }
        }catch (Exception e){
            paciente = null;
            logger.error("conexion fallida: "+e.getMessage());
        }
        return paciente;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        logger.info("iniciando las operaciones de buscado de un paciente");

        Paciente paciente=null;
        Domicilio domicilio=null;
        try{
            Connection connection= BDH2.getConnection();
            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1,id);
            ResultSet rs= psSelectOne.executeQuery();
            ImpleDomicilioRepository daoAux= new ImpleDomicilioRepository();
            while (rs.next()){
                domicilio= daoAux.buscarPorId(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio, rs.getString(7));
            }

        }catch (Exception e){
            logger.error("conexion fallida: "+e.getMessage());
        }
        return paciente;
    }

    @Override
    public Paciente buscarPorString(String string) {
        logger.info("Iniciando las Operaciones de busqueda : ${}", string);
        //Se coloca el paciente de tipo paciente.
        Paciente paciente = null;
        Domicilio domicilio = null;
        ImpleDomicilioRepository daoAux = new ImpleDomicilioRepository();
        try {
            Connection connection = BDH2.getConnection();
            PreparedStatement psSelectEmail = connection.prepareStatement(SQL_SELECT_EMAIL);
            //Se le asigna el valor del string a buscar
            psSelectEmail.setString(1, string);
            //Se guarda el valor seteado en rs
            ResultSet rs = psSelectEmail.executeQuery();
            while (rs.next()){
                //mientras que el rs tenga un registro se va a buscar el domicilio por id.
                domicilio = daoAux.buscarPorId(rs.getInt("DOMICILIO_ID"));
                paciente = new Paciente(rs.getInt("ID"),rs.getString("NOMBRE"),rs.getString("APELLIDO"),rs.getString("CEDULA"),rs.getDate("FECHA_INGRESO").toLocalDate(),domicilio,rs.getString("EMAIL"));
            }
        } catch (Exception e) {
            logger.error("Fallo en la conexion: ${}", e.getMessage());
        }
        if (paciente != null) {
            logger.info("Paciente encontrado con exito!!!");
        }else {
            logger.warn("error en la busqueda, paciente null");
        }
        return paciente;
    }

    @Override
    public void actualizar(Paciente paciente) {
        logger.info("Inicio de actualizacion de Paciente");

        ImpleDomicilioRepository impleDomicilioRepository = new ImpleDomicilioRepository();
        impleDomicilioRepository.actualizar(paciente.getDomicilio());
        Domicilio domicilio = impleDomicilioRepository.buscarPorId(paciente.getDomicilio().getId());
        logger.info("Domicilio encontrado correctamente.");
        try {
            //Se realiza conexion a la BD
            Connection connection = BDH2.getConnection();
            //Se mete el sql en psactualizar y se le asignan los valores.
            PreparedStatement psActualizar = connection.prepareStatement(SQL_UPDATE_PACIENTE);
            psActualizar.setString(1,paciente.getNombre());
            psActualizar.setString(2, paciente.getApellido());
            psActualizar.setString(3, paciente.getCedula());
            psActualizar.setDate(4,Date.valueOf(paciente.getFechaIngreso()));
            psActualizar.setInt(5,domicilio.getId());
            psActualizar.setString(6, paciente.getEmail());
            psActualizar.setInt(7, paciente.getId());
            Integer filasActualizadas = psActualizar.executeUpdate();
            if(filasActualizadas > 0){
                logger.info("Paciente con ID: {} actualizado correctamente", paciente.getId());
            }else {
                logger.warn("no se encontro paciente con ID: {}", paciente.getId());
            }

        } catch (Exception e) {
            logger.error("Error de Conexion al actualizar Paciente, error: {}", e.getMessage());
        }
    }

    @Override
    public void eliminar(Integer id) {
        logger.warn("Intentando Eliminar un paciente por ID: {}", id);
        try {
            Connection connection = BDH2.getConnection();
            PreparedStatement psEliminar = connection.prepareStatement(SQL_DELETE_PACIENTE);
            psEliminar.setInt(1,id);
            psEliminar.execute();
            logger.warn("Paciente Eliminado con exito!!!");
        } catch (Exception e) {
            logger.error("error de conexion al eliminar paciente : ${}", e.getMessage());
        }
    }

    @Override
    public List<Paciente> mostrarTodos() {
        List<Paciente> todosLosPacientes = new ArrayList<>();
        ImpleDomicilioRepository auxDomicilio = new ImpleDomicilioRepository();
        try {
            Connection connection = BDH2.getConnection();
            PreparedStatement psObtener = connection.prepareStatement(SQL_OBTENER_PACIENTES);
            ResultSet resultSet = psObtener.executeQuery();
            while (resultSet.next()){
                Integer id = resultSet.getInt("ID");
                String nombre = resultSet.getString("NOMBRE");
                String apellido = resultSet.getString("APELLIDO");
                String cedula = resultSet.getString("CEDULA");
                LocalDate fechaIngreso = resultSet.getDate("FECHA_INGRESO").toLocalDate();
                Domicilio domicilio = auxDomicilio.buscarPorId(resultSet.getInt("DOMICILIO_ID"));
                String email = resultSet.getString("EMAIL");

                todosLosPacientes.add(new Paciente(id, nombre, apellido, cedula, fechaIngreso, domicilio, email));
            }
            logger.info("Se obtuvieron todos los Pacientes de la base de datos");
        } catch (Exception e) {
            logger.error("error de conexion al obtener todos los pacientes: {}", e.getMessage());
        }
        return todosLosPacientes;
    }
}
