package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RegistrationTestsRestAssured implements Helper {

    String endpoint = "user/registration/usernamepassword";

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = PATH;
    }

    @Test
    public void RegistrationPositive(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("oleg" + i + "@mail.com")
                .password("$Abcdef1"+i)
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);

        System.out.println(responseDTO.getToken());
    }
    @Test
    public void RegistrationNegativeWrongEmail() {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("oleg" + i + "mail.com")
                .password("$Abcdef1"+i)
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(400)
                .extract()
                .as(AuthResponseDTO.class);

        System.out.println(responseDTO.getToken());

    }
    @Test
    public void RegistrationNegativeRegistrationExist() {
        String email = "hw@hw";
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username(email)
                .password("Oleg123$")
                .build();

         AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(409)
                .extract()
                .as(AuthResponseDTO.class);

        System.out.println(responseDTO.getToken());

    }
}