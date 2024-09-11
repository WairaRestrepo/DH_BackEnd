public class Circulo extends Figura{

    private Double radio;

    public Circulo(String tipo, Double radio) {
        super(tipo);
        this.radio = radio;
    }

    public Double getRadio() {
        return radio;
    }

    @Override
    public String getTipo() {
        return super.getTipo();
    }

    @Override
    public double calcularArea() {
        if(radio>0.0){
            return (3.1416*(radio *radio));
        }
        return Double.parseDouble("El valor del radio debe ser mayor que cero");
    }
}
