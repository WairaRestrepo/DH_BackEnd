import java.util.Date;

public class FachadaHotelVuelo implements iFachadaHotelVuelo {

    //Que se debe de hacer para tener una conexion entre las diferetes apis..
    //Establecer una relacion de asociacion entre las apis
        private ApiVuelo apiVuelo;
        private ApiHotel apiHotel;


    public FachadaHotelVuelo() {
        apiVuelo = new ApiVuelo();
        apiHotel = new ApiHotel();
    }

    @Override
    public void busqueda(String fechaSalida, String fechaRegreso, String origen, String destino) {
        apiHotel.buscarHotel(fechaSalida,fechaRegreso);
        apiVuelo.buscarVuelo(fechaSalida,fechaRegreso,origen,destino);

    }


}
