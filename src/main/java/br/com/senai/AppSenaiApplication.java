package br.com.senai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title="Projeto API rest SENAI", version= "2.0.0", description="Projeto Instrutor 4.0"))
@SpringBootApplication
public class AppSenaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppSenaiApplication.class, args);
	}

}
