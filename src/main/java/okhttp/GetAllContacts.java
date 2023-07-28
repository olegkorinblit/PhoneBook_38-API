package okhttp;


import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ContactListDTO;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;



public class GetAllContacts {
    Gson gson=new Gson();
   OkHttpClient client = new OkHttpClient();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MTEwMDM1NCwiaWF0IjoxNjkwNTAwMzU0fQ.mb7blnGJMfNSgFmN364XASI4F4AIlZu2aOR8WnYTsp4";

    @Test
    public void getAllContactsPositive() throws IOException {

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());

        ContactListDTO contacts = gson.fromJson(response.body().string(), ContactListDTO.class);

        for(ContactDTO contactDTO : contacts.getContacts()){
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getEmail());
            System.out.println("===================================");
        }
    }
}
