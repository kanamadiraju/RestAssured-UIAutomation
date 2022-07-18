package com.trello.API.testcases;
import static io.restassured.RestAssured.given;

import java.util.Map;

import static io.restassured.RestAssured.*;


import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.trello.qa.Analyzer.CustomListener;
import com.trello.qa.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

@Listeners(CustomListener.class)
public class RestAssuredAPITestcases extends TestBase {

	

	
	@Test (enabled=false)
	public void getAllBoardsTest() {

		Assert.assertEquals(getAllBoards(), 200);

	}
	
	@Test (enabled=false)
	public void createNewBoardTest() {


		JsonPath newBoard = createNewBoard(prop.getProperty("boardName")); 
		System.out.println("Board Name: "+newBoard.get("id"));
		Assert.assertEquals(newBoard.get("name"), prop.getProperty("boardName"), "Not matching");
		
		deleteBoard(newBoard.get("id"));


	}
	@Test (enabled=false)
	public void EndtoEndTest() {


		JsonPath newBoard= createNewBoard(prop.getProperty("boardName"));
		JsonPath newList1 = createList(newBoard.get("id"), prop.getProperty("listName1"));
		JsonPath newList2 = createList(newBoard.get("id"), prop.getProperty("listName2"));
		JsonPath newCard1 = createCardonList(newList1.get("id"), prop.getProperty("cardName1"));
		JsonPath newCard2 = createCardonList(newList1.get("id"), prop.getProperty("cardName2"));
		JsonPath newCard3 = createCardonList(newList1.get("id"), prop.getProperty("cardName3"));
		
		moveAllCardsInList(newList1.get("id"), newList2.get("id"), newBoard.get("id"));
		
		deleteBoard(newBoard.get("id"));
		


	}
	
	@Test (enabled=false)
	public void createCardTest() {


		JsonPath newBoard= createNewBoard(prop.getProperty("boardName"));
		JsonPath newList1 = createList(newBoard.get("id"), prop.getProperty("listName1"));
		JsonPath newList2 = createList(newBoard.get("id"), prop.getProperty("listName2"));
		JsonPath newCard1 = createCardonList(newList1.get("id"), prop.getProperty("cardName1"));
		JsonPath newCard2 = createCardonList(newList1.get("id"), prop.getProperty("cardName2"));
		JsonPath newCard3 = createCardonList(newList1.get("id"), prop.getProperty("cardName3"));
		Assert.assertEquals(newCard1.get("name"), prop.getProperty("cardName1"), "Not matching");
		Assert.assertEquals(newCard2.get("name"), prop.getProperty("cardName2"), "Not matching");
		Assert.assertEquals(newCard3.get("name"), prop.getProperty("cardName3"), "Not matching");
		
		deleteBoard(newBoard.get("id"));


	}
	
	@Test (enabled=false)
	public void archiveAllCardsTest() {

		JsonPath newBoard= createNewBoard(prop.getProperty("boardName"));
		JsonPath newList1 = createList(newBoard.get("id"), prop.getProperty("listName1"));
		JsonPath newList2 = createList(newBoard.get("id"), prop.getProperty("listName2"));
		JsonPath newCard1 = createCardonList(newList1.get("id"), prop.getProperty("cardName1"));
		JsonPath newCard2 = createCardonList(newList1.get("id"), prop.getProperty("cardName2"));
		JsonPath newCard3 = createCardonList(newList1.get("id"), prop.getProperty("cardName3"));
		
		moveAllCardsInList(newList1.get("id"), newList2.get("id"), newBoard.get("id"));

		archiveAllCardsOnList(newList1.get("id"));
		
		deleteBoard(newBoard.get("id"));


	}
	
