public  class CompruebaCalidad {

private AnalistaDeCalidad inicial;

//Ya tenemos listos los eslabones
    public CompruebaCalidad() {
        inicial = new ControlPeso() ;
        AnalistaDeCalidad lote = new ControlLote();
        AnalistaDeCalidad envase =new ControlEnvase();
        AnalistaDeCalidad aceptado = new ControlAceptado();
        AnalistaDeCalidad peso = new ControlPeso();

        //Necesitamos unir estos eslabones
        inicial.setSiguienteEnCalidad(peso);
        peso.setSiguienteEnCalidad(envase);
        envase.setSiguienteEnCalidad(aceptado);
    }

    public void procesaArticulo(Producto articulo){
        inicial.CompruebaCalidad(producto);

    }
}
