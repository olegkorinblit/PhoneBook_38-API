package okhttp;

import com.google.gson.Gson;
import dto.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddContact {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MTEwMDM1NCwiaWF0IjoxNjkwNTAwMzU0fQ.mb7blnGJMfNSgFmN364XASI4F4AIlZu2aOR8WnYTsp4";


@Test
    public void AddContact() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .build();

    int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        AddContactRequestDTO addContactRequestDTO = AddContactRequestDTO.builder()
                .id("")
                .name("gjghgghm"+i)
                .lastName("jjk;m.m,.nbb"+i)
                .email("gjghgghm@gmail.com"+i)
                .phone("+4654764586987"+i)
                .address("gfnfg 586987"+i)
                .description("Contactmcmgcmbmc"+i)
                .build();
//         "id": "string",
//         "name": "string",
//         "lastName": "string",
//         "email": "string",
//         "phone": "61776954477926",
//         "address": "string",
//         "description": "string"
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