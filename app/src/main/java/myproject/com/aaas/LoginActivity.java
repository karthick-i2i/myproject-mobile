package myproject.com.aaas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;



/**
 * A login screen that offers login via username and QR code.
 */
public class LoginActivity extends AppCompatActivity{

    // UI references.
    private EditText musername;
    private EditText mbase_url;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        musername = (EditText) findViewById(R.id.username);

        // save base api url
        mbase_url = (EditText) findViewById(R.id.base_url);
        SharedPreferences.Editor editor = getSharedPreferences("base_url", MODE_PRIVATE).edit();
        editor.putString("base_url", mbase_url.getText().toString());
        editor.commit();

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
    }



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Store values at the time of the login attempt.
        String username = musername.getText().toString();
        String base_url = mbase_url.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid username
        if (TextUtils.isEmpty(username)) {
            musername.setError(getString(R.string.error_field_required));
            focusView = musername;
            cancel = true;
        }

        if (TextUtils.isEmpty(base_url)) {
            mbase_url.setError(getString(R.string.error_field_required));
            focusView = mbase_url;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            Intent qrcode = new Intent(LoginActivity.this, QRScannerActivity.class);
            qrcode.putExtra("musername", username);
            this.startActivity(qrcode);

        }
    }

}

