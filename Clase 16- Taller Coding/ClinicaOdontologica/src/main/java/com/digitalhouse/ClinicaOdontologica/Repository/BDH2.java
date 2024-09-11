package com.digitalhouse.ClinicaOdontologica.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BDH2 {
    private static final Logger logger = LogManager.getLogger(BDH2.class);

    private static final String URL = "jdbc:h2:mem:~/BDClinicaOdontologica";
    private static final String h2User = "sa";
    private static final String h2Passw = "sa";

    private static final String SQL_CREATE_ODONTOLOGOS = "DROP TABLE IF EXISTS ODONTOLOGOS;"
            + "CREATE TABLE ODONTOLOGOS(ID INT AUTO_INCREMENT PRIMARY KEY, " +
            "MATRICULA VARCHAR(100) NOT NULL, " +
            "NOMBRE VARCHAR(100) NOT NULL, " +
            "APELLIDO VARCHAR(100) NOT NULL);";

    private static final String SQL_CREATE_PACIENTES="DROP TABLE IF EXISTS PACIENTES; " +
            "CREATE TABLE PACIENTES(ID INT AUTO_INCREMENT PRIMARY KEY, " +
            "NOMBRE VARCHAR(100) NOT NULL, " +
            "APELLIDO VARCHAR(100) NOT NULL, " +
            "CEDULA VARCHAR(100) NOT NULL, " +
            "FECHA_INGRESO DATE NOT NULL, " +
            "DOMICILIO_ID INT NOT NULL, " +
            "EMAIL VARCHAR(100) NOT NULL)";
    private static final String SQL_CREATE_DOMICILIOS="DROP TABLE IF EXISTS DOMICILIOS; " +
            "CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT NOT NULL, " +
            "CALLE VARCHAR(100) NOT NULL, " +
            "NUMERO INT NOT NULL, " +
            "LOCALIDAD VARCHAR(100) NOT NULL, " +
            "PROVINCIA VARCHAR(100) NOT NULL)";

    private static final String SQL_TURNOS = "DROP TABLE IF EXISTS TURNOS; " +
            "CREATE TABLE TURNOS (ID INT AUTO_INCREMENT NOT NULL," +
            "PACIENTE_ID INT NOT NULL," +
            "ODONTOLOGO_ID INT NOT NULL," +
            "FECHA DATE NOT NULL)";

    private static final String SQL_INSERT_PRUEBA="INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES " +
            "('Jorgito','Pereyra','345678','2024-08-07','1','jorgito@digitalhouse.com');" +
            "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES('Siempre viva','748','Sprinfield','USA')";

    public static void sqlCrearTabla(){
        try {
            Connection connection = getConnection();
            logger.info("Conexion a h2 establecida con exito");

            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_ODONTOLOGOS);
            statement.execute(SQL_CREATE_DOMICILIOS);
            statement.execute(SQL_CREATE_PACIENTES);
            statement.execute(SQL_TURNOS);
            statement.execute(SQL_INSERT_PRUEBA);

            logger.info("datos y tablas creados con exito");

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(URL,h2User,h2Passw);
    }
}
