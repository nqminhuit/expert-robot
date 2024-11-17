package org.nqm.er.common.utils;

import static java.time.format.DateTimeFormatter.ofPattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public class ErDateUtils {

    public static final String NYPOST_DATE_PATTERN = "MMM dd, yyyy hh:mma";
    public static final DateTimeFormatter FULL_MONTH_NAME_FORMATTER = ofPattern("MMMM d, yyyy");

    public static Long getTimestamp(String dateStr, String pattern) {
    var sdt = new SimpleDateFormat();
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }

        sdt.applyPattern(pattern);
        try {
            return sdt.parse(dateStr).getTime();
        }
        catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static int getMonth(String date) {
        return YearMonth.parse(date, ofPattern("MMMM yyyy", Locale.US)).getMonthValue();
    }
}
