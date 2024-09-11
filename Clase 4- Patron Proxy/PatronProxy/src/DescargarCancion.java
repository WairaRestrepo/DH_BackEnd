public class DescargarCancion implements IDescargarCancionService {
    @Override
    public String descargar(Usuario usuario) {
        System.out.println("----Verificacion de los datos----");
        System.out.println("Cacion descargada con exito");
        System.out.println("Datos del usuario: "+ usuario.getTipoUsuario()+" / Id del User "+usuario.getId());
        return "Finalizo con exito";

    }
}
