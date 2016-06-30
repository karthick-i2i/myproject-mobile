package myproject.com.aaas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import myproject.com.api.APIResponse;
import myproject.com.api.RetrofitAPIBuilder;
import myproject.com.api.UserApp;
import myproject.com.api.UserAppService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class QRScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Programmatically initialize the scanner view
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);

        // Start camera
        mScannerView.startCamera();
    }


    @Override
    public void onPause() {
        super.onPause();

        // Stop camera on pause
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        getIntent().getExtras().get("musername");

        Retrofit retrofitAPI = RetrofitAPIBuilder.getInstance();
        UserAppService userAppService = retrofitAPI.create(UserAppService.class);
        Call call = userAppService.saveUserQRCode(getIntent()
                        .getExtras().get("musername").toString(),
                rawResult.getText());
        call.enqueue(new Callback<APIResponse<UserApp>>() {
            @Override
            public void onResponse(Call<APIResponse<UserApp>> call, Response<APIResponse<UserApp>> response) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                Intent result = new Intent(QRScannerActivity.this, ResultActivity.class);
                startActivity(result);
            }

            @Override
            public void onFailure(Call<APIResponse<UserApp>> call, Throwable t) {
                Log.e("handler", "1"+t.getMessage()); // Prints the scan format (qrcode)

            }


            // If you would like to resume scanning, call this method below:
            // mScannerView.resumeCameraPreview(this);
        });
    }
}

