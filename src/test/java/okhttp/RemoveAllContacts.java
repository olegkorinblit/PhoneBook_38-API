package okhttp;

import dto.ContactResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RemoveAllContacts implements Helper {


    String endpoint = "contacts/clear";

    @Test
    public void deleteAllContactByIDPositive() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint+ "/" )
                .addHeader(authHeader, TOKEN)
                .delete()
                .build();

        Response response = client.newCall(request).execute();
        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
        String message = contactResponseDTO.getMessage();
        System.out.println(message);
        if(response.isSuccessful()) {
            System.out.println("Response code is: " + response.code());
        }
        else {
            System.out.println("Response code is: " + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
            System.out.println("ERROR: ERROR Error error: ");
            Assert.assertTrue(response.isSuccessful());
        }
    }
}
