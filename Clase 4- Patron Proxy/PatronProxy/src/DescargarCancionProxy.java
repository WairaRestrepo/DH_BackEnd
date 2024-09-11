public class DescargarCancionProxy  implements IDescargarCancionService{
    private  DescargarCancion descargarCancion;

    public DescargarCancionProxy() {
        descargarCancion = new DescargarCancion();
    }

    @Override
    public String descargar(Usuario usuario) {

        System.out.println("-----------Verificando datos---------");
        System.out.println("El usuario es de tipo: "+ usuario.getTipoUsuario());
        if((usuario.getTipoUsuario().name().equalsIgnoreCase("Premium"))){
                return descargarCancion.descargar(usuario);
        }else{
            return "Regresa cuando sea Premium, por favor ";
        }

    }

}



