package com.example.mypersonalvault;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DocumentAdapter adapter;
    List<DocumentModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        list.add(new DocumentModel("Aadhaar Card"));
        list.add(new DocumentModel("PAN Card"));
        list.add(new DocumentModel("Driving License"));
        list.add(new DocumentModel("10th Certificate"));

        adapter = new DocumentAdapter(list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}