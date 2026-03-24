package com.example.memoryjitter.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;

public class MemoryMonitor {

    private Context context;

    public MemoryMonitor(Context context) {
        this.context = context;
    }

    public String getMemoryInfo() {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        long totalMemory = memoryInfo.totalMem / (1024 * 1024);
        long availableMemory = memoryInfo.availMem / (1024 * 1024);
        long usedMemory = totalMemory - availableMemory;

        Debug.MemoryInfo debugMemoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(debugMemoryInfo);

        return String.format(
            "总内存: %d MB\n可用内存: %d MB\n已用内存: %d MB\nJava堆: %d KB",
            totalMemory, availableMemory, usedMemory, debugMemoryInfo.getTotalPss()
        );
    }
}
