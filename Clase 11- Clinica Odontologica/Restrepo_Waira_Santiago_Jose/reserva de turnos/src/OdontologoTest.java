import Dao.BD;
import Dao.OdontologiaDAOList;
import Model.Odontologo;
import Service.OdontologoService;
import Service.OdontologoServiceList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OdontologoTest {
    @Test
    public void listarOdontologos(){
        BD.crearTabla();
        OdontologoService odontologoService= new OdontologoService();
        List<Odontologo> odontologos = new ArrayList<>();
        odontologos = odontologoService.listarOdontologos();
        Assertions.assertFalse(odontologos.isEmpty());

    }

    @Test
    public void testListaOdontologos(){
        Odontologo odontologo = new Odontologo(1,2,"Juan","Perez");
        Odontologo odontologo1 = new Odontologo(2,3,"Juana","Lopez");
        OdontologiaDAOList odontologiaDAOList = new OdontologiaDAOList();
        OdontologoServiceList odontologoServiceList = new OdontologoServiceList(odontologiaDAOList);
        odontologoServiceList.guardarOdontologoList(odontologo);
        odontologoServiceList.guardarOdontologoList(odontologo1);
        List<Odontologo> odontologos = new ArrayList<>();
        odontologos = odontologoServiceList.listarOdontologos();
        Assertions.assertFalse(odontologos.isEmpty());

    }




}
