[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![](https://jitpack.io/v/HamidrezaAmz/android-support-pdfium.svg)](https://jitpack.io/#HamidrezaAmz/android-support-pdfium)


### Installing

Step 1. Add the JitPack repository to your build file,
Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }
```

Step 2. Add the dependency

```gradle
dependencies {
       implementation 'com.github.hamidrezaamz:android-support-pdfium:1.0.1'
}
```


# Build PDFium Library for Android

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4e17a6cb9ed94fec9895162a0c2531d1)](https://app.codacy.com/app/benjamin-linus/android-support-pdfium?utm_source=github.com&utm_medium=referral&utm_content=benjinus/android-support-pdfium&utm_campaign=Badge_Grade_Settings)

This library instructs how to build pdfium natives with [origin/HEAD](https://pdfium.googlesource.com/pdfium/+/master/) for Android. 


## HOW-TO
Fork a copy of the repo, follow <b>BUILD.md</b> to build the natives for Android

## Features
* All features from barteksc/PdfiumAndroid
* Text functions(locate/extract)
* Search functions(previous/next)

## Thanks
jni layer mod from [barteksc/PdfiumAndroid](https://github.com/barteksc/PdfiumAndroid
