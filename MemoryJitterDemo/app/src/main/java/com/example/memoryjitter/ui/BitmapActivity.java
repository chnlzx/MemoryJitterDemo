package com.example.memoryjitter.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.memoryjitter.R;
import com.example.memoryjitter.utils.MemoryMonitor;

import java.util.ArrayList;
import java.util.List;

public class BitmapActivity extends AppCompatActivity {

    private TextView tvBitmapInfo;
    private MemoryMonitor memoryMonitor;
    private List<Bitmap> bitmapList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        memoryMonitor = new MemoryMonitor(this);
        tvBitmapInfo = findViewById(R.id.tvBitmapInfo);

        Button btnLoadBitmap = findViewById(R.id.btnLoadBitmap);
        Button btnRecycleBitmap = findViewById(R.id.btnRecycleBitmap);

        btnLoadBitmap.setOnClickListener(v -> loadBitmaps());
        btnRecycleBitmap.setOnClickListener(v -> recycleBitmaps());
    }

    private void loadBitmaps() {
        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long startMemory = runtime.totalMemory() - runtime.freeMemory();

        // 创建多个大图
        for (int i = 0; i < 10; i++) {
            Bitmap bitmap = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            canvas.drawRect(0, 0, 1024, 1024, paint);
            bitmapList.add(bitmap);
        }

        long endTime = System.currentTimeMillis();
        long endMemory = runtime.totalMemory() - runtime.freeMemory();

        String info = String.format(
            "加载10张1024x1024图片:\n耗时: %d ms\n内存增加: %d MB",
            endTime - startTime,
            (endMemory - startMemory) / (1024 * 1024)
        );
        tvBitmapInfo.setText(info);
    }

    private void recycleBitmaps() {
        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long startMemory = runtime.totalMemory() - runtime.freeMemory();

        // 回收所有Bitmap
        for (Bitmap bitmap : bitmapList) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
        bitmapList.clear();

        System.gc();

        long endTime = System.currentTimeMillis();
        long endMemory = runtime.totalMemory() - runtime.freeMemory();

        String info = String.format(
            "回收Bitmap:\n耗时: %d ms\n内存变化: %d KB",
            endTime - startTime,
            (endMemory - startMemory) / 1024
        );
        tvBitmapInfo.setText(info);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recycleBitmaps();
    }
}
