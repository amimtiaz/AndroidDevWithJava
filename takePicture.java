
// add permission
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
   <uses-feature android:name="android.hardware.camera2" android:required="true"></uses-feature>


//code
 void TakePicture(){
        Intent intent= new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,tag);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        if (requestCode==tag && resultCode==RESULT_OK){
         Bundle b=data.getExtras();
            Bitmap img=(Bitmap)b.get("data");
            imageView.setImageBitmap(img);

        }
    }
