public abstract class  Figura {

    private String tipo;

    public Figura(String tipo) {
        this.tipo = tipo;
    }

    public abstract double calcularArea();

    public String getTipo() {
        return tipo;
    }
}
