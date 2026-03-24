# MemoryJitterDemo

一个用于学习 Android 内存优化的 Java 演示项目，展示了常见的内存抖动场景。

## 项目简介

本项目通过实际代码示例演示 Android 应用中常见的内存抖动问题，帮助开发者理解内存优化的重要性。

## 功能模块

### 1. 主界面 (MainActivity)
- 显示当前内存使用情况
- 提供跳转到各个演示模块的入口
- 实时监控内存变化

### 2. 对象创建演示 (ObjectCreationActivity)
- 演示频繁创建对象导致的内存抖动
- 展示字符串拼接的内存开销
- 对比不同对象创建方式的性能差异

### 3. Bitmap 管理演示 (BitmapActivity)
- 演示大图加载的内存占用
- 展示 Bitmap 回收机制
- 对比回收前后的内存变化

### 4. 集合扩容演示 (CollectionActivity)
- 演示 ArrayList 频繁扩容的内存开销
- 演示 HashMap 频繁扩容的内存开销
- 展示预分配容量的优化效果

## 技术栈

- **语言**: Java
- **构建工具**: Gradle 7.6
- **Android SDK**: compileSdk 33, minSdk 21, targetSdk 33
- **依赖库**:
  - AndroidX AppCompat
  - AndroidX ConstraintLayout
  - AndroidX Lifecycle (ViewModel, LiveData)
  - LeakCanary (内存泄漏检测)

## 项目结构

```
MemoryJitterDemo/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/memoryjitter/
│   │   │   ├── ui/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── ObjectCreationActivity.java
│   │   │   │   ├── BitmapActivity.java
│   │   │   │   └── CollectionActivity.java
│   │   │   └── utils/
│   │   │       └── MemoryMonitor.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── activity_object_creation.xml
│   │   │   │   ├── activity_bitmap.xml
│   │   │   │   └── activity_collection.xml
│   │   │   └── values/
│   │   │       └── strings.xml
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md
```

## 构建和运行

### 前置要求

- JDK 17 或更高版本
- Android Studio (推荐) 或 Android SDK 命令行工具

### 构建项目

```bash
# Linux/Mac
./gradlew build

# Windows
gradlew.bat build
```

### 安装到设备

```bash
# 安装 Debug 版本
./gradlew installDebug

# 安装 Release 版本
./gradlew installRelease
```

### 运行测试

```bash
./gradlew test
```

## 内存优化要点

### 1. 对象创建优化
- 避免在循环中创建临时对象
- 使用对象池复用对象
- 优先使用基本类型而非包装类

### 2. Bitmap 优化
- 及时回收不再使用的 Bitmap
- 使用适当的缩放比例加载图片
- 考虑使用 Glide/Picasso 等图片加载库

### 3. 集合优化
- 预估集合大小并设置初始容量
- 避免频繁扩容导致的内存抖动
- 及时清理不再使用的集合元素

## LeakCanary 集成

本项目集成了 LeakCanary 2.10，用于自动检测内存泄漏。当应用检测到内存泄漏时，会显示通知并提供详细的泄漏报告。

## 许可证

本项目仅供学习和演示用途。

## 参考资料

- [Android 内存优化官方文档](https://developer.android.com/topic/performance/memory)
- [LeakCanary 官方文档](https://square.github.io/leakcanary/)
- [Android 性能优化最佳实践](https://developer.android.com/topic/performance)
