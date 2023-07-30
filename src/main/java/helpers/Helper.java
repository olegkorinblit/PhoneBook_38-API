package helpers;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface Helper {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    Gson gson=new Gson();
    OkHttpClient client=new OkHttpClient();
    String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiaHdAaHciLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MTI1MzA4OCwiaWF0IjoxNjkwNjUzMDg4fQ.4-cZYNaA6enWVD73TVVpeQx-DW55l5om2fi6ICnQeP4";

    String BASE_URI = "https://contactapp-telran-backend.herokuapp.com";
    String PATH = "v1";

    String authHeader = "Authorization";

    int i = new Random().nextInt(1000) + 1000;


}
