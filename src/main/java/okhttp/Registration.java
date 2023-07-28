package okhttp;

import com.google.gson.Gson;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import dto.RegistrationRequestDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Registration
{
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    Gson gson=new Gson();
    OkHttpClient client=new OkHttpClient();


    @Test
    public void testRegistration() throws IOException {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        RegistrationRequestDTO registrationRequestDTO=RegistrationRequestDTO.builder()
                .username("olegk"+i+"@mail.com")
                .password("$Abcdef"+i)
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(registrationRequestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/registration/usernamepassword")
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
            Assert.assertTrue(response.isSuccessful());
        }
    }
}
