![](https://github.com/kxs109/LoadingDrawable/raw/master/mydrawableviewapp/gif/loadingdrawable.gif)
# How to use
1. Add maven in your project build.gradle
```Java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
 2. Add depends in your module build.gradle
```Java
		dependencies {
	        compile 'com.github.kxs109:LoadingDrawable:1.0'
	}

  ```
  3. Use in XML
```Java
	<com.kxs.mydrawableview.MyDrawableView
        android:layout_width="90dp"
        android:layout_height="90dp"
        android:id="@+id/my_drawable"
        app:mydrawable="one"
        />

  ```
