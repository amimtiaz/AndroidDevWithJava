public void startAlert() {  
    // Set specific date and time: 25/12/2012 at 12:00
    Calendar myAlarmDate = Calendar.getInstance();
    myAlarmDate.setTimeInMillis(System.currentTimeMillis());
    myAlarmDate.set(2012, Calendar.DECEMBER, 25, 12, 0, 0);

    // Set alarm time (HOUR, MINUTE) from user input or other source
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, Hour);
    calendar.set(Calendar.MINUTE, Minute);
    calendar.set(Calendar.SECOND, 0);

    // Define a repeating alarm starting every 2 minutes
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, AlarmReceiver.class);
    intent.setAction("com.quranonline.Broadcast");
    intent.putExtra("MyMessage", context.getResources().getString(R.string.msg_notify));
    
    PendingIntent pendingIntent = PendingIntent.getBroadcast(
            context, 
            0, 
            intent, 
            PendingIntent.FLAG_UPDATE_CURRENT
    );

    // Set repeating alarm to fire every day
    alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, 
            calendar.getTimeInMillis(), 
            2 * 60 * 1000,  // Repeat every 2 minutes
            pendingIntent
    );

    // One-time alarm at the specified date/time
    alarmManager.set(
            AlarmManager.RTC_WAKEUP, 
            myAlarmDate.getTimeInMillis(), 
            pendingIntent
    );
}

/* 
    Required Permissions:
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

    Required Filters:
    <action android:name="android.intent.action.BOOT_COMPLETED" />
    <action android:name="android.intent.action.ACTION_SHUTDOWN" />
    <action android:name="android.intent.action.QUICKBOOT_POWEROFF" />
*/
