package br.ce.maquino.simulacoes;

import static io.restassured.RestAssured.given;

import org.junit.Test;


public class SimulacaoGet {

	String uriBase = "http://localhost:8080/api/v1/simulacoes/";

	@Test
	public void consutaCorreto() {
				
		given()
			.relaxedHTTPSValidation()
		.when()
			.get(uriBase)
		.then()
			.statusCode(200);
			}

	@Test
	public void idNaoEncontrado() {

		given()
			.relaxedHTTPSValidation()
		.when()
			.get(uriBase)
		.then()
			.statusCode(204);
	}	
}
