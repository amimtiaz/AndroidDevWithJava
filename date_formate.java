// date formate
private SimpleDateFormat dateFormat = new SimpleDateFormat("d,MMM, yyyy", Locale.ENGLISH);


// bangla date formate --> it will remove hh:mm:ss --> if you don't to do that, hh:mm:ss to the convert line to bangla locale date formate.
private String formatDateInEnglishAndBengali(String dateString) {
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

            Date date = originalFormat.parse(dateString);

            SimpleDateFormat bengaliFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("bn", "BD")); // Bengali locale

            String bengaliDate = bengaliFormat.format(date);

            return  bengaliDate;

        } catch (ParseException e) {
            e.printStackTrace();
            // Return the original string in case of an error
            return dateString;
        }
    }
