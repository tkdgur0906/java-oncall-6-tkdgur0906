package oncall.util;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

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
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        return 28;
    }

    private enum Regex {
        SPACE(" "), NO_SPACE(""),
        SQUARE_BRACKETS_START("["), SQUARE_BRACKETS_END("]"),
        COMMA(",");

        private final String regex;

        Regex(String regex) {
            this.regex = regex;
        }
    }
}
