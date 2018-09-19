package learn.apacheCommons;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilTest {

    public static void main(String[] args) {



        System.out.println(DateUtils.addDays(new Date(),2));
        System.out.println(DateUtils.addHours(new Date(),3));
        System.out.println(DateUtils.addWeeks(new Date(),1));
        System.out.println(DateUtils.isSameInstant(new Date(),new Date()));
        try {
            Date d = DateUtils.parseDate("2015-06-23","yyyy-MM-dd");
            System.out.println(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------------");
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println(format.format(date));

        System.out.println(DateFormatUtils.format(new Date(),"yyyy年MM月dd日"));//同上效果一样

    }
}
