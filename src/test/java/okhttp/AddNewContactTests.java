package okhttp;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContactTests implements Helper {

    String endpoint = "contacts";

    @Test(invocationCount = 5)
    public void addNewContactPositive() throws IOException {

        ContactDTO contactDTO = ContactDTO.builder()
                .name("QA38")
                .lastName("Automation")
                .email("qa38_" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Rehovot")
                .description("Students")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);

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
        String id = message.substring(message.lastIndexOf(" ") + 1);
        //pri sozdanii contacta daet Contact was added! ID: 26b35f7e-c535-43e1-9f56-d9f1f026e83b;
        // komanda:lastIndexOf(" ") + 1 daet poslednii probel i sleduusi element
        System.out.println(id);

    }
}
