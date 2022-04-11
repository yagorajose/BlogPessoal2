package org.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration //define essa classe como fonte das definições de beans
public class SwaggerConfig {
	
	@Bean //diz ao spring para criar esse objeto e deixa disponível para as outras classes poderem utilizá-lo como dependência
	public OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Projeto Blog Pessoal")	//informações da API	
				.description("Projeto Blog Pessoal - Generation Brasil") 
				.version("v0.0.1")
			.license(new License() //informações da licença da API
				.name("Generation Brasil")	
				.url("https://brazil.generation.org/"))
			.contact(new Contact() //informações de contato da pessoa desenvolvedora
				.name("Conteudo Generation")
				.url("https://github.com/conteudoGeneration")
				.email("conteudogeneration@gmail.com")))
			.externalDocs(new ExternalDocumentation()
				.description("Github")
				.url("https://github.com/conteudoGeneration/"));
			}
	


	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser(){
		
		return openApi -> { //cria o objeto OpenAPI e gera a documentação no Swagger
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> { //lê todos os recursos pelo método getPaths. cria outro looping que identifica qual método http que está em execução através do readOperations
			
			ApiResponses apiResponses = operation.getResponses(); //cria o  objeto apiResponses que receba as respostas dos endpoints pelo getResponses	
				
			apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
			apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
			apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
			apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!")); //respostas dos endpoints criadas pelo addApiResponse
			apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
			apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
			apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
		}));
	};		
			
}			
			
			
			
			
			
				
	private ApiResponse createApiResponse(String message) { //adiciona uma mensagem em cada resposta do HTTP
		return new ApiResponse().description(message);	
	}
	
}

