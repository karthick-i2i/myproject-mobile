package myproject.com.api;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by karthick on 29/6/16.
 */
public interface UserAppService {

    @POST("users")
    Call<APIResponse<UserApp>> saveUserQRCode(
            @Path("username") String username,
            @Path("qrcode") String qrcode);


}
