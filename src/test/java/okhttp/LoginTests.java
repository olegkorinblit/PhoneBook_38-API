package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
Gson gson=new Gson();
OkHttpClient client=new OkHttpClient();


    @Test
    public void loginPositive() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("hw@hw")
                .password("Oleg123$")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()){

            AuthResponseDTO responseDTO = gson.fromJson(response.body().string(), AuthResponseDTO.class);
            System.out.println(responseDTO.getToken());
            System.out.println("Response code is: " + response.code());
            Assert.assertTrue(response.isSuccessful());

        }else{
            System.out.println("Response code is: " + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
            System.out.println("ERROR: Test LOgin POsitive:Failed");
            Assert.assertTrue(response.isSuccessful());
        }
    }
}
//eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiaHdAaHciLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MTI1MzA4OCwiaWF0IjoxNjkwNjUzMDg4fQ.4-cZYNaA6enWVD73TVVpeQx-DW55l5om2fi6ICnQeP4
