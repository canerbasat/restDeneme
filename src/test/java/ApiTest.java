
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.put;
import static io.restassured.RestAssured.when;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class ApiTest {


    @Test
    public void firstTask(){
        RestAssured.baseURI = "https://swapi.co";
        get("/api/stamps/").then()
                .assertThat()
                .statusCode(200)
                .body("", empty());
    }

    @Test
    public void secondTask(){

        String requestBody = "{\r\n" +
                " \"owner\":" +
                " \"title\":\"23\"\r\n" +
                "}";


        RestAssured.baseURI = "https://swapi.co/api/stamps/";
        Response response = null;
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .put(requestBody);

        Assert.assertTrue(response.asString().contains(" { \"error\": \"Field 'owner' is required\" }"));
    }

    @Test
    public void thirdTask(){

        String requestBody = "{\r\n" +
                " \"owner\":\"\",\r\n" +
                " \"title\":\"23\"\r\n" +
                "author:"+
                "}";

        RestAssured.baseURI = "https://swapi.co/api/stamps/";
        Response response = null;
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .put(requestBody);

        Assert.assertTrue(response.asString().contains("{ \"error\": \"Field 'author' is required\" }"));
    }

    @Test
    public void fourthTask(){

        String requestBody = "{\r\n" +
                " \"id\":\"12\",\r\n" +
                " \"owner\":" +
                " \"title\":\"23\"\r\n" +
                "}";


        RestAssured.baseURI = "https://swapi.co/api/stamps/";
        Response response = null;
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .put(requestBody);

        Assert.assertTrue(response.asString()==null);
    }

    @Test
    public void fifthTask(){

        String requestBody = "{\r\n" +
                " \"id\":\"9\",\r\n" +
                " \"owner\":CanerBasat" +
                " \"title\":\"Test\"\r\n" +
                "}";


        RestAssured.baseURI = "https://swapi.co/api/stamps/";
        Response response = null;
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .put(requestBody);

        RestAssured.baseURI = "https://swapi.co";
        get("/api/stamps/9").then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void sixthTask(){

        String requestBody = "{\r\n" +
                " \"id\":1" +
                " \"owner\":John Smith" +
                " \"title\":\"23\"\r\n" +
                "}";


        RestAssured.baseURI = "https://swapi.co/api/stamps/";
        Response response = null;
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .put(requestBody);

        Assert.assertTrue(response.asString()=="Another stamp with similar title and owner\n" +
                "already exists.");
    }
}
