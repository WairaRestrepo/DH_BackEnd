public class MenuInfantil extends Menu {
    private int numeroDeJuguete;

    public MenuInfantil(String nombreMenu, double precioBase, int numeroDeJuguete) {
        super(nombreMenu, precioBase);
        this.numeroDeJuguete = numeroDeJuguete;
    }


    public void setNumeroDeJuguete(int numeroDeJuguete) {
        this.numeroDeJuguete = numeroDeJuguete;
    }

    public int getNumeroDeJuguete() {
        return numeroDeJuguete;
    }

    @Override
    public double calculoDePrecioVenta() {
        if (getPrecioBase()!= 0){
            return getPrecioBase() +(numeroDeJuguete*3) ;
        }
        return getPrecioBase();

    }

    @Override
    public String toString() {
        return "El menu preparado y entregado fue: \n"+
                "Nombre del menu: "+getNombreMenu()+"\n"+
                "Numero de juguetes que Contiene : " + getNumeroDeJuguete()+"\n"+
                "El valor total  a pagar por el menu es:  "+ calculoDePrecioVenta();
    }
}
