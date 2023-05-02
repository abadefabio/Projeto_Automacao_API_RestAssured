package com.thecat.TesteAutoAPI;

public class MassaDeDados {

	String votoId;
	String favoritoId;
	String api_key = "live_UkNZ0T8oo11htqZfBPjiNldmBGz9lULqb9kEEtpZEmq1QY12va4BVXybbucgFetm";
	
	String urlCadastro = "user/passwordlesssignup";
	String corpoCadastro = "{\"email\": \"abadefabio8@gmail.com\", \"appDescription\": \"teste the cat api\"}";
	String corpoVotacao = "{\"image_id\": \"6u4\", \"sub_id\": \"demo-35286\", \"value\": \"1\"}";
	String corpoFavorita = "{\"image_id\": \"bb4\", \"sub_id\": \"demo-958b6d\"}";
	String corpoDeletarFav = "favourites/{favourite_Id}";
}
