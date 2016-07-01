package myproject.com.api;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ideas2it-dineshraghavan on 29/6/16.
 */

public class RetrofitAPIBuilder extends AppCompatActivity {
    static Retrofit retrofit = null;

    //"http://localhost:8080
    public static Retrofit getInstance(String baseURL) {
        final OkHttpClient okHttpClient = new OkHttpClient();
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL+"/services/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
