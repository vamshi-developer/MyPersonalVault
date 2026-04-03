package com.example.mypersonalvault;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static final int PICK_FILE = 1;

    Button addDocBtn;
    RecyclerView recyclerView;
    TextView userEmail;

    ArrayList<DocumentModel> list;
    DocumentAdapter adapter;

    ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addDocBtn = findViewById(R.id.addDocBtn);
        recyclerView = findViewById(R.id.recyclerView);
        userEmail = findViewById(R.id.userEmail);

        api = RetrofitClient.getClient().create(ApiService.class);

        list = new ArrayList<>();
        adapter = new DocumentAdapter(list, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addDocBtn.setOnClickListener(v -> openFilePicker());

        String email = getSharedPreferences("MyApp", MODE_PRIVATE)
                .getString("email", "User");
        userEmail.setText(email);
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE && resultCode == Activity.RESULT_OK && data != null) {

            Uri uri = data.getData();

            try {
                // ✅ Read file
                InputStream inputStream = getContentResolver().openInputStream(uri);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);

                // ✅ File name
                String fileName = "doc_" + System.currentTimeMillis();

                // ✅ Correct format for Supabase
                RequestBody requestFile =
                        RequestBody.create(bytes, MediaType.parse("application/octet-stream"));

                // 🚀 Upload to Supabase
                api.uploadFile(fileName, requestFile).enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()) {

                            String publicUrl =
                                    "https://vzyzadpbvzbbcsfwzdjj.supabase.co/storage/v1/object/public/documents/" + fileName;

                            list.add(new DocumentModel(fileName, publicUrl));
                            adapter.notifyDataSetChanged();

                            Toast.makeText(HomeActivity.this, "Uploaded ✅", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(HomeActivity.this, "Upload failed ❌", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error reading file ❌", Toast.LENGTH_SHORT).show();
            }
        }
    }
}