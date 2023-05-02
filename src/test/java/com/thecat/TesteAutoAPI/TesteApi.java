package com.thecat.TesteAutoAPI;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TesteApi extends MassaDeDados {

	@BeforeClass
	public static void urlBase() {
		RestAssured.baseURI = "https://api.thecatapi.com/v1/";
	}

	@Test
	public void cadastro() {

		// GIVEN -Dado que - // WHEN - quando estiver com - // THEN - então
		Response response = given().contentType("application/json").body(corpoCadastro).when().post(urlCadastro);

		validacao(response);
	}

	@Test
	public void votacao() {

		// GIVEN -Dado que - // WHEN - quando estiver com - // THEN - entao
		Response response = given().contentType("application/json").header("x-api-key", api_key).body(corpoVotacao)
				.when().post("votes/");

		validacao(response);

		String id = response.jsonPath().getString("id"); // pegando id e gardando em variavel
		votoId = id;
		System.out.println("ID => " + id);
	}

	@Test
	public void deletaVotacao() {
		votacao();
		deletarVoto();
	}


	public void deletarVoto() {

		String url = "https://api.thecatapi.com/v1/votes/:{voto_Id}";

		// GIVEN -Dado que - // WHEN - quando estiver com - // THEN - entao
		Response response = given().contentType("application/json").header("x-api-key", api_key) // passando token
				.pathParam("voto_Id", votoId).when().delete("votes/{voto_Id}");

		validacao(response);
	}


	public void favoritar() {

		String url = "favourites";

		Response response = given().contentType("application/json").header("x-api-key", api_key).body(corpoFavorita)
				.when().post("favourites");

		validacao(response);

		String id = response.jsonPath().getString("id"); // pegando id e gardando em variavel
		favoritoId = id;
		System.out.println("ID => " + id);
	}

	
	public void deletarFavorito() {

		// GIVEN -Dado que - // WHEN - quando estiver com - // THEN - entao
		Response response = given().contentType("application/json").header("x-api-key", api_key) // passando token
				.pathParam("favourite_Id", favoritoId).when().delete(corpoDeletarFav);

		validacao(response);
	}

	@Test
	public void favoritaDesfavorita() {
		favoritar();
		deletarFavorito();
	}

	public void validacao(Response response) {

		response.then().statusCode(200).body("message", containsString("SUCCESS"));
		System.out.println("Retorno da API => " + response.body().asString());
		System.out.println("------------------------");
	}
}
