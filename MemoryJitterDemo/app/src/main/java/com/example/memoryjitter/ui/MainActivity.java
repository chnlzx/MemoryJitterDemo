package com.example.memoryjitter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.memoryjitter.R;
import com.example.memoryjitter.utils.MemoryMonitor;

public class MainActivity extends AppCompatActivity {

    private TextView tvMemoryDetails;
    private MemoryMonitor memoryMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoryMonitor = new MemoryMonitor(this);

        Button btnObjectCreation = findViewById(R.id.btnObjectCreation);
        Button btnBitmap = findViewById(R.id.btnBitmap);
        Button btnCollection = findViewById(R.id.btnCollection);
        tvMemoryDetails = findViewById(R.id.tvMemoryDetails);

        btnObjectCreation.setOnClickListener(v -> {
            startActivity(new Intent(this, ObjectCreationActivity.class));
        });

        btnBitmap.setOnClickListener(v -> {
            startActivity(new Intent(this, BitmapActivity.class));
        });

        btnCollection.setOnClickListener(v -> {
            startActivity(new Intent(this, CollectionActivity.class));
        });

        updateMemoryDisplay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMemoryDisplay();
    }

    private void updateMemoryDisplay() {
        String memoryInfo = memoryMonitor.getMemoryInfo();
        tvMemoryDetails.setText(memoryInfo);
    }
}
