package Service;

import Dao.OdontologiaDAOH2;
import Dao.iDao;
import Model.Odontologo;

import java.util.List;

public class OdontologoService {

    private iDao<Odontologo> odontologoiDao;
//Se crea el constructor desde la clase.
    public OdontologoService() {
        odontologoiDao= new OdontologiaDAOH2();

    }
    //Todos los metodos basicos de comunicacion entre los idao y los datos (clase de negocio)

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoiDao.guardar(odontologo);
    }
    public List<Odontologo> listarOdontologos(){
        return odontologoiDao.buscarTodos();
    }

}
