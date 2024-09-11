public class Cuadrado extends Figura{

    private Double lado;

    public Cuadrado(String tipo, Double lado) {
        super(tipo);
        this.lado = lado;
    }

    public Double getLado() {
        return lado;
    }

    @Override
    public String getTipo() {
        return super.getTipo();
    }

    @Override
    public double calcularArea() {
        if(lado>0.0){
            return lado *lado;
        }
        return Double.parseDouble("El valor del lado debe ser mayor que cero");
    }


}
