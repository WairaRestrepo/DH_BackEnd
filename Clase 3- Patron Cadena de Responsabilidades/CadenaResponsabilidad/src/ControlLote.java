public class ControlLote extends  AnalistaDeCalidad{
    @Override
    public void validarCalidadDelProducto(Producto producto) {

        if(producto.getLote()>=1000 && producto.getLote()<=2000){
            if(getSiguienteEnCalidad()!= null){
                getSiguienteEnCalidad().validarCalidadDelProducto(producto);
                System.out.println("Cumple con el crietrio de calidad del peso");
            }
        }else {
            System.out.println("No Cumple con el crietrio de calidad del peso");
        }
    }
}
