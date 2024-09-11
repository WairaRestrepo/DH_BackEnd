public class MenuClasico extends Menu {


    public MenuClasico(String nombreMenu, double precioBase) {
        super(nombreMenu, precioBase);

    }

    @Override
    public double calculoDePrecioVenta() {
        return getPrecioBase();
    }

    @Override
    public String toString() {
        return "El menu preparado y entregado fue: \n"+
                "Nombre del menu: "+getNombreMenu()+"\n"+
                "El valor total  a pagar por el menu es:  "+ calculoDePrecioVenta();
    }
}
