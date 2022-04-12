package APITests.RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import models.LoginModel;
import models.PatchModels;
import models.RegisterModel;
import org.testng.annotations.Test;
import utilities.GenerateFakeMessage;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public class RestAssuredTests {
    @Test
    public void checkResponseDeleteCodeTest() {
        RestAssured
                .given()
                .log()
                .all()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void checkFieldsInGetResponseTest() {
        RestAssured
                .given()
                .log()
                .all()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("total", equalTo(12))
                .body("per_page", equalTo(6))
                .body("total_pages", instanceOf(Integer.class))
                .body("data[0].id", instanceOf(Integer.class))
                .body("data[0].email", equalTo("george.bluth@reqres.in"))
                .body("data[1].first_name", equalTo("Janet"));
    }

    @Test
    public void checkBodyGetTest() {
        JsonPath expectedJson = new JsonPath(new File("src/test/resources/responce.json"));
        RestAssured
                .given()
                .log()
                .all()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .log()
                .all()
                .body("", equalTo(expectedJson.getMap("")));
    }

    @Test
    public void checkPatchTest() {
        PatchModels patchModels = new PatchModels();
        patchModels.setName(GenerateFakeMessage.getFirstName());
        patchModels.setJob(GenerateFakeMessage.getJob());
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .and()
                .body(patchModels)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void checkPostRegistrationSuccessTest() {
        JsonPath expectedJson = new JsonPath(new File("src/test/resources/registration.json"));
        RegisterModel registerModel = new RegisterModel();
        registerModel.setEmail("eve.holt@reqres.in");
        registerModel.setPassword("pistol");
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(registerModel)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("", equalTo(expectedJson.getMap("")));
    }

    @Test
    public void checkPostRegistrationUnSuccessTest() {
        RegisterModel registerModel = new RegisterModel();
        registerModel.setEmail("eve.holt@reqres.in");
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(registerModel)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log()
                .all()
                .statusCode(400);
    }

    @Test
    public void checkPostLoginSuccessTest() {
        LoginModel loginModel = new LoginModel();
        loginModel.setEmail("eve.holt@reqres.in");
        loginModel.setPassword("cityslicka");
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .and()
                .body(loginModel)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("token", instanceOf(String.class));


    }

    @Test
    public void checkPostLoginUnSuccessTest() {
        LoginModel loginModel = new LoginModel();
        loginModel.setEmail(GenerateFakeMessage.getEmail());
        ;
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .and()
                .body(loginModel)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log()
                .all()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }
}
