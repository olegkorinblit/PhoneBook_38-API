package okhttp;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateContact implements Helper {

    String endpoint = "contacts";
    String id;

    @BeforeMethod
    public void precondition() throws IOException {

        ContactDTO contactDTO = ContactDTO.builder()
                .name("QA38")
                .lastName("Automation")
                .email("qa38_" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Rehovot")
                .description("Students")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);
        System.out.println(contactDTO);

        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint)
                .addHeader(authHeader, TOKEN)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);

        Assert.assertTrue(response.isSuccessful());
        String message = contactResponseDTO.getMessage();
        System.out.println(message);
        id = message.substring(message.lastIndexOf(" ") + 1);
    }
    @Test
    public  void  UpdateContact() throws IOException {
        ContactDTO contactDTO = ContactDTO.builder()
                .id(id)
                .name("QA42")
                .lastName("Automation")
                .email("qa42_" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Tel-Aviv")
                .description("Stud")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);

        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint)
                .addHeader(authHeader, TOKEN)
                .put(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
        System.out.println(contactResponseDTO);
        String message = contactResponseDTO.getMessage();
        System.out.println(message);
        System.out.println(contactDTO);

        if(response.isSuccessful()) {
            System.out.println("Response code is: " + response.code());
        }
        else {
            System.out.println("Response code is: " + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
            System.out.println("ERROR: ADD Contact:Failed");
            Assert.assertTrue(response.isSuccessful());
        }
    }


}
