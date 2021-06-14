package br.ce.maquino.simulacoes;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;


import org.junit.Ignore;
import org.junit.Test;

import io.restassured.http.ContentType;

public class SimulacaoPost {

	String uriBase = "http://localhost:8080/api/v1/simulacoes";

	@Test
	public void cadastroCorreto() {
		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 58283185004, "
					+ " \"email\": \"Maisa@teste.com\","
					+ " \"valor\": 1220,"
					+ " \"parcelas\": 4,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(201);
	}

	@Test
	public void cpfVazio() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\":  , " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 25000," 
					+ " \"parcelas\": 45," 
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void cpfComLetras() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 58283185asd  , " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 25000," 
					+ " \"parcelas\": 45," 
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void cpfCom12Digitos() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 415523568514  , " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 25000," 
					+ " \"parcelas\": 45," 
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void cpfCom10Digitos() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 4155235514  , " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 25000," 
					+ " \"parcelas\": 45," 
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void cpfDuplicado() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220," + " \"parcelas\": 4," 
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(409)
			.body("mensagem", containsString("CPF já existente"));
	}

	@Ignore
	public void cpfInvalido() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 111111111111, "
					+ " \"email\": \"Marerty@teste.com\"," 
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 4,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
	}

	@Test
	public void emailInvalido() {

		
		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 72132092052, "
					+ " \"email\": \"Marertyteste.com\","
					+ " \"valor\": 1220,"
					+ " \"parcelas\": 4," 
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void emailInvalido2() {

		
		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 71028037023, "
					+ " \"email\": \"Marer@tyteste\","
					+ " \"valor\": 1220,"
					+ " \"parcelas\": 4," 
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
	}

	@Test
	public void valorMenor1000() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 08489892016, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 999.99," 
					+ " \"parcelas\": 4," 
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
	}

	@Test
	public void valorIgual1000() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 51751761029, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1000.00,"
					+ " \"parcelas\": 4," 
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(201);
	}

	@Test
	public void valorMaior1000() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", "
				+ " \"cpf\": 51751761029, " 
				+ " \"email\": \"Marerty@teste.com\","
				+ " \"valor\": 1000.01," 
				+ " \"parcelas\": 4," 
				+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(201);
	}

	@Test
	public void valorMenor40000() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 72994527073, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 39999.99," 
					+ " \"parcelas\": 4,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(201);
	}

	@Test
	public void valorIgual40000() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 50655714065, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 40000.00,"
					+ " \"parcelas\": 4,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(201);
	}

	@Test
	public void valorMaior40000() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 46150911098, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 40000.01," 
					+ " \"parcelas\": 4,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
	}

	@Test
	public void quantidadeparcela1() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 87732350081, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 25000," 
					+ " \"parcelas\": 1,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
	}

	@Test
	public void quantidadeParcela2() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 31139088092, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 25000," 
					+ " \"parcelas\": 2,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(201);
	}

	@Test
	public void quantidadeParcela3() {
		
		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 25184249036, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 25000," 
					+ " \"parcelas\": 3,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(201);
		
	}

	@Test
	public void quantidadeparcela47() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 31139088092, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 25000," 
					+ " \"parcelas\": 47,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
		.statusCode(201);
	}

	@Test
	public void quantidadeparcela48() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 29951651089, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 25000," 
					+ " \"parcelas\": 48,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(201);		
	}

	@Test
	public void quantidadeparcela49() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 47542345028, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 25000," 
					+ " \"parcelas\": 49,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
		}

	@Test
	public void parcelaLetra() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 38209387022, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 25000,"
					+ " \"parcelas\": dois,"
					+ " \"seguro\": true }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(400);
	}
}
