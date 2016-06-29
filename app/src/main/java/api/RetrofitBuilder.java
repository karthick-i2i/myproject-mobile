package api;

import java.util.List;

/**
 * Created by ideas2it-dineshraghavan on 29/6/16.
 */
public class RetrofitBuilder {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build();


}
