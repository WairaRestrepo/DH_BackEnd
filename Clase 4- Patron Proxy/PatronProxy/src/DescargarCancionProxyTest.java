import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescargarCancionProxyTest {

    @Test
    void descargar() {
        TipoUser tipoUser = TipoUser.Premium;
        Usuario usuario = new Usuario(1,tipoUser);
        DescargarCancionProxy descargarCancion = new DescargarCancionProxy();
        String respuestaCorrecta = "Finalizo con exito";

        //Cuando
        String respuestaActual = descargarCancion.descargar(usuario);

        //Entonces
        Assertions.assertEquals(respuestaActual,respuestaCorrecta);
    }
}