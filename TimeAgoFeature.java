 public String covertTimeToText(String dataDate) {

        String convTime = null;

        String prefix = "";
        String suffix = "আগে";  // "Ago" in Bengali

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date pasTime = dateFormat.parse(dataDate);

            Date nowTime = new Date();

            long dateDiff = nowTime.getTime() - pasTime.getTime();

            long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
            long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
            long hour   = TimeUnit.MILLISECONDS.toHours(dateDiff);
            long day    = TimeUnit.MILLISECONDS.toDays(dateDiff);

            if (second < 60) {
                convTime = convertNumberToBengali(second) + " সেকেন্ড " + suffix;
            } else if (minute < 60) {
                convTime = convertNumberToBengali(minute) + " মিনিট " + suffix;
            } else if (hour < 24) {
                convTime = convertNumberToBengali(hour) + " ঘণ্টা " + suffix;
            } else if (day >= 7) {
                if (day > 360) {
                    convTime = convertNumberToBengali(day / 360) + " বছর " + suffix;
                } else if (day > 30) {
                    convTime = convertNumberToBengali(day / 30) + " মাস " + suffix;
                } else {
                    convTime = convertNumberToBengali(day / 7) + " সপ্তাহ " + suffix;
                }
            } else if (day < 7) {
                convTime = convertNumberToBengali(day) + " দিন " + suffix;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("ConvTimeE", e.getMessage());
        }

        return convTime;
    }

    // Function to convert English numbers to Bengali numbers
    private String convertNumberToBengali(long number) {
        String[] bengaliNumbers = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};
        String numberStr = String.valueOf(number);
        StringBuilder bengaliNumberStr = new StringBuilder();

        for (char c : numberStr.toCharArray()) {
            if (Character.isDigit(c)) {
                bengaliNumberStr.append(bengaliNumbers[c - '0']);
            } else {
                bengaliNumberStr.append(c);  // In case of any non-digit characters
            }
        }

        return bengaliNumberStr.toString();
    }
