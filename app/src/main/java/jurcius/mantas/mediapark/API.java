package jurcius.mantas.mediapark;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String BASE_URL = "https://development.espark.lt/api/mobile/public/";

    @GET("availablecars")
    Call<List<Car>> getCars();
}
