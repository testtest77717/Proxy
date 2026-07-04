# Android HTTP Interceptor

A lightweight Android application for intercepting and editing HTTP/HTTPS requests and responses, similar to Burp Suite but optimized for mobile devices.

## Features

- ✅ Intercept HTTP/HTTPS traffic
- ✅ View request/response headers and body
- ✅ Edit requests before sending
- ✅ Edit responses before displaying
- ✅ Clean and intuitive UI
- ✅ Request history/logging
- ✅ Certificate management for HTTPS interception
- ✅ Pattern-based request filtering

## Requirements

- Android 8.0+ (API 26+)
- Kotlin 1.8+
- Gradle 7.0+

## Architecture

```
android-http-interceptor/
├── app/src/main/java/com/interceptor/
│   ├── ui/
│   ├── network/
│   ├── model/
│   ├── viewmodel/
│   └── MainActivity.kt
├── build.gradle
└── AndroidManifest.xml
```

## Getting Started

1. Clone the repository
2. Open with Android Studio
3. Build and run on Android device/emulator
4. Grant required permissions
5. Start intercepting requests

## License

MIT License
