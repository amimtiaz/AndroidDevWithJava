AlertDialog.Builder alert = new AlertDialog.Builder(this);
alert.setTitle("Alert")
     .setMessage("Are you sure you want to delete?")
     .setIcon(android.R.drawable.stat_notify_error)
     .setPositiveButton("Yes", (dialog, which) -> {
         // Handle positive action (e.g., deletion)
     })
     .setNegativeButton("No", (dialog, which) -> {
         // Handle negative action (e.g., cancel)
     })
     .show();

// Imtiaz
