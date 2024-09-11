public class ControlEnvase extends  AnalistaDeCalidad {

    @Override
    public void validarCalidadDelProducto(Producto producto) {
        if (producto.getEnvasado().equalsIgnoreCase("Sano")|| producto.getEnvasado().equalsIgnoreCase("Casi Sano")){
            if(getSiguienteEnCalidad()!= null){
                getSiguienteEnCalidad().validarCalidadDelProducto(producto);
                System.out.println("Cumple con el crietrio de calidad de Envasado");
            }
        }else{
            System.out.println("No Cumple con el crietrio de calidad de Envasado");
        }
    }
}
