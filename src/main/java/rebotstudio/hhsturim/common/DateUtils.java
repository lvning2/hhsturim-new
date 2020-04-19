package rebotstudio.hhsturim.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {


    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 季度的开始时间
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = sdf.parse(sdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ceshi");
        return now;
    }

    /**
     * 季度的结束时间
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = sdf.parse(sdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    public static String GetQuarterByDate(Date date) throws ParseException, ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String year = String.valueOf(calendar.get(Calendar.YEAR));
        int mouth = date.getMonth();

        if(mouth>=0 && mouth<3){
            return year + "年第一季度";
        }else if(mouth>=3 && mouth<6){
            return year + "年第二季度";
        }else if(mouth>=6 && mouth<9){
            return year + "年第三季度";
        }else if(mouth>=9 && mouth<=12){
            return year + "年第四季度";
        }else{
            return "";
        }
    }




}
