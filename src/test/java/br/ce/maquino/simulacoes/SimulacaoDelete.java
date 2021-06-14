package br.ce.maquino.simulacoes;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class SimulacaoDelete {

	String uriBase = "http://localhost:8080/api/v1/simulacoes/";
	
	@Test
	public void deletarCorreto() {
		
		int id = 11;
		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
		.when()
			.delete(uriBase + id)
		.then()
			.statusCode(204);
}

	@Test
	public void idNaoEncontrado() {

		int id = 15284;

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
		.when()
			.delete(uriBase + id)
		.then()
			.statusCode(404)
			.body("mensagem", Matchers.is("Simulação não encontrada"));
	}
}
