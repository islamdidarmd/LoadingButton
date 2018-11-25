# LoadingButton
A lightweight library to show loading animation inside a button

[![](https://jitpack.io/v/droidbond/LoadingButton.svg)](https://jitpack.io/v/droidbond/LoadingButton)

## Installation (with Gradle)
Add the dependency to your *build.gradle*:

```groovy
   repositories {
        jcenter()
        maven { url "https://jitpack.io" }
   }
   dependencies {
         implementation 'com.github.droidbond:LoadingButton:0.1.4'
   }
```
```
minSdk 15
targetSdk 28
```

### Demo
  <img src="https://github.com/droidbond/LoadingButton/blob/master/device-2018-11-22-155730.png" width="250">
  <img src="https://github.com/droidbond/LoadingButton/blob/master/device-2018-11-22-155750.png" width="250">

### How to use
Inside xml layout, add this in your root element. For using custom attributes use `app` namespace
```
xmlns:app="http://schemas.android.com/apk/res-auto"
 ```
```xml
    <com.droidbond.loadingbutton.LoadingButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/normal"
            app:text="@string/continue_text"
            app:textColor="@color/white"
            app:progressColor="@color/black"
            app:progressBarSize="small"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            android:layout_marginTop="28dp"
            app:layout_constraintEnd_toEndOf="parent"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginStart="8dp"/>
```
### Customizable attributes
```xml
        <attr name="text" format="string"/>
        <attr name="textColor" format="color"/>
        <attr name="boldText" format="boolean"/>
        <attr name="textSize" format="dimension"/>
        <attr name="progressColor" format="color"/>
        <attr name="background" format="reference"/>
        <attr name="progressBarSize" format="enum">
            <enum name="large" value="64"/>
            <enum name="medium" value="48"/>
            <enum name="small" value="18"/>
        </attr>
```
### Show Loading
```java
custombtn.showLoading()
```
### Hide Loading
```java
custombtn.hideLoading()
```

For a complete example see the sample app https://github.com/droidbond/LoadingButton/tree/master/app
