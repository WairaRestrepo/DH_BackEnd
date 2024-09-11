import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiguraTest {
    @Test
public void calcularAreaDeFigura(){

        Cuadrado cuadrado1 =  new Cuadrado("Cuadrado",8.0);
        Circulo  circulo1 = new Circulo("circulo",2.0);
        double Cuadrado1ValorArea = cuadrado1.calcularArea();
        double Circulo1ValorArea = circulo1.calcularArea();


        assertEquals(64.0,cuadrado1.calcularArea());
        assertEquals(12.5664,circulo1.calcularArea());

    }
    //DADO


    //CUANDO

}
