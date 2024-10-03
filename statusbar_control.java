// remove status bar
 Window  window = getWindow();
 window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//==>>

View decorView = getWindow().getDecorView();
decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);


//==>>


// sortcut way to remove status bar
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

// change the status bar color
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.your_color));
}

//==>>
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
}


// Set Translucent Status Bar
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    getWindow().setStatusBarColor(Color.TRANSPARENT);
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
}


// xml approach
<item name="android:statusBarColor">@android:color/transparent</item>
<item name="android:windowLightStatusBar">true</item> <!-- for dark icons -->
