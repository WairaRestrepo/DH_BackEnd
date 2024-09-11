package Service;

import Dao.MedicamentoDAOH2;
import Dao.iDao;
import Model.Medicamento;

public class MedicamentoService {

    private iDao<Medicamento> medicamentoiDao;

    public MedicamentoService() {
        //se pone DAOH2 para que se conecte a la base de datos
        medicamentoiDao=new MedicamentoDAOH2();
    }

    //todos los medotos basicos de comunicacion entre los idao y los datos(clases de negocio)
    public Medicamento guardarUnMedicamento(Medicamento medicamento){
        return medicamentoiDao.guardar(medicamento);
    }
    public Medicamento buscarPorId(Integer id){
        return medicamentoiDao.buscarPorId(id);
    }
}

