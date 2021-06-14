package br.ce.maquino.simulacoes;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class SimulacaoGetCpf {

	String uriBase = "http://localhost:8080/api/v1/simulacoes/";

	@Test
	public void consutaCorreto() {
		
		String cpf = "58283185004";
		
		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
		.when()
			.get(uriBase + cpf)
		.then()
			.statusCode(200)
			.body("id", Matchers.is(16))
			.body("nome", Matchers.is("Maisa Pedro"))
			.body("cpf", Matchers.is("58283185004"))
			.body("email", Matchers.is("Marerty@teste.com"))
			.body("valor", Matchers.is(1220.0F))
			.body("parcelas", Matchers.is(48))
			.body("seguro", Matchers.is(true));;
}

	@Test
	public void idNaoEncontrado() {

		String cpf = "60646364081";

		given()
			.relaxedHTTPSValidation()
		.when()
			.get(uriBase + cpf)
		.then()
			.statusCode(404)
			.body("mensagem", Matchers.is("Simulação não encontrada"));
	}
}
