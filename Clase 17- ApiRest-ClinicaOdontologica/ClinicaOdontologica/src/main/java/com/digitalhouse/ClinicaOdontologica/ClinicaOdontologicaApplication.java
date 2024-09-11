package com.digitalhouse.ClinicaOdontologica;

import com.digitalhouse.ClinicaOdontologica.Repository.BDH2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

	/*@PostConstruct		// funciona despues de que la aplicacion fue creada y permite inicialiar cosas
	public void inicializarBD(){
		BDH2.sqlCrearTabla();
	}*/

	//despues de configurar devtools el postconstructor dejo de funcionar, pero el CommandLiner se ejecuta despues de que la app ha sido cargada
	//asegura que el codigo se ejecute cada vez que la aplicacion arranca por el devtools
	@Bean
	public CommandLineRunner inicializar(){
		return args -> {
			BDH2.sqlCrearTabla();
		};
	}

}
