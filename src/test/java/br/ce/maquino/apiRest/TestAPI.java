package br.ce.maquino.apiRest;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

public class TestAPI {

	 String uriBase = "http://localhost:8080/api/v1/restricoes/";

	@Test
	public void Teste_200() {

		List<String> cpf = new ArrayList<String>();
		String cpf1 = new String("97093236014");
		cpf.add(cpf1);

		String cpf2 = new String("60094146012");
		cpf.add(cpf2);

		String cpf3 = new String("84809766080");
		cpf.add(cpf3);

		String cpf4 = new String("62648716050");
		cpf.add(cpf4);

		String cpf5 = new String("26276298085");
		cpf.add(cpf5);

		String cpf6 = new String("01317496094");
		cpf.add(cpf6);

		String cpf7 = new String("55856777050");
		cpf.add(cpf7);

		String cpf8 = new String("19626829001");
		cpf.add(cpf8);

		String cpf9 = new String("24094592008");
		cpf.add(cpf9);

		String cpf10 = new String("58063164083");
		cpf.add(cpf10);


		for (String x : cpf) {
			given().
				relaxedHTTPSValidation()
			.when()
				.get(uriBase + x)
			.then()
				.log()
				.all()
				.body("mensagem", Matchers.is("O CPF " + x + " possue restrição"))
				.statusCode(200);
		}
	}
	
	  @Test public void Teste_204() {
	  
	  List<String> cpf = new ArrayList<String>();
	  
	  String cpf1 = new String("12345678912");
	  cpf.add(cpf1);
	  
	  String cpf2 = new String("658.908.050-02");
	  cpf.add(cpf2);
	  
	  String cpf3 = new String("73139041004");
	  cpf.add(cpf3);
	  
	  String cpf4 = new String("1425trfg745");
	  cpf.add(cpf4);
	  
	  String cpf5 = new String("ascvdfghjuyt");
	  cpf.add(cpf5);
	  
	  String cpf6 = new String("0158*-+.5685");
	  cpf.add(cpf6);
	  
	  String cpf7 = new String("125452"); 
	  cpf.add(cpf7);
	  
	  String cpf8 = new String("1254856235852"); 
	  cpf.add(cpf8);
	  
	  for (String x : cpf) {
		  given() 
		  	.relaxedHTTPSValidation() 
		  .when() 
		  	.get(uriBase + x) 
		  .then()
		  	.statusCode(204); 
		  }
	  } 
}
