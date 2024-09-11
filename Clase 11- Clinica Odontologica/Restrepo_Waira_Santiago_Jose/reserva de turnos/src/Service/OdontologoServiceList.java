package Service;

import Dao.OdontologiaDAOList;
import Model.Odontologo;

import java.util.List;

public class OdontologoServiceList {

    private OdontologiaDAOList odontologiaDAOList;

    public OdontologoServiceList(OdontologiaDAOList odontologiaDAOList) {
        this.odontologiaDAOList = odontologiaDAOList;
    }

    public void guardarOdontologoList(Odontologo odontologo){
        odontologiaDAOList.guardar(odontologo);

    }

    public List<Odontologo> listarOdontologos(){
        return odontologiaDAOList.buscarTodos();
    }
}
