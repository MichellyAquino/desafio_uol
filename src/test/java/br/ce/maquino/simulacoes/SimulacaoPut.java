package br.ce.maquino.simulacoes;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class SimulacaoPut {

	String uriBase = "http://localhost:8080/api/v1/simulacoes/";

	String cpf = "58283185004";

	@Test
	public void alteracaoCompleta() {
		
		given()
			.relaxedHTTPSValidation()
			.log()
			.all()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Jose Augusto\", "
					+ " \"cpf\": 58283185004, "
					+ " \"email\": \"jose@teste.com\","
					+ " \"valor\": 1220,"
					+ " \"parcelas\": 14,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(200)
			.body("nome", Matchers.is("Jose Augusto"))
			.body("cpf", Matchers.is("58283185004"))
			.body("email", Matchers.is("jose@teste.com"))
			.body("valor", Matchers.is(1220.0F))
			.body("parcelas", Matchers.is(14))
			.body("seguro", Matchers.is(true));
	}

	@Test
	public void cpfVazio() {

		given()
			.relaxedHTTPSValidation()
			.log()
			.all()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\":  , " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 45," 
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void cpfComLetras() {

		given()
			.relaxedHTTPSValidation()
			.log()
			.all()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 58283185asd  , " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 45," 
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void cpfCom12Digitos() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 415523568514 , " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 45," 
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void cpfCom10Digitos() {
		
		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 4155235514  , " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 45," 
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(400);
	}
	
	
	@Test
	public void cpfNaoEncontrado() {

		String cpfs = "43845734051";

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220,"
					+ " \"parcelas\": 4," 
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpfs)
		.then()
			.statusCode(404)
			.body("mensagem", containsString("CPF não encontrado"));
	}

	@Test
	public void cpfInvalido() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Mariana Pedro\", " 
					+ " \"cpf\": 111111111111, "
					+ " \"email\": \"Marerty@teste.com\"," 
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 4,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(400);
	}

	@Test
	public void emailInvalido() {
		
		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Marina Pedro\", " 
					+ " \"cpf\": 58283185004, "
					+ " \"email\": \"marina.com\","
					+ " \"valor\": 1220,"
					+ " \"parcelas\": 4," 
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(400);
	}
	
	@Test
	public void emailInvalido2() {
		
		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, "
					+ " \"email\": \"Marer@tyteste\","
					+ " \"valor\": 1220,"
					+ " \"parcelas\": 4," 
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(400);
	}

	@Test
	public void valorMenor1000() {
		
		 given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 452.99," 
					+ " \"parcelas\": 4," 
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
		.statusCode(200)
		.body("valor", Matchers.is("452.99"));
		}

	@Test
	public void valorIgual1000() {
		 given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 58283185004, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1000.00,"
					+ " \"parcelas\": 4," 
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.body("valor", Matchers.is(1000.0F))
			.statusCode(200);
	}

	@Test
	public void valorMaior1000() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", "
				+ " \"cpf\": 58283185004, " 
				+ " \"email\": \"Marerty@teste.com\","
				+ " \"valor\": 1041.81," 
				+ " \"parcelas\": 4," 
				+ " \"seguro\": true }")
		.when()
		.	put(uriBase + cpf)
		.then()
			.body("valor", Matchers.is(1041.81F))
			.statusCode(200);
	}

	@Test
	public void valorMenor40000() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 39458.99," 
					+ " \"parcelas\": 4,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.body("valor", Matchers.is(39458.99F))
			.statusCode(200);
	}

	@Test
	public void valorIgual40000() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 58283185004, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 40000.00,"
					+ " \"parcelas\": 4,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.body("valor", Matchers.is(40000.00F))
			.statusCode(200);
	}

	@Test
	public void valorMaior40000() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 40000.01," 
					+ " \"parcelas\": 4,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.body("valor", Matchers.is(40000.01F))
			.statusCode(400);
	}

	@Test
	public void quantidadeparcela1() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 1,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(400);
	}

	@Test
	public void quantidadeParcela2() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 2,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(200)
			.body("parcelas", Matchers.is(2));
	}

	@Test
	public void quantidadeParcela3() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 3,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(200)
			.body("parcelas", Matchers.is(3));
		
	}

	@Test
	public void quantidadeparcela47() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 47,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
		.statusCode(200)
		.body("parcelas", Matchers.is(47));
	
	}

	@Test
	public void quantidadeparcela48() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 48,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(200)
			.body("parcelas", Matchers.is(48));
		
	}

	@Test	
	public void quantidadeparcela49() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", " 
					+ " \"cpf\": 58283185004, "
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220," 
					+ " \"parcelas\": 49,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(400);
		}


	@Test
	public void parcelaLetra() {

		given()
			.relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.log()
			.all()
			.body("{ \"nome\": \"Maisa Pedro\", "
					+ " \"cpf\": 58283185004, " 
					+ " \"email\": \"Marerty@teste.com\","
					+ " \"valor\": 1220,"
					+ " \"parcelas\": dois,"
					+ " \"seguro\": true }")
		.when()
			.put(uriBase + cpf)
		.then()
			.statusCode(400);
	}
}