	@Test 
	public void updateCardTest() {


		JsonPath newBoard= createNewBoard(prop.getProperty("boardName"));
		JsonPath newList1 = createList(newBoard.get("id"), prop.getProperty("listName1"));
		JsonPath newList2 = createList(newBoard.get("id"), prop.getProperty("listName2"));
		JsonPath newCard1 = createCardonList(newList1.get("id"), prop.getProperty("cardName1"));
		JsonPath newCard2 = createCardonList(newList1.get("id"), prop.getProperty("cardName2"));
		
		updateCard(newList1.get("id"), newBoard.get("id"), "Test Strategy document creation", "Create document by sprint end and upload the same in confluence", newCard1.get("id"));

		deleteBoard(newBoard.get("id"));


	}
	
public void updateCard(Object listID, Object boardID, String updateCardName, String updateCardDesc, Object cardID) {
		
		RestAssured.baseURI = prop.getProperty("apiURL");
		
		JSONObject request = new JSONObject();
		request.put("key", prop.getProperty("key"));
		request.put("token", prop.getProperty("token"));
		request.put("name", updateCardName);
		request.put("desc", updateCardDesc);
		request.put("idList", listID);
		request.put("idBoard", boardID);
		
		given().
				header("Content-Type", "application/json").
				body(request.toJSONString()).
			when().
				put("1/cards/"+cardID+"").then().
				log().body().assertThat().statusCode(200);
		
	}
	


	
	
	
public void archiveAllCardsOnList(Object listID) {

        
		RestAssured.baseURI = prop.getProperty("apiURL");
		
		JSONObject request = new JSONObject();
		request.put("key", prop.getProperty("key"));
		request.put("token", prop.getProperty("token"));
		request.put("value", "true");
		
		given().
				header("Content-Type", "application/json").
				body(request.toJSONString()).
		when().
				post("/1/lists/"+listID+"/archiveAllCards").
		then().log().body().assertThat().statusCode(200);
				
	   
	}
	
	
public void archiveList(Object listID) {

                
		RestAssured.baseURI = prop.getProperty("apiURL");
		
		JSONObject request = new JSONObject();
		request.put("key", prop.getProperty("key"));
		request.put("token", prop.getProperty("token"));
		request.put("value", "true");
		
		given().
				header("Content-Type", "application/json").
				body(request.toJSONString()).
		when().
				put("/1/lists/"+listID+"/closed").
		then().log().body().assertThat().statusCode(200);
		
		
	
        
	}
	
	
	
public JsonPath createNewBoard(String boardName) {
		
		RestAssured.baseURI = prop.getProperty("apiURL");
		
		JSONObject request = new JSONObject();
		request.put("key", prop.getProperty("key"));
		request.put("token", prop.getProperty("token"));
		request.put("name", prop.getProperty("boardName"));
		
		JsonPath jsonPathEvaluator = given().
				contentType("application/json").
				body(request.toJSONString()).
			when().
				post("/1/boards/").jsonPath();
		


		return jsonPathEvaluator;

		
			
	}
	
	
	
	
public int getAllBoards() {
		
		
		RestAssured.baseURI = prop.getProperty("apiURL");
		
		JSONObject request = new JSONObject();
		request.put("key", prop.getProperty("key"));
		request.put("token", prop.getProperty("token"));
		
		int statusCode = given().
				header("Content-Type", "application/json").
				body(request.toJSONString()).
		when().
				get("1/members/me/boards").getStatusCode();
		
		
		return statusCode;
		

	}
	
	
	
public JsonPath createCardonList(Object object, String cardName) {
		
		RestAssured.baseURI = prop.getProperty("apiURL");
		
		JSONObject request = new JSONObject();
		request.put("key", prop.getProperty("key"));
		request.put("token", prop.getProperty("token"));
		request.put("name", cardName);
		request.put("idList", object);
		
		JsonPath jsonPathEvaluator= given().
				header("Content-Type", "application/json").
				body(request.toJSONString()).
			when().
				post("1/cards").jsonPath();


		return jsonPathEvaluator;
		
		
	}


public JsonPath getAllLists(Object object) {
	
	RestAssured.baseURI = prop.getProperty("apiURL");
	
	JSONObject request = new JSONObject();
	request.put("key", prop.getProperty("key"));
	request.put("token", prop.getProperty("token"));

	
	JsonPath jsonPathEvaluator= given().
			header("Content-Type", "application/json").
			body(request.toJSONString()).
		when().
			get("/1/boards/"+object+"/lists").jsonPath();
	
	


	return jsonPathEvaluator;
	
	
}

public void moveAllCardsInList(Object srcListID, Object destListID, Object boardID) {
	
	RestAssured.baseURI = prop.getProperty("apiURL");
	
	JSONObject request = new JSONObject();
	request.put("key", prop.getProperty("key"));
	request.put("token", prop.getProperty("token"));
    request.put("idList", destListID);				//dest list id
    request.put("idBoard", boardID);	
	
	   given().
			contentType("application/json").
			body(request.toJSONString()).
	   when().
	   		post("/1/lists/"+srcListID+"/moveAllCards").
	   then().
		log().body().assertThat().statusCode(200);

	
}
	

	
	
	
public JsonPath createList(Object object, String listName) {
		RestAssured.baseURI = prop.getProperty("apiURL");
		
		JSONObject request = new JSONObject();
		request.put("key", prop.getProperty("key"));
		request.put("token", prop.getProperty("token"));
		request.put("name", listName);
		
		JsonPath jsonPathEvaluator= given().
				header("Content-Type", "application/json").
				body(request.toJSONString()).
			when().
				post("1/boards/"+object+"/lists").jsonPath();


		 return jsonPathEvaluator;
		
	}
	
public void deleteBoard(Object object) {
		
		RestAssured.baseURI = prop.getProperty("apiURL");
		
		JSONObject request = new JSONObject();
		request.put("key", prop.getProperty("key"));
		request.put("token", prop.getProperty("token"));
		
		   given().
				contentType("application/json").
				body(request.toJSONString()).
			when().
				delete("1/boards/"+object+"").
			then().
			log().body().assertThat().statusCode(200);

		
			
	}
	
	
	
}
