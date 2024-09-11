public class ControlAceptado extends AnalistaDeCalidad{
    
    @Override

     public void compruebaCalidad(Producto producto) {
        System.out.println("producto aceptado");
    producto.setStatus("aceptado");
    }
}