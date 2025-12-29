# **Android Blur Image Maker**

---
**Android Blur Image Maker** is a custom Android library that allows you to easily blur images with multiple blur effects, overlay colors, and alpha control. You can apply Gaussian, Box, or Stack blur on any ImageView with simple XML attributes.

---

## ✨ **Features**

- Apply blur effect on any image.

- Supports Gaussian, Box, and Stack blur types.

- Customize blur radius and blur scale.

- Add an overlay color with adjustable alpha.

- Enable or disable the blur dynamically.

- Easy integration via XML attributes.

  ---

# **Preview**
---

<img src="https://github.com/S13reya/AndroidBlurImageMaker/blob/stages/app/src/main/assets/demo.png" height="320"/>

---

## ⚡ **Installation**

**Step 1:** Add JitPack repository to your root build.gradle:

```
gradle
maven { url = uri("https://jitpack.io") }
```

**Step 2:** Add the dependency in your app `build.gradle` (example if hosted on JitPack):  

```gradle
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:Android_BlurImageMaker:1.0.0'

}
```


## ⚡ **Usage**

1. Add in XML

```
<com.ext.android_blur_image_maker.BlurImageView
    android:layout_width="300dp"
    android:layout_height="300dp"
    android:layout_gravity="center"
    app:blurImageSrc="@drawable/seen"
    app:blurRadius="15"
    app:blurScale="0.25"
    app:blurEnabled="true"
    app:blurType="box"
    app:overlayColor="#000000"
    app:overlayAlpha="0.2" />


```







## **Supported Blur Types**

- Gaussian Blur – Uses RenderScript for high-quality blur (default).

- Box Blur – Fast blur with average pixel calculation.

- Stack Blur – Balanced blur (lightweight for performance).
  

## **📄 License**

**MIT License**  
```
Copyright (c) 2025 Excelsior Technologies

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED **"AS IS"**, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```



  
