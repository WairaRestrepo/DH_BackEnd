package Dao;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {

/*
private  Integer Id;
private String nombre;
    private String apellido;
    private  Integer matricula;
*/
    private static final Logger logger =Logger.getLogger(BD.class);
    private  static  final  String SQL_DROP_CREATE="DROP TABLE IF EXISTS ODONTOLOGIA; "+
            "CREATE TABLE ODONTOLOGIA (ID INT AUTO_INCREMENT PRIMARY KEY, MATRICULA INT NOT NULL, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL)";

    private  static  final  String SQL_INSERT_PRUEBA="INSERT INTO ODONTOLOGIA(MATRICULA,NOMBRE,APELLIDO) VALUES(100,'JUAN','LOPEZ');";

    private  static  final  String SQL_INSERT_PRUEBA1="INSERT INTO ODONTOLOGIA(MATRICULA,NOMBRE,APELLIDO) VALUES(101,'JUANA','PEREZ');";




    public static void crearTabla(){
       Connection connection = null;

        try {
            connection= getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_DROP_CREATE);
            logger.info("tabla creada con exito");

            statement.execute(SQL_INSERT_PRUEBA);
            statement.execute(SQL_INSERT_PRUEBA1);
            logger.info("Datos cargados con exito");
        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }
    public static Connection getConnection() throws  Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:~/DaoOdontologia","sa","sa");

    }



}
