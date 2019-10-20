# LoadingButton
A small utility to show loading animation inside a button

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/854722f7c90a43e29caf0b7b477d1ee9)](https://app.codacy.com/app/droidbond/LoadingButton?utm_source=github.com&utm_medium=referral&utm_content=droidbond/LoadingButton&utm_campaign=Badge_Grade_Dashboard)
[![](https://jitpack.io/v/droidbond/LoadingButton.svg)](https://jitpack.io/v/droidbond/LoadingButton)

## Installation (with Gradle)
Add the dependency to your root *build.gradle*:
```groovy
   repositories {
        jcenter()
        maven { url "https://jitpack.io" }
   }
   ```
Now add this dependency in your module *build.gradle*
```groovy
   dependencies {
         implementation 'com.github.droidbond:LoadingButton:0.1.7'
   }
```

### Demo
  <img src="https://github.com/droidbond/LoadingButton/blob/master/device-2018-11-22-155730.png" width="250"> <img src="https://github.com/droidbond/LoadingButton/blob/master/device-2018-11-22-155750.png" width="250"> <img src="https://github.com/droidbond/LoadingButton/blob/master/device-2018-11-26-120750.png" width="250">

### How to use
For using custom attributes use `app` namespace
```xml
xmlns:app="http://schemas.android.com/apk/res-auto"
 ```
```xml
     <com.droidbond.loadingbutton.LoadingButton
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                app:text="Custom Button"
                app:background="@color/colorAccent"
                app:successBackground="@drawable/ic_bg_blue_buttons_style"
                app:successIcon="@drawable/ic_done_white_24dp"
                app:textSize="16sp"
                app:boldText="true"
                app:textColor="@color/black"
                app:progressColor="@color/black"
                app:progressBarSize="small"
                android:id="@+id/custombtn"
                app:layout_constraintStart_toStartOf="parent"
                android:layout_marginTop="20dp"
                app:layout_constraintTop_toBottomOf="@+id/normal"
                app:layout_constraintEnd_toEndOf="parent"
                android:layout_marginEnd="8dp"
                android:layout_marginRight="8dp"
                android:layout_marginStart="8dp"
                android:layout_marginLeft="8dp"/>
```
### Customizable attributes
```xml
         <attr name="text" format="string"/>
                <attr name="textColor" format="color"/>
                <attr name="boldText" format="boolean"/>
                <attr name="textSize" format="dimension"/>
                <attr name="progressColor" format="color"/>
                <attr name="background" format="reference"/>
                <attr name="successBackground" format="reference"/>
                <attr name="errorBackground" format="reference"/>
                <attr name="successIcon" format="reference"/>
                <attr name="errorIcon" format="reference"/>
                <attr name="customFontFamily" format="reference"/>
                <attr name="progressBarSize" format="enum">
                    <enum name="large" value="64"/>
                    <enum name="medium" value="48"/>
                    <enum name="small" value="18"/>
                </attr>
```
```java
LoadingButton custombtn = findViewById(R.id.custombtn);
```
### Show Loading
```java
custombtn.showLoading()
```
### Hide Loading
```java
custombtn.hideLoading()
```
### Show Success
```java
custombtn.showSuccess()
```
### Show Error
```java
custombtn.showError()
```
### Loading status
```java
custombtn.isLoading()
```

### Set Custom TypeFace
```java
custombtn.setTypeFace(typeface)
```

For a complete example, see the sample app `https://github.com/droidbond/LoadingButton/tree/master/app`
