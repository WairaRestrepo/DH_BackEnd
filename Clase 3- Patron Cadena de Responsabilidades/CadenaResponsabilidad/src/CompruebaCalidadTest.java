public class CompruebaCalidadTest {

    @Test
    public void analizarProducto(){
        //DADO
        Producto producto= new Producto("Nutella",1015,1400,"sano");
        CompruebaCalidad compruebaCalidad= new CompruebaCalidad();
        //CUANDO
        String respE= "aceptado";
compruebaCalidad.procesaArticulo(producto);
        //ENTONCES
        assertEquals(respE,producto.getStatus());



    }
}
