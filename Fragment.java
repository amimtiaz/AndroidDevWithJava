//layout

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TimePicker
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/tp1" />

    <Button
        android:text="Done"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/buDome" />
    
</LinearLayout>

//Fragment class

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * Created by Imtiaz on 26/2/24.
 */

public class PopTime extends DialogFragment implements View.OnClickListener {

    View view;
    TimePicker tp;
    Button buDome;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.pop_time,container,false);
        tp=(TimePicker)view.findViewById(R.id.tp1);
        buDome=(Button)view.findViewById(R.id.buDome);
        buDome.setOnClickListener(this);
        return view;
   }


    @Override
    public void onClick(View v) {
        this.dismiss();
        String timeOn=tp.getHour() +":"+ tp.getMinute();
MainActivity ma=(MainActivity)getActivity();
        ma.SetTime(timeOn);
    }
}


/display Fragment from Activity

 public void SetTime(String Time){
  Toast.makeText(getApplicationContext(),Time,Toast.LENGTH_LONG).show();
    }


    public void bushow(View view) {
        android.app.FragmentManager fm= getFragmentManager();
        PopTime popTime=new PopTime();
        popTime.show(fm,"Show fragment");

    }


