public abstract class AnalistaDeCalidad {

    private AnalistaDeCalidad siguienteEnCalidad;

    public AnalistaDeCalidad getSiguienteEnCalidad() {
        return siguienteEnCalidad;
    }

    public void setSiguienteEnCalidad(AnalistaDeCalidad siguienteEnCalidad) {
        this.siguienteEnCalidad = siguienteEnCalidad;
    }

    public abstract void validarCalidadDelProducto(Producto producto );





}
