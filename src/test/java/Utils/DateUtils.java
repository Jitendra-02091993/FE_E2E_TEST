package Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
	private static String formattedDay;
	private static String formattedMonth;
	private static String formattedYear;
	public static Map<String,String> date;
	
	public static Map<String, String> dateCalculate() {
        // Get the current date
        Calendar currentDate = Calendar.getInstance();
        int year1 = currentDate.get(Calendar.YEAR);
        int month1 = currentDate.get(Calendar.MONTH) + 1; // Months are 0-based in Calendar
        int day1 = currentDate.get(Calendar.DAY_OF_MONTH);
        
        // Format the date as yyyy-mm-dd
        String formattedDate = String.format("%04d-%02d-%02d", year1, month1, day1);
//        System.out.println(formattedDate);

        // Example date (yyyy-mm-dd format)
        Date date1 = parseDate("1973-08-31");
//        System.out.println("=======date1======= \"" + date1 + "\"");

        Date today = parseDate(formattedDate);
        Date date2 = parseDate("2017-03-24");

        // Calculate the time in milliseconds for both dates
        long date1Milliseconds = date1.getTime();
        long todayMilliseconds = today.getTime();
        long date2Milliseconds = date2.getTime();
        long resultMilliseconds = date1Milliseconds + todayMilliseconds - date2Milliseconds;

        // Create a new Date object representing the result
        Date resultDate = new Date(resultMilliseconds);
        
        // Extract day, month, and year from the result
        Calendar resultCalendar = Calendar.getInstance();
        resultCalendar.setTime(resultDate);
        
        int day = resultCalendar.get(Calendar.DAY_OF_MONTH);
        int month = resultCalendar.get(Calendar.MONTH) + 1; // Note that January is 0-based
        int year = resultCalendar.get(Calendar.YEAR);
        
        // Format day and month as two digits
        formattedDay = String.format("%02d", day);
        formattedMonth = String.format("%02d", month);
        formattedYear = String.format("%04d", year);
        date = new HashMap<String,String>();
        date.put("date", formattedDay);
        date.put("month", formattedMonth);
        date.put("year", formattedYear);
//        System.out.println("===Entered DOB based date formula from Experian for happy flow==== :" + formattedDay+"-"+formattedMonth+"-"+formattedYear);
         return date;
    }

    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
//    public static void main(String[] args) {
//    	Map<String, String> a= dateCalculate();
//    	System.out.println("date is "+a.get("date"));
//    	System.out.println("month is "+a.get("month"));
//	}
}
