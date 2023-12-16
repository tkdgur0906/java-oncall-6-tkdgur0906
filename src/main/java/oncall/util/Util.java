package oncall.util;

import oncall.domain.Constants;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import static oncall.domain.Constants.*;

public class Util {

    private Util() {
    }

    public static String removeSpace(String input) {
        return replace(input, Regex.SPACE, Regex.NO_SPACE);
    }


    private static String replace(String input, Regex target, Regex replacement) {
        return input.replace(target.regex, replacement.regex);
    }

    public static List<String> splitByComma(String input) {
        return Arrays.asList(removeSpace(input).split(Regex.COMMA.regex));
    }

    public static int calculateMonthEndDay(int month) {
        if (month == Month.JANUARY.month || month == Month.MARCH.month || month == Month.MAY.month
                || month == Month.JULY.month || month == Month.AUGUST.month || month == Month.OCTOBER.month) {
            return THIRTY_FIRST;
        }
        if (month == Month.APRIL.month || month == Month.JUNE.month ||
                month == Month.SEPTEMBER.month || month == Month.NOVEMBER.month) {
            return THIRTY;
        }
        return TWENTY_EIGHT;
    }

    private enum Regex {
        SPACE(" "),
        NO_SPACE(""),
        COMMA(",");

        private final String regex;

        Regex(String regex) {
            this.regex = regex;
        }
    }

    private enum Month {
        JANUARY(1), FEBRUARY(2), MARCH(3), APRIL(4), MAY(5), JUNE(6),
        JULY(7), AUGUST(8), SEPTEMBER(9), OCTOBER(10), NOVEMBER(11), DECEMBER(12);

        private final int month;

        Month(int month) {
            this.month = month;
        }
    }
}
