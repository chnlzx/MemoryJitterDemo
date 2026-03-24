package com.example.memoryjitter.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.memoryjitter.R;
import com.example.memoryjitter.utils.MemoryMonitor;

public class ObjectCreationActivity extends AppCompatActivity {

    private TextView tvResult;
    private MemoryMonitor memoryMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_creation);

        memoryMonitor = new MemoryMonitor(this);
        tvResult = findViewById(R.id.tvResult);

        Button btnCreateObjects = findViewById(R.id.btnCreateObjects);
        Button btnStringConcat = findViewById(R.id.btnStringConcat);

        btnCreateObjects.setOnClickListener(v -> createObjects());
        btnStringConcat.setOnClickListener(v -> stringConcat());
    }

    private void createObjects() {
        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long startMemory = runtime.totalMemory() - runtime.freeMemory();

        // 频繁创建临时对象
        for (int i = 0; i < 10000; i++) {
            String temp = "Object " + i;
            Integer value = i;
        }

        long endTime = System.currentTimeMillis();
        long endMemory = runtime.totalMemory() - runtime.freeMemory();

        String result = String.format(
            "创建10000个对象:\n耗时: %d ms\n内存增加: %d KB",
            endTime - startTime,
            (endMemory - startMemory) / 1024
        );
        tvResult.setText(result);
    }

    private void stringConcat() {
        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long startMemory = runtime.totalMemory() - runtime.freeMemory();

        // 字符串拼接导致大量临时对象
        String result = "";
        for (int i = 0; i < 1000; i++) {
            result += "Item " + i + " ";
        }

        long endTime = System.currentTimeMillis();
        long endMemory = runtime.totalMemory() - runtime.freeMemory();

        String info = String.format(
            "字符串拼接测试:\n耗时: %d ms\n内存增加: %d KB",
            endTime - startTime,
            (endMemory - startMemory) / 1024
        );
        tvResult.setText(info);
    }
}
