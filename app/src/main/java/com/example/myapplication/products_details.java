package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class products_details extends AppCompatActivity {
    TextView title_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_details);
        String title = getIntent().getExtras().getString("title");
        title_view = findViewById(R.id.title_txt);
        title_view.setText(title);
    }
}
