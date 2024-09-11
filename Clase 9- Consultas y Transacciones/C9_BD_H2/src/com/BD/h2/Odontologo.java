package com.BD.h2;

import java.sql.*;
import java.util.logging.Logger;

public class Odontologo {


    private static final Logger logger= Logger.getLogger(String.valueOf(Odontologo.class));
    private  static final String SQL_DROP_CREATE="DROP TABLE IF EXISTS ODONTOLOGO; " +
            "CREATE TABLE CUENTAS(ID INT PRIMARY KEY, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL , MATRICULA VARCHAR(100) NOT NULL)";

    private static final String SQL_INSERT="INSERT INTO ODONTOLOGO VALUES (?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE ODONTOLOGO SET MATRICULA=? WHERE ID=?";

    public static void main(String[] args) {

        Connection connection= null;
        try{
            connection= getConnection();
            //crear la tabla
            Statement statement= connection.createStatement();
            statement.execute(SQL_DROP_CREATE);
            logger.info("tabla creada con exito");
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT);
            //ahora debemos ingresar las parametrizadas
            psInsert.setInt(1,1);
            psInsert.setString(2,"Laura");
            psInsert.setString(3,"Lopez");
            psInsert.setString(4,"XWW");
            psInsert.execute(); //--> estos datos se confirmaron

            logger.info(" primera carga de datos cargados con exito");
            //ahora debemos sumar 10 al saldo

            PreparedStatement psUpdate= connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1,"XWX");
            psUpdate.setInt(4,1); //--> ID=1, Ub=2
            psUpdate.execute(); //--> este fue el savepoint
            logger.info(" segunda carga de datos cargados con exito");
            connection.setAutoCommit(false);/*El autocommit en la bd siempre esta en true, con este comando se desactiva*/
            //los datos se volvieron a confirmar

            PreparedStatement psTx= connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1,"ABC");
            psTx.setInt(4,1);
            psTx.execute();
            logger.info("saldo nuevamente actualizado con exito");

            connection.commit();
            connection.setAutoCommit(true);

        }catch (Exception e){
            //logger.error(e.getMessage());
            try{
                connection.rollback();
            }catch (SQLException sql){
                logger.warning(sql.getMessage());
            }
        }
    }

    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/TablaCuentas","admin","admin");

    }















    }



