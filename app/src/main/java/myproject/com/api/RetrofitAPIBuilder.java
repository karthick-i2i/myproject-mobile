package myproject.com.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by ideas2it-dineshraghavan on 29/6/16.
 */

public class RetrofitAPIBuilder {
    static  Retrofit retrofit = null;
    public static Retrofit getInstance() {

        final OkHttpClient okHttpClient = new OkHttpClient();

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://localhost:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
