public abstract class Menu {

    private  String nombreMenu;
    private double precioBase;

    public Menu(String nombreMenu, double precioBase) {
        this.nombreMenu = nombreMenu;
        this.precioBase = precioBase;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }



    public double getPrecioBase() {
        return precioBase;
    }



    public abstract double calculoDePrecioVenta();


}
