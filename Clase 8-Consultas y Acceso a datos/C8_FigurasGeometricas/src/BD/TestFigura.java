package BD;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestFigura {

    private static final Logger logger= Logger.getLogger(TestFigura.class);

    public static void main(String[] args) {

        //Se inicia la conexion en nula

        Connection connection=null;
        //Como la conexion puede dar error se crea un tru /catch
        try{
            connection= getConnection();
            //Comunicar desde el mundo de los objetos a las tablas
            logger.info("conexion establecida");
            Statement statement= connection.createStatement();
            //Luego va a ejecutar un String de tpo SQL.
            statement.execute("DROP TABLE IF EXISTS FIGURAS; " +
                    "CREATE TABLE FIGURAS (ID INT PRIMARY KEY, " +
                    "TIPO VARCHAR(40) NOT NULL, COLOR VARCHAR(50) NOT NULL)");
            //INSERTAR LAS FIGURAS
            System.out.println("****************************Insertando************************************");
            statement.execute("INSERT INTO FIGURAS VALUES(1,'CUADRADO','ROJO'),(2,'CUADRADO','VERDE'),(3,'CUADRADO','AZUL'),(4,'CIRCULO','ROJO'),(5,'CIRCULO','VERDE')");
            //para mostrar necesito un Objeto (ResultSet/ Elemento para traer consultas que vienen desde la BD).
            //Quiero que lo que consulte me lo guarde en un objeto de tipo ResultSet


            ResultSet rs= statement.executeQuery("SELECT * FROM FIGURAS");
            //Debo de recorrer todos los objetos resultantes , mediante wile con el metodo  next  va ir recorriendo fila por fila

            while (rs.next()){
                //mostrar por pantalla/ se coloca 2 de index porque primero esta el id.

                System.out.println("Nombre: "+rs.getString(2)+" tipo: "+rs.getString(3));

            }
            System.out.println("****************************Eliminamos************************************");

            //eliminamos 1
            statement.execute("DELETE  FROM FIGURAS WHERE ID=3");
            rs= statement.executeQuery("SELECT * FROM FIGURAS");
            System.out.println("Eliminamos LA FIGURA CON ID = 3");
            System.out.println("****************************Mostrando el resultado************************************");

            while (rs.next()){
                //mostrar por pantalla
                System.out.println("ID: "+rs.getInt(1)+" -Nombre: "+rs.getString(2)+" tipo: "+rs.getString(3));

            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:~/tablaFiguras","sa","sa");
    }


}
