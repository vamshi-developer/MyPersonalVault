package com.example.mypersonalvault;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn;
    TextView signupText; // 👈 NEW

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        signupText = findViewById(R.id.signupText); // 👈 NEW

        loginBtn.setOnClickListener(v -> loginUser());

        // 🚀 GO TO SIGNUP SCREEN
        signupText.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });
    }

    private void loginUser() {

        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        // 🔒 Validation
        if (emailText.isEmpty() || passwordText.isEmpty()) {
            Toast.makeText(this, "Enter email & password", Toast.LENGTH_SHORT).show();
            return;
        }

        // 🔄 Disable button while loading
        loginBtn.setEnabled(false);
        loginBtn.setText("Logging in...");

        // ✅ Request body
        Map<String, String> map = new HashMap<>();
        map.put("email", emailText);
        map.put("password", passwordText);

        ApiService api = RetrofitClient.getClient().create(ApiService.class);

        Call<ResponseBody> call = api.login(map);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                // 🔄 Enable button again
                loginBtn.setEnabled(true);
                loginBtn.setText("Login");

                if (response.isSuccessful()) {

                    Toast.makeText(LoginActivity.this, "Login Success ✅", Toast.LENGTH_SHORT).show();

                    // 🚀 Go to Home
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials ❌", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                // 🔄 Enable button again
                loginBtn.setEnabled(true);
                loginBtn.setText("Login");

                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}