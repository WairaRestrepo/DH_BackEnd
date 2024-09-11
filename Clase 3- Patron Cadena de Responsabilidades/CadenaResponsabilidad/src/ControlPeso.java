public class ControlPeso extends  AnalistaDeCalidad {
    @Override
    public void validarCalidadDelProducto(Producto producto) {

        if (producto.getPeso()>=1200 && producto.getPeso()<=1300){
            
            if(getSiguienteEnCalidad()!= null){
                getSiguienteEnCalidad().validarCalidadDelProducto(producto);
                System.out.println("Cumple con el crietrio de calidad del peso");
            }
        }else {
            System.out.println("No Cumple con el crietrio de calidad del peso");
        }


        
    }
}
