package Dao;

import Model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologiaDAOH2 implements  iDao<Odontologo> {
    /*

    private String nombre;
    private String apellido;
    private  Integer matricula;

*/
    private static  final Logger logger = Logger.getLogger(OdontologiaDAOH2.class);
    private static  final String SQL_INSERT="INSERT INTO ODONTOLOGIA(MATRICULA,NOMBRE,APELLIDO) VALUES (?,?,?)";
    private static  final String SQL_SELECT_ONE="SELECT * FROM ODONTOLOGIA WHERE ID = ?";


    private static  final String SQL_SELECT_ALL= "SELECT * FROM ODONTOLOGIA";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Iniciando las oprecaciones de guardado de un odontologo");

        //Logica para persistir en la bd
        Connection connection = null;
        try {
            connection= BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1,odontologo.getMatricula());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3,odontologo.getApellido());
            psInsert.execute();
            logger.info("Datos cargados con exito");

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()){
                odontologo.setId(rs.getInt(1));

            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }


        return odontologo;
    }

    @Override
    public Odontologo buscarporid(Integer id) {

        logger.info("Iniciando las oprecaciones de buscado de un odontologo");
        Connection connection = null;
        Odontologo odontologo = null;
        try {
            connection= BD.getConnection();
            Statement statement =connection.createStatement();
            PreparedStatement psSelectOne = connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1,id);
            ResultSet rs = psSelectOne.executeQuery();

            while (rs.next()){
                odontologo = new Odontologo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));

            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        List<Odontologo> odontologos = new ArrayList<>();
        Connection connection = null;
        try {
            connection= BD.getConnection();
            PreparedStatement psSelectAll = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = psSelectAll.executeQuery();
            while (rs.next()){
                 odontologos.add( new Odontologo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));


            }


        }catch (Exception e){
            logger.error(e.getMessage());
        }


        return odontologos;
    }

}
