package learn.apacheCommons;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class CommonsDate {

    public static void main(String[] args) {
        Date yesterday = DateUtils.addHours(DateUtils.truncate(new Date(), Calendar.DATE), -3);
        Date today = DateUtils.addHours(DateUtils.truncate(new Date(), Calendar.DATE), 21);

        System.out.println(yesterday);
        System.out.println(today);


    }
}
