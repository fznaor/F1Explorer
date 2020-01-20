package hr.ferit.filipznaor.f1explorer.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {
    private static final String BASE_API = "https://ergast.com/api/f1/";
    private static APIInterface apiInterface;
    public static APIInterface getApiInterface(){
        if (apiInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterface = retrofit.create(APIInterface.class);
        }
        return apiInterface;
    }
}
