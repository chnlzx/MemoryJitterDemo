package com.example.memoryjitter.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.memoryjitter.R;
import com.example.memoryjitter.utils.MemoryMonitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectionActivity extends AppCompatActivity {

    private TextView tvCollectionInfo;
    private MemoryMonitor memoryMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        memoryMonitor = new MemoryMonitor(this);
        tvCollectionInfo = findViewById(R.id.tvCollectionInfo);

        Button btnArrayList = findViewById(R.id.btnArrayList);
        Button btnHashMap = findViewById(R.id.btnHashMap);

        btnArrayList.setOnClickListener(v -> testArrayList());
        btnHashMap.setOnClickListener(v -> testHashMap());
    }

    private void testArrayList() {
        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long startMemory = runtime.totalMemory() - runtime.freeMemory();

        // ArrayList频繁扩容
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }

        long endTime = System.currentTimeMillis();
        long endMemory = runtime.totalMemory() - runtime.freeMemory();

        String info = String.format(
            "ArrayList扩容测试:\n元素数量: %d\n耗时: %d ms\n内存增加: %d KB",
            list.size(),
            endTime - startTime,
            (endMemory - startMemory) / 1024
        );
        tvCollectionInfo.setText(info);
    }

    private void testHashMap() {
        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long startMemory = runtime.totalMemory() - runtime.freeMemory();

        // HashMap频繁扩容
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            map.put(i, "Value " + i);
        }

        long endTime = System.currentTimeMillis();
        long endMemory = runtime.totalMemory() - runtime.freeMemory();

        String info = String.format(
            "HashMap扩容测试:\n元素数量: %d\n耗时: %d ms\n内存增加: %d KB",
            map.size(),
            endTime - startTime,
            (endMemory - startMemory) / 1024
        );
        tvCollectionInfo.setText(info);
    }
}
