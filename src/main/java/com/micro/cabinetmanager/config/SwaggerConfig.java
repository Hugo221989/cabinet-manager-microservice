package com.micro.cabinetmanager.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.common.base.Predicates;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
			Arrays.asList("application/json"));

	ModelRef errorModel = new ModelRef("ErrorResponse");

	List<ResponseMessage> responseMessages = Arrays.asList(
			new ResponseMessageBuilder().code(200).message("OK - Solicitud correcta").build(),
			new ResponseMessageBuilder().code(500).message("Error del Servidor").build(),
			new ResponseMessageBuilder().code(400).message("Solicitud incorrecta - Uso incorrecto de la API").build(),
			new ResponseMessageBuilder().code(401).message("Autenticación nula o no válida").build(),
			new ResponseMessageBuilder().code(403).message("Acceso no permitido").build(),
			new ResponseMessageBuilder().code(404).message("Recurso solicitado no disponible").build());

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.micro.cabinetmanager"))
				.paths(Predicates.not(PathSelectors.regex("/error.*"))).paths(PathSelectors.regex("/.*")).build()
				.apiInfo(apiInfo()).consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.globalResponseMessage(RequestMethod.GET, responseMessages)
				.globalResponseMessage(RequestMethod.PUT, responseMessages)
				.globalResponseMessage(RequestMethod.POST, responseMessages)
				.globalResponseMessage(RequestMethod.DELETE, responseMessages);
	}

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Información de las reuniones del alumno del gabinete").description(
				"Esta API define el conjunto de especificaciones de la capa de servicios de los alumnos del gabinete. "
				+ "El contenido actual, propone un conjunto de Servicios Web, con métodos de integración que pretende dar respuesta a las necesidades de las "
				+ "plataformas de servicios que necesiten integrarse.")
				.contact(new Contact("Hugo Onetto", "http://www.gabinetepaula.es", null)).version("1.0.0").build();
	}

}