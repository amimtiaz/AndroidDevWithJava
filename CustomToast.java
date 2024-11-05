// Layout custom_toast.xml
/*
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:padding="10dp"
    android:background="@drawable/toast_background">

    <ImageView
        android:id="@+id/toast_image"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_check_circle" />

    <TextView
        android:id="@+id/toast_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:text="Custom Toast Message"
        android:textColor="#FFFFFF"
        android:textSize="16sp" />

</LinearLayout>
*/

//toast_background.xml (drawable)
/* <?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="#333333"/>
    <corners android:radius="10dp"/>
    <padding android:left="10dp" android:top="5dp" android:right="10dp" android:bottom="5dp"/>
</shape> */


// Custom Toast method in Java
private void customToast() {
    // Inflate the custom toast layout
    LayoutInflater inflater = getLayoutInflater();
    View customView = inflater.inflate(R.layout.custom_toast, null);

    // Find and set the text in the custom layout
    TextView toastTitle = customView.findViewById(R.id.toast_text);
    toastTitle.setText("Course Enrolled");

    // Create a new Toast and set its properties
    Toast toast = new Toast(getApplicationContext());
    toast.setDuration(Toast.LENGTH_SHORT);
    toast.setView(customView);

    // Set the position of the Toast on the screen
    toast.setGravity(Gravity.BOTTOM, 0, 80); // 80 is the offset from the bottom

    // Show the custom Toast
    toast.show();
}
