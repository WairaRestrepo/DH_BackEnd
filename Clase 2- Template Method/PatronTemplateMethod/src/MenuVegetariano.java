public class MenuVegetariano extends Menu{

    private  Boolean especias;
    private  Boolean salsaVegetariana;
    private int cantidadDeSalsas;



    public MenuVegetariano(String nombreMenu, double precioBase, Boolean especias, Boolean salsaVegetariana, int cantidadDeSalsas) {
        super(nombreMenu, precioBase);
        this.especias = especias;
        this.salsaVegetariana = salsaVegetariana;
        this.cantidadDeSalsas = cantidadDeSalsas;
    }
    public int getCantidadDeSalsas() {
        return cantidadDeSalsas;
    }
    public Boolean getEspecias() {
        return especias;
    }

    public Boolean getSalsaVegetariana() {
        return salsaVegetariana;
    }

    @Override
    public double calculoDePrecioVenta() {
        if(getEspecias() == true && getSalsaVegetariana() == true){
            return getPrecioBase() +0.01+(getCantidadDeSalsas()*2);
        } else if (getEspecias() == true && getSalsaVegetariana() == false) {
            return getPrecioBase() +0.01;
        } else if (getSalsaVegetariana() == true && getEspecias() == false ) {
            return getPrecioBase()+(cantidadDeSalsas*2);
        }
        return getPrecioBase();
    }

    @Override
    public String toString() {
        return "El menu preparado y entregado fue: \n"+
                "Nombre del menu: "+getNombreMenu()+"\n"+
                "Conntiene Salsas: " + getSalsaVegetariana()+"\n"+
                "Contiene Especias: "+ getEspecias()+"\n"+
                "Numero de salsas que contiene: "+getCantidadDeSalsas()+"\n"+
                "El valor total  a pagar por el menu es:  "+ calculoDePrecioVenta();

    }
}
